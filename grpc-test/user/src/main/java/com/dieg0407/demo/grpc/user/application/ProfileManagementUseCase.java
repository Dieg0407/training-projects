package com.dieg0407.demo.grpc.user.application;

import com.dieg0407.demo.grpc.user.domain.UserProfile;
import com.dieg0407.demo.grpc.user.domain.UserProfileId;
import com.dieg0407.demo.grpc.user.domain.UserProfileRepository;
import java.util.Optional;
import java.util.UUID;
import org.springframework.stereotype.Service;

@Service
public class ProfileManagementUseCase {

  private final UserProfileRepository userProfileRepository;

  public ProfileManagementUseCase(UserProfileRepository userProfileRepository) {
    this.userProfileRepository = userProfileRepository;
  }

  public Optional<UserProfile> findProfileById(final UUID id) {
    final var userProfileId = new UserProfileId(id);

    return userProfileRepository.findById(userProfileId);
  }
}
