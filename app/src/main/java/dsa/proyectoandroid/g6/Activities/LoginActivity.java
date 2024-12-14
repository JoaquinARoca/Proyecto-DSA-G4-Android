package dsa.proyectoandroid.g6.Activities;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import dsa.proyectoandroid.g6.MainActivity;
import dsa.proyectoandroid.g6.R;
import dsa.proyectoandroid.g6.RetrofitClient;
import dsa.proyectoandroid.g6.UserAdapter;
import dsa.proyectoandroid.g6.UserService;
import dsa.proyectoandroid.g6.models.SavedPreferences;
import dsa.proyectoandroid.g6.models.User;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LoginActivity extends AppCompatActivity {
    private UserAdapter userAdapter;
    private SavedPreferences savedPreferences;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
    }
    public void Register(View v){
        finish();
    }

    public void Login(View v){
        String nombre = ((EditText) findViewById(R.id.usertbx)).getText().toString();
        String password = ((EditText) findViewById(R.id.passwdtbx)).getText().toString();

        User credentials = new User(null, nombre, password); // Enviamos solo nombre y contraseña

        UserService service = RetrofitClient.getRetrofitInstance().create(UserService.class);
        Call<User> call = service.login(credentials);

        call.enqueue(new Callback<User>() {
            @Override
            public void onResponse(Call<User> call, Response<User> response) {
                if (response.isSuccessful()) {
                    // Código 200, iniciar nueva actividad
                    savedPreferences.setMy_user(response.body());
                    Intent intent = new Intent(LoginActivity.this, Dashboard.class);
                    startActivity(intent);
                    finish();
                } else if (response.code() == 401) {
                    // Código 401, credenciales incorrectas
                    Toast.makeText(LoginActivity.this, "Error en nombre o contraseña", Toast.LENGTH_LONG).show();
                } else {
                    // Otros errores
                    Toast.makeText(LoginActivity.this, "Error desconocido: " + response.code(), Toast.LENGTH_LONG).show();
                }
            }

            @Override
            public void onFailure(Call<User> call, Throwable t) {
                Toast.makeText(LoginActivity.this, "Error de conexión: " + t.getMessage(), Toast.LENGTH_LONG).show();
            }
        });
    }

}