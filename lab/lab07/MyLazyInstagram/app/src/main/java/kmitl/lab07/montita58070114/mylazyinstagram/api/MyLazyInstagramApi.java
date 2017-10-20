package kmitl.lab07.montita58070114.mylazyinstagram.api;


import kmitl.lab07.montita58070114.mylazyinstagram.model.FollowRequest;
import kmitl.lab07.montita58070114.mylazyinstagram.model.FollowResponse;
import kmitl.lab07.montita58070114.mylazyinstagram.model.UserProfile;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Query;

public interface MyLazyInstagramApi {

    String BASE = "https://us-central1-retrofit-course.cloudfunctions.net";

    @GET("/getProfile")
    Call<UserProfile> getProfile(@Query("user") String user);

    @POST("/follow")
    Call<FollowResponse> postFollow(@Body FollowRequest sendFollow) ;


}
