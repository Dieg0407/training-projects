package com.dieg0407.demo.grpc.user.domain;

import java.util.Optional;

public interface UserProfileRepository {

  Optional<UserProfile> findById(UserProfileId id);
}
