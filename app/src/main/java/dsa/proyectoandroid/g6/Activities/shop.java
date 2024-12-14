package dsa.proyectoandroid.g6.Activities;

import android.annotation.SuppressLint;
import android.os.Bundle;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

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

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shop);

        recyclerView = findViewById(R.id.ShopObjectsRV);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        productService = RetrofitClient.getRetrofitInstance().create(ProductService.class);

        loadObjects();
    }

    private void loadObjects(){
        Call<List<Product>> call = productService.getAllProducts();
        call.enqueue(new Callback<List<Product>>() {
            @Override
            public void onResponse(Call<List<Product>> call, Response<List<Product>> response) {
                if(response.isSuccessful()){
                    List<Product> productList = response.body();
                    adapter = new ProductAdapter(productList, product -> {
                        //Codigo para comprar objetos
                    }, shop.this);

                    recyclerView.setAdapter(adapter);
                }
            }

            @Override
            public void onFailure(Call<List<Product>> call, Throwable throwable) {

            }
        });
    }

}