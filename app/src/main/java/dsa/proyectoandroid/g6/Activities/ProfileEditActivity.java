package dsa.proyectoandroid.g6.Activities;

import android.os.Bundle;
import android.widget.EditText;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import dsa.proyectoandroid.g6.R;
import dsa.proyectoandroid.g6.UserAdapter;
import dsa.proyectoandroid.g6.models.SavedPreferences;
import dsa.proyectoandroid.g6.models.User;

public class ProfileEditActivity extends AppCompatActivity {
    private int cont = 0;
    private UserAdapter userAdapter;
    private SavedPreferences savedPreferences;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile_edit);

    }

    private void changesClick(){
        if(cont==0){
            Toast.makeText(ProfileEditActivity.this,
                    "¿Estás seguro de estos cambios? Presiona otra vez si estas de acuerdo",
                    Toast.LENGTH_LONG).show();
            cont++;
        }else {
            
            String nombre = ((EditText) findViewById(R.id.usertbx)).getText().toString();
            String pass1 = ((EditText) findViewById(R.id.passwdtbx)).getText().toString();

            User u = new User(nombre,pass1);

        }
    }
}