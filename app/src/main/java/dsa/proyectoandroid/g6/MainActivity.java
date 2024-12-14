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

import java.io.IOException;
import java.util.List;

import dsa.proyectoandroid.g6.Activities.LoginActivity;
import dsa.proyectoandroid.g6.Activities.SplashScreen;
import dsa.proyectoandroid.g6.models.User;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {
    private UserAdapter userAdapter;

    @Override
    protected void onResume() {
        super.onResume();
        View rootView = findViewById(R.id.main);
        rootView.setVisibility(View.VISIBLE);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        userAdapter = new UserAdapter(); // Inicializar el adaptador
    }

    public void registerClick(View v) {
        String nombre = ((EditText) findViewById(R.id.usertbx)).getText().toString();
        String pass1 = ((EditText) findViewById(R.id.passwdtbx)).getText().toString();
        String pass2 = ((EditText) findViewById(R.id.passwd2tbx)).getText().toString();

        if (nombre.isEmpty() || pass1.isEmpty() || pass2.isEmpty()) {
            Toast.makeText(this, "Todos los campos son obligatorios", Toast.LENGTH_SHORT).show();
            return;
        }

        if (!pass1.equals(pass2)) {
            Toast.makeText(this, "Las contraseñas no coinciden", Toast.LENGTH_SHORT).show();
            return;
        }

        User nuevoUsuario = new User(null, nombre, pass1);

        // Usar el método del adaptador para crear un usuario
        userAdapter.createUser(nuevoUsuario, new Callback<User>() {
            @Override
            public void onResponse(Call<User> call, Response<User> response) {
                if (response.isSuccessful() && response.body() != null) {
                    Toast.makeText(MainActivity.this,
                            "Usuario registrado: " + response.body().getName(),
                            Toast.LENGTH_LONG).show();
                    clearFields();
                    Intent Actv = new Intent(MainActivity.this, LoginActivity.class);
                    startActivity(Actv);
                    finish();
                } else {
                    String errorMessage = "Error al registrar usuario";
                    if (response.errorBody() != null) {
                        try {
                            errorMessage += ": " + response.errorBody().string();
                        } catch (IOException e) {
                            errorMessage += ": respuesta inesperada";
                        }
                    }
                    Toast.makeText(MainActivity.this, errorMessage, Toast.LENGTH_LONG).show();
                    clearFields();
                }
            }

            @Override
            public void onFailure(Call<User> call, Throwable t) {
                Toast.makeText(MainActivity.this,
                        "Error de conexión: " + t.getMessage(),
                        Toast.LENGTH_LONG).show();
            }
        });
    }

    private void clearFields() {
        ((EditText) findViewById(R.id.usertbx)).setText("");
        ((EditText) findViewById(R.id.passwdtbx)).setText("");
        ((EditText) findViewById(R.id.passwd2tbx)).setText("");
    }

    public void LoginActvClick(View v) {
        Intent Actv = new Intent(this, LoginActivity.class);
        startActivity(Actv);
    }


}