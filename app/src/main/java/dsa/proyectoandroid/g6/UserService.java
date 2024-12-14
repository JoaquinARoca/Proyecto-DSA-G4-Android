package dsa.proyectoandroid.g6;
import dsa.proyectoandroid.g6.models.User;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;

import java.util.List;

public interface UserService {
    @GET("/usuarios")
    Call<List<User>> getUsers();

    @GET("/usuarios/{nombre}")
    Call<User> getUserByName(@Path("nombre") String name);

    @POST("/usuarios")
    Call<User> createUser(@Body User user);

    @POST("/usuarios/login")
    Call<Void> login(@Body User credentials);
}

