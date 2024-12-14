package dsa.proyectoandroid.g6;
import dsa.proyectoandroid.g6.models.User;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Path;

import java.util.List;

public interface UserService {
    @GET("dsaApp/usuarios")
    Call<List<User>> getUsers();

    @GET("dsaApp/usuarios/{nombre}")
    Call<User> getUserByName(@Path("nombre") String name);

    @POST("dsaApp/usuarios")
    Call<User> createUser(@Body User user);

    @POST("dsaApp/usuarios/login")
    Call<User> login(@Body User credentials);

    @PUT("dsaApp/usuarios/")
    Call<Void> updateUser(@Body User user);
}

