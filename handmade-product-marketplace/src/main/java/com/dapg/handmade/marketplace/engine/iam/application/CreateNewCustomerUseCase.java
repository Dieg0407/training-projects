package com.dapg.handmade.marketplace.engine.iam.application;

import org.springframework.stereotype.Service;
import com.dapg.handmade.marketplace.engine.iam.application.command.CreateNewCustomerCommand;
import com.dapg.handmade.marketplace.engine.iam.domain.CreatedCustomerEvent;
import com.dapg.handmade.marketplace.engine.iam.domain.CustomerProfile;
import com.dapg.handmade.marketplace.engine.iam.domain.CustomerProfileId;
import com.dapg.handmade.marketplace.engine.iam.domain.CustomerProfileRepository;
import com.dapg.handmade.marketplace.engine.iam.domain.EventPublisher;
import com.dapg.handmade.marketplace.engine.iam.domain.User;
import com.dapg.handmade.marketplace.engine.iam.domain.UserRepository;

@Service
public class CreateNewCustomerUseCase {
  private final CustomerProfileRepository customerProfileRepository;
  private final UserRepository userRepository;
  private final EventPublisher eventPublisher;

  public CreateNewCustomerUseCase(CustomerProfileRepository customerProfileRepository,
      UserRepository userRepository, EventPublisher eventPublisher) {
    this.customerProfileRepository = customerProfileRepository;
    this.userRepository = userRepository;
    this.eventPublisher = eventPublisher;
  }

  public CustomerProfileId execute(CreateNewCustomerCommand command) {
    if (userRepository.findByUsermame(command.username()).isPresent()) {
      throw new IllegalArgumentException("Username already exists");
    }

    var user = new User(command.username(), command.password());
    userRepository.save(user);

    var customerProfile = new CustomerProfile(command.fullName(), command.address(), user.id());
    customerProfileRepository.save(customerProfile);

    eventPublisher.publish(new CreatedCustomerEvent(customerProfile));

    return customerProfile.id();
  }
}
