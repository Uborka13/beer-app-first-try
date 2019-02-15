package soft.ubi.hu.daveapp.controllers.network;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.POST;
import soft.ubi.hu.daveapp.controllers.network.dto.registration.RegistrationRequest;
import soft.ubi.hu.daveapp.controllers.network.dto.user.UserListResponse;
import soft.ubi.hu.daveapp.controllers.network.dto.user.UserResponse;

import static soft.ubi.hu.daveapp.controllers.network.Constants.REGISTRATION;
import static soft.ubi.hu.daveapp.controllers.network.Constants.USERS;

public interface EndPoints {


    @POST(REGISTRATION)
    Call<UserResponse> postRegistration(@Body RegistrationRequest registrationRequest);

    @GET(USERS)
    Call<UserListResponse> getAllUser(@Header("Authorization") String auth);
}
