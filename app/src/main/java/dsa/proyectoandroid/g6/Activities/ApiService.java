package dsa.proyectoandroid.g6.Activities;

import dsa.proyectoandroid.g6.models.Message;
import java.util.List;
import retrofit2.Call;
import retrofit2.http.GET;

public interface ApiService {

    // Define la ruta de la API para obtener los mensajes
    @GET("dsaApp/usuarios/posts")  // Asegúrate de que esta ruta sea correcta
    Call<List<Message>> getMessages();
}