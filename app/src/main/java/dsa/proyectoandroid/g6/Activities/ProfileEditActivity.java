package dsa.proyectoandroid.g6.Activities;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import dsa.proyectoandroid.g6.R;
import dsa.proyectoandroid.g6.RetrofitClient;
import dsa.proyectoandroid.g6.AdapterAndService.UserAdapter;
import dsa.proyectoandroid.g6.AdapterAndService.UserService;
import dsa.proyectoandroid.g6.models.SavedPreferences;
import dsa.proyectoandroid.g6.models.User;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ProfileEditActivity extends AppCompatActivity {
    private int cont = 0;
    private UserAdapter userAdapter;
    private Spinner profileSpinner;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile_edit);

        profileSpinner = findViewById(R.id.profileSpinner); // Asegúrate de que este ID coincida con el diseño XML
        ArrayAdapter<String> adapter = new ArrayAdapter<>(
                this,
                android.R.layout.simple_spinner_item,
                new String[]{"Perfil1", "Perfil2"}
        );
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        profileSpinner.setAdapter(adapter);
    }
    private void guardarPreferencias(User user) {
        SharedPreferences preferences = getSharedPreferences("Credenciales", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = preferences.edit();
        editor.putString("user_id", user.getId());
        editor.putString("user_name", user.getName());
        editor.putString("user_passwd", user.getPasswd());
        editor.putInt("user_saldo", user.getSaldo() != null ? user.getSaldo() : 0);
        editor.putString("user_perfil", user.getPerfil());
        editor.apply(); // Aplicar cambios
    }
    public void changesClick(View v){
        if(cont==0){
            Toast.makeText(ProfileEditActivity.this,
                    "¿Estás seguro de estos cambios? Presiona otra vez si estas de acuerdo",
                    Toast.LENGTH_LONG).show();
            cont++;
        }else {
            User currentUser = SavedPreferences.getInstance().getMy_user();
            String id = currentUser.getId();
            String nombre = ((EditText) findViewById(R.id.usertbx)).getText().toString();
            String pass1 = ((EditText) findViewById(R.id.passwdtbx)).getText().toString();
            String perfil = profileSpinner.getSelectedItem().toString();
            Integer Money = SavedPreferences.getInstance().getMy_user().getSaldo();

            User u = new User(id,nombre,pass1,Money,perfil);
            UserService service = RetrofitClient.getRetrofitInstance().create(UserService.class);
            Call<User> call = service.updateUser(id,u);
            call.enqueue(new Callback<User>() {
                @Override
                public void onResponse(Call<User> call, Response<User> response) {
                    if(response.isSuccessful()) {
                        User changedUser = response.body();
                        guardarPreferencias(changedUser);
                        SavedPreferences.getInstance().setMy_user(changedUser);
                        Toast.makeText(ProfileEditActivity.this, "Usuario Actualizado correctamente", Toast.LENGTH_LONG).show();
                        Intent Actv = new Intent(ProfileEditActivity.this, Dashboard.class);
                        startActivity(Actv);
                        finish();
                    } else if (response.code()==404) {
                        Toast.makeText(ProfileEditActivity.this,"Usuario no encontrado",Toast.LENGTH_LONG).show();
                    }else {
                        Toast.makeText(ProfileEditActivity.this,"Error desconocido: " + response.code(),Toast.LENGTH_LONG).show();
                    }
                }

                @Override
                public void onFailure(Call<User> call, Throwable throwable) {
                    Toast.makeText(ProfileEditActivity.this,"Error de conexión: " + throwable.getMessage(), Toast.LENGTH_LONG).show();
                }
            });

        }
    }
}