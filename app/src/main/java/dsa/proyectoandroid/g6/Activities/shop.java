package dsa.proyectoandroid.g6.Activities;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import dsa.proyectoandroid.g6.MainActivity;
import dsa.proyectoandroid.g6.ProductAdapter;
import dsa.proyectoandroid.g6.ProductService;
import dsa.proyectoandroid.g6.R;
import dsa.proyectoandroid.g6.RetrofitClient;
import dsa.proyectoandroid.g6.UserAdapter;
import dsa.proyectoandroid.g6.UserService;
import dsa.proyectoandroid.g6.models.Product;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class shop extends AppCompatActivity {
    private RecyclerView recyclerView;
    private ProductAdapter adapter;
    private ProductService productService;
    private ProgressBar progressBar;
    private Handler handler = new Handler();

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shop);

        progressBar = findViewById(R.id.progressBar);
        recyclerView = findViewById(R.id.ShopObjectsRV);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        // Asegurarse de que el RecyclerView esté inicialmente invisible
        recyclerView.setVisibility(View.GONE);

        productService = RetrofitClient.getRetrofitInstance().create(ProductService.class);

        // Simula el progreso inicial hasta el 75%
        simulateInitialProgress();

        // Cargar los objetos de la API
        loadObjects();
    }

    private void simulateInitialProgress() {
        new Thread(() -> {
            for (int progress = 0; progress <= 75; progress++) {
                final int currentProgress = progress;
                handler.post(() -> progressBar.setProgress(currentProgress));
                try {
                    Thread.sleep(50); // Incremento lento
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }).start();
    }

    private void loadObjects() {
        Call<List<Product>> call = productService.getAllProducts();
        call.enqueue(new Callback<List<Product>>() {
            @Override
            public void onResponse(@NonNull Call<List<Product>> call, @NonNull Response<List<Product>> response) {
                if (response.isSuccessful()) {
                    List<Product> productList = response.body();

                    // Configurar el adaptador con los datos recibidos
                    adapter = new ProductAdapter(productList, product -> {
                        // Código para comprar objetos
                    }, shop.this);

                    recyclerView.setAdapter(adapter);

                    // Ocultar ProgressBar y mostrar RecyclerView
                    progressBar.setVisibility(View.GONE);
                    recyclerView.setVisibility(View.VISIBLE);

                } else {
                    Toast.makeText(shop.this, "Error al cargar productos", Toast.LENGTH_LONG).show();
                }
            }

            @Override
            public void onFailure(@NonNull Call<List<Product>> call, @NonNull Throwable throwable) {
                Toast.makeText(shop.this, "Error de red: " + throwable.getMessage(), Toast.LENGTH_LONG).show();
            }
        });
    }

}