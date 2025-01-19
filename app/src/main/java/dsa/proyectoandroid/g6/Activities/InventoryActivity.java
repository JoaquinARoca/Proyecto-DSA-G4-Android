package dsa.proyectoandroid.g6.Activities;

import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.SimpleAdapter;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import dsa.proyectoandroid.g6.AdapterAndService.ProductAdapter;
import dsa.proyectoandroid.g6.AdapterAndService.UserAdapter;
import dsa.proyectoandroid.g6.R;
import dsa.proyectoandroid.g6.models.Product;
import dsa.proyectoandroid.g6.models.Purchase;
import dsa.proyectoandroid.g6.models.SavedPreferences;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class InventoryActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private UserAdapter userAdapter;
    private ProductAdapter productAdapter;
    private ProgressBar progressBar;

    private List<Product> productList = new ArrayList<>();

    private final int PROGRESS_DELAY_75 = 5000; // 5 segundos para el 75%
    private final int PROGRESS_DELAY_100 = 3000; // 3 segundos para el 100%

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_inventory);

        // Inicializar vistas
        recyclerView = findViewById(R.id.ShopObjectsRV);
        progressBar = findViewById(R.id.progressBar);

        // Configurar RecyclerView
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setVisibility(View.GONE);
        // Simulación de carga del ProgressBar
        simulateProgressBar();

        // Cargar datos de productos
        loadProducts(SavedPreferences.getInstance().getMy_user().getId());
    }

    private void simulateProgressBar() {
        // Simula la carga inicial hasta el 75%
        progressBar.setProgress(0);
        Handler handler = new Handler();

        // Incrementar hasta el 75% y mantener por 5 segundos
        handler.postDelayed(() -> {
            progressBar.setProgress(75);
        }, 2000); // 2 segundos para alcanzar el 75%

        handler.postDelayed(() -> {
            progressBar.setProgress(75); // Mantener en 75% por 5 segundos
        }, 2000 + PROGRESS_DELAY_75);
    }

    private void completeProgress() {
        Handler handler = new Handler();

        // Completar al 100% después de los 5 segundos del 75%
        handler.postDelayed(() -> {
            progressBar.setProgress(100);
        }, PROGRESS_DELAY_75);

        // Ocultar el ProgressBar después de 3 segundos en 100%
        handler.postDelayed(() -> {
            progressBar.setVisibility(View.GONE);
        }, PROGRESS_DELAY_75 + PROGRESS_DELAY_100);

    }



    public void loadProducts(String id) {
        List<Map<String, String>> displayList = new ArrayList<>(); // Lista para mostrar en RecyclerView
        userAdapter = new UserAdapter();
        productAdapter = new ProductAdapter();
        List<Product> products = new LinkedList<Product>();
        productAdapter.getAllProducts(new Callback<List<Product>>() {
            @Override
            public void onResponse(Call<List<Product>> call, Response<List<Product>> response) {
                if(response.body()!=null)
                    products.addAll(response.body());
            }
            @Override
            public void onFailure(Call<List<Product>> call, Throwable throwable) {
                showError("Error en la conexion: "+ throwable.getMessage());
            }
        });

        // Obtener las compras del usuario
        userAdapter.getUserObjects(id, new Callback<List<Purchase>>() {
            @Override
            public void onResponse(Call<List<Purchase>> call, Response<List<Purchase>> response) {
                if (response.isSuccessful() && response.body() != null) {
                    // Mapear compras a productos con detalles completos para mostrar
                    for (Purchase purchase : response.body()) {
                        for (Product product : products) {
                            if (product.getId().equals(purchase.getIdP())) {
                                Map<String, String> item = new HashMap<>();
                                item.put("nombre", product.getNombre());
                                item.put("precio", String.valueOf(product.getPrecio()));
                                item.put("cantidad", String.valueOf(purchase.getCantidad()));
                                displayList.add(item);
                                break;
                            }
                        }
                    }

                    // Configurar adaptador del RecyclerView
                    ProductAdapter adapter = new ProductAdapter(displayList);
                    recyclerView.setAdapter(adapter);

                    // Simular la carga completada del ProgressBar
                    completeProgress();
                    progressBar.setVisibility(View.GONE);
                    recyclerView.setVisibility(View.VISIBLE);
                } else {
                    showError("Error al obtener las compras del usuario.");
                }
            }

            @Override
            public void onFailure(Call<List<Purchase>> call, Throwable t) {
                showError("Fallo en la conexión: " + t.getMessage());
            }
        });
    }

    private void showError(String message) {
        Toast.makeText(InventoryActivity.this, message, Toast.LENGTH_SHORT).show();
        progressBar.setProgress(0); // Reiniciar el progreso en caso de error
    }
}