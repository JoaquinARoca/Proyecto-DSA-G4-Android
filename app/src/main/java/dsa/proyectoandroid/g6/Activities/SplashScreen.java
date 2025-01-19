package dsa.proyectoandroid.g6.Activities;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;

import androidx.appcompat.app.AppCompatActivity;

import dsa.proyectoandroid.g6.R;
import dsa.proyectoandroid.g6.models.SavedPreferences;
import dsa.proyectoandroid.g6.models.User;

public class SplashScreen extends AppCompatActivity {
    int counter = 0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_screen);

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                SharedPreferences preferences = getSharedPreferences("Credenciales", Context.MODE_PRIVATE);
                String regUser = preferences.getString("user_name",null);
                if(regUser!=null){
                    SavedPreferences.getInstance().setMy_user(new User(preferences.getString("user_id",null),
                            preferences.getString("user_name",null),preferences.getString("user_passwd",null),
                            preferences.getInt("user_saldo",0),preferences.getString("user_perfil",null)
                    ));
                    Intent intent = new Intent(SplashScreen.this, Dashboard.class);
                    startActivity(intent);
                }else {
                    // Regresa a MainActivity
                    Intent intent = new Intent(SplashScreen.this, MainActivity.class);
                    startActivity(intent);
                }
                finish(); // Cierra SplashScreenActivity
            }
        }, 5000);
    }


}