package dsa.proyectoandroid.g6.Activities;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import dsa.proyectoandroid.g6.R;

public class Creators extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_creators);
    }

    public void onClickJR(View v){
        Intent actv = new Intent(this, QuestionActivity.class);
        startActivity(actv);
        finish();
    }

    public void onClickDavid(View v){
        Intent actv = new Intent(this, Minimo2David.class);
        startActivity(actv);
        finish();
    }

    public void onClickAdri(View v){

    }

}