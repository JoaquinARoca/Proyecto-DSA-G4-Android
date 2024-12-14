package dsa.proyectoandroid.g6;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import android.widget.EditText;

import dsa.proyectoandroid.g6.Activities.LoginActivity;
import dsa.proyectoandroid.g6.models.User;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {

    private UserAdapter userAdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void registerClick(View v) {
        String nombre = ((EditText) findViewById(R.id.usertbx)).getText().toString();
        String pass1 = ((EditText) findViewById(R.id.passwdtbx)).getText().toString();
        String pass2 = ((EditText) findViewById(R.id.passwd2tbx)).getText().toString();

        if (pass1.equals(pass2)) {  // Usa equals para comparar valores
            User nuevoUsuario = new User(null, nombre, pass1);

//            // Crea una instancia de UserAdapter
//            UserAdapter userAdapter = new UserAdapter();

            // Llama al método de creación de usuario
            userAdapter.createUser(nuevoUsuario, new Callback<User>() {
                @Override
                public void onResponse(Call<User> call, Response<User> response) {
                    if (response.isSuccessful()) {
                        Toast.makeText(MainActivity.this,
                                "Usuario registrado: " + response.body().getName(),
                                Toast.LENGTH_LONG).show();
                        Intent Actv = new Intent(MainActivity.this, LoginActivity.class);
                        startActivity(Actv);
                    } else {
                        Toast.makeText(MainActivity.this,
                                "Error al registrar usuario: " + response.code(),
                                Toast.LENGTH_LONG).show();
                    }
                }

                @Override
                public void onFailure(Call<User> call, Throwable t) {
                    Toast.makeText(MainActivity.this,
                            "Error de conexión: " + t.getMessage(),
                            Toast.LENGTH_LONG).show();
                }
            });
        } else {
            Toast.makeText(MainActivity.this,
                    "Las contraseñas no coinciden",
                    Toast.LENGTH_SHORT).show();
        }

    }

    public void LoginActvClick(View v){
        Intent Actv = new Intent(this, LoginActivity.class);
        startActivity(Actv);
    }


}