package com.dieg0407.demo.grpc.user.infrastructure;

import com.dieg0407.demo.grpc.user.domain.UserProfile;
import com.dieg0407.demo.grpc.user.domain.UserProfileId;
import com.dieg0407.demo.grpc.user.domain.UserProfileRepository;
import java.util.Map;
import java.util.Optional;
import java.util.UUID;
import org.springframework.stereotype.Repository;
import org.springframework.util.Assert;

@Repository
public class InMemoryUserProfileRepository implements UserProfileRepository {

  private final Map<UserProfileId, UserProfile> storage;

  public InMemoryUserProfileRepository() {
    this.storage = new java.util.concurrent.ConcurrentHashMap<>();

    final var user1 = new UserProfile(
        new UserProfileId(UUID.fromString("31db342e-b05d-452a-8888-feb824af1eec")), "Diego",
        "Lopez");
    final var user2 = new UserProfile(
        new UserProfileId(UUID.fromString("8d53bcf1-597c-432e-a9df-cab7757f17a7")), "John", "Doe");
    final var user3 = new UserProfile(
        new UserProfileId(UUID.fromString("2f1e8dde-f758-46f3-8ce4-7cff60c1588f")), "Jane", "Doe");

    this.storage.put(user1.getId(), user1);
    this.storage.put(user2.getId(), user2);
    this.storage.put(user3.getId(), user3);
  }

  @Override
  public Optional<UserProfile> findById(final UserProfileId id) {
    Assert.notNull(id, "UserProfile id must not be null");

    return Optional.ofNullable(this.storage.get(id));
  }
}
