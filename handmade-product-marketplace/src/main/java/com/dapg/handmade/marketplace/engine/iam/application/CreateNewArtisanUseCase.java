package com.dapg.handmade.marketplace.engine.iam.application;

import org.springframework.stereotype.Service;
import com.dapg.handmade.marketplace.engine.iam.application.command.CreateNewArtisanCommand;
import com.dapg.handmade.marketplace.engine.iam.domain.ArtisanProfile;
import com.dapg.handmade.marketplace.engine.iam.domain.ArtisanProfileId;
import com.dapg.handmade.marketplace.engine.iam.domain.ArtisanProfileRepository;
import com.dapg.handmade.marketplace.engine.iam.domain.CreatedArtisanEvent;
import com.dapg.handmade.marketplace.engine.iam.domain.EventPublisher;
import com.dapg.handmade.marketplace.engine.iam.domain.User;
import com.dapg.handmade.marketplace.engine.iam.domain.UserRepository;

@Service
public class CreateNewArtisanUseCase {
  private final UserRepository userRepository;
  private final ArtisanProfileRepository artisanProfileRepository;
  private final EventPublisher eventPublisher;

  public CreateNewArtisanUseCase(UserRepository userRepository,
      ArtisanProfileRepository artisanProfileRepository, EventPublisher eventPublisher) {
    this.userRepository = userRepository;
    this.artisanProfileRepository = artisanProfileRepository;
    this.eventPublisher = eventPublisher;
  }

  public ArtisanProfileId execute(CreateNewArtisanCommand command) {
    if (userRepository.findByUsername(command.username()).isPresent()) {
      throw new IllegalArgumentException("Username already exists");
    }

    var user = new User(command.username(), command.password());
    userRepository.save(user);

    var artisanProfile = new ArtisanProfile(command.shopName(), command.craftType(),
        command.countryCode(), command.phoneNumber(), user.id());
    artisanProfileRepository.save(artisanProfile);

    eventPublisher.publish(new CreatedArtisanEvent(artisanProfile));

    return artisanProfile.id();
  }
}
