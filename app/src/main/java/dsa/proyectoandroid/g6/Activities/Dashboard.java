package dsa.proyectoandroid.g6.Activities;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import dsa.proyectoandroid.g6.R;
import dsa.proyectoandroid.g6.models.SavedPreferences;
import dsa.proyectoandroid.g6.models.User;

public class Dashboard extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dashboard);

        ((TextView) findViewById(R.id.userinfotV)).setText("User: " + SavedPreferences.getInstance().getMy_user().getName());
    }

    public void onClickCreators(View v){
        Intent Actv = new Intent(Dashboard.this, InventoryActivity.class);
        startActivity(Actv);
        finish();
    }

    public void onClickShop(View v){
        Intent Actv = new Intent(Dashboard.this, InventoryActivity.class);
        startActivity(Actv);
        finish();
    }

    public void onClickProfile(View v){
        Intent Actv = new Intent(Dashboard.this, ProfileEditActivity.class);
        startActivity(Actv);
        finish();
    }

    public void onClickExit(View v){
        SharedPreferences preferences = getSharedPreferences("Credenciales", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = preferences.edit();
        editor.putString("user_id", null);
        editor.putString("user_name", null);
        editor.putString("user_passwd", null);
        editor.putInt("user_saldo", 0);
        editor.putString("user_perfil", null);
        editor.apply(); // Aplicar cambios

        SavedPreferences.getInstance().setMy_user(new User());

    }

    public void onClickMensajes(View v){
        Intent Actv = new Intent(Dashboard.this, Minimo2David.class);
        startActivity(Actv);
        finish();
    }
}