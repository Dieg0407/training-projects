package com.dapg.handmade.marketplace.engine.iam.application;

import org.springframework.stereotype.Service;
import com.dapg.handmade.marketplace.engine.iam.domain.CreatedArtisanEvent;
import com.dapg.handmade.marketplace.engine.iam.domain.EventPublisher;
import com.dapg.handmade.marketplace.engine.iam.domain.Role;
import com.dapg.handmade.marketplace.engine.iam.domain.RoleAssignmentRepository;
import com.dapg.handmade.marketplace.engine.iam.domain.User;
import com.dapg.handmade.marketplace.engine.iam.domain.UserRepository;

@Service
public class CreateArtisanUseCase {
  private final UserRepository userRepository;
  private final RoleAssignmentRepository roleAssignmentRepository;
  private final EventPublisher eventPublisher;

  public CreateArtisanUseCase(UserRepository userRepository,
      RoleAssignmentRepository roleAssignmentRepository, EventPublisher eventPublisher) {
    this.userRepository = userRepository;
    this.roleAssignmentRepository = roleAssignmentRepository;
    this.eventPublisher = eventPublisher;
  }

  public void execute(String username, String password) {
    if (userRepository.findByUsermame(username).isPresent()) {
      throw new IllegalArgumentException(
          "Username %s already exists is already taken".formatted(username));
    }

    var user = new User(username, password);
    userRepository.save(user);

    var roleAssignment = user.assignRole(Role.ARTISAN);
    roleAssignmentRepository.save(roleAssignment);

    eventPublisher.publish(new CreatedArtisanEvent(user.id()));
  }
}
