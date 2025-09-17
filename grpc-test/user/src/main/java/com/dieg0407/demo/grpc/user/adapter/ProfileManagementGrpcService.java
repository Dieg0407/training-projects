package com.dieg0407.demo.grpc.user.adapter;

import com.dieg0407.demo.grpc.user.application.ProfileManagementUseCase;
import com.dieg0407.demo.grpc.user.contracts.GetUserProfileRequest;
import com.dieg0407.demo.grpc.user.contracts.GetUserProfileResponse;
import com.dieg0407.demo.grpc.user.contracts.UserProfile;
import com.dieg0407.demo.grpc.user.contracts.UserProfileServiceGrpc;
import io.grpc.stub.StreamObserver;
import java.util.UUID;
import org.springframework.stereotype.Service;

@Service
public class ProfileManagementGrpcService extends
    UserProfileServiceGrpc.UserProfileServiceImplBase {

  private final ProfileManagementUseCase profileManagementUseCase;

  public ProfileManagementGrpcService(ProfileManagementUseCase profileManagementUseCase) {
    this.profileManagementUseCase = profileManagementUseCase;
  }

  @Override
  public void getUserProfile(GetUserProfileRequest request,
      StreamObserver<GetUserProfileResponse> responseObserver) {
    final var userId = request.getUserId();

    try {
      final var uuid = UUID.fromString(userId);
      final var userProfileOpt = profileManagementUseCase.findProfileById(uuid);

      if (userProfileOpt.isPresent()) {
        final var userProfile = userProfileOpt.get();

        final var response = GetUserProfileResponse.newBuilder()
            .setProfile(
                UserProfile.newBuilder()
                    .setUserId(userProfile.getId().toString())
                    .setFirstName(userProfile.getFirstName())
                    .setLastName(userProfile.getLastName())
                    .build()
            )
            .build();

        responseObserver.onNext(response);
        responseObserver.onCompleted();
      } else {
        responseObserver.onError(new Exception("User profile not found"));
      }
    } catch (IllegalArgumentException e) {
      responseObserver.onError(new Exception("Invalid UUID format"));
    }
  }
}
