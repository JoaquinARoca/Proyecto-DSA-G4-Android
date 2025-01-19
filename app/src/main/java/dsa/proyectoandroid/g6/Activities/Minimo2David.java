package dsa.proyectoandroid.g6.Activities;

import android.os.Bundle;
import android.widget.ListView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.util.List;

import dsa.proyectoandroid.g6.AdapterAndService.MessageService;
import dsa.proyectoandroid.g6.AdapterAndService.MessageAdapter;
import dsa.proyectoandroid.g6.R;
import dsa.proyectoandroid.g6.RetrofitClient;
import dsa.proyectoandroid.g6.models.Message;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
public class Minimo2David extends AppCompatActivity {

    private ListView listView;
    private MessageAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_minimo2david);  // Asegúrate de tener este layout correctamente

        listView = findViewById(R.id.listViewMessages);

        // Realiza la solicitud para obtener los mensajes
        fetchMessages();
    }

    private void fetchMessages() {
        // Obtener la instancia de Retrofit y el servicio de la API
        MessageService apiService = RetrofitClient.getRetrofitInstance().create(MessageService.class);

        // Realiza la solicitud GET para obtener los mensajes
        Call<List<Message>> call = apiService.getMessages();

        call.enqueue(new Callback<List<Message>>() {
            @Override
            public void onResponse(Call<List<Message>> call, Response<List<Message>> response) {
                if (response.isSuccessful()) {
                    // Si la respuesta es exitosa, obtener los mensajes
                    List<Message> messages = response.body();

                    // Crear el adaptador y asociarlo al ListView
                    adapter = new MessageAdapter(Minimo2David.this, messages);
                    listView.setAdapter(adapter);
                } else {
                    // Si la respuesta no es exitosa, mostrar el código de error
                    Toast.makeText(Minimo2David.this, "Error en la respuesta: " + response.code(), Toast.LENGTH_LONG).show();
                }
            }

            @Override
            public void onFailure(Call<List<Message>> call, Throwable t) {
                // Si ocurre un error en la solicitud, mostrar un mensaje
                Toast.makeText(Minimo2David.this, "Error al conectar a la API: " + t.getMessage(), Toast.LENGTH_LONG).show();
            }
        });
    }
}

