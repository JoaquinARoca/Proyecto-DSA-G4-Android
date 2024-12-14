package dsa.proyectoandroid.g6;
import android.content.Context;
import android.graphics.Color;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import dsa.proyectoandroid.g6.Activities.shop;
import dsa.proyectoandroid.g6.models.Product;
import dsa.proyectoandroid.g6.models.User;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ProductAdapter extends RecyclerView.Adapter {
    private ProductService productService;
    private List<Product> productList;
    private OnProductClickListener listener;
    private Context context;

    public ProductAdapter(String baseUrl) {
        this.productService = RetrofitClient.getRetrofitInstance().create(ProductService.class);
    }
    public interface OnProductClickListener{
        void onProductClick(Product p);
    }

    public ProductAdapter(List<Product> productList, OnProductClickListener listener, Context context) {
        this.productList = productList;
        this.listener = listener;
        this.context = context;
    }
    @NonNull
    @Override
    public ProductViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        // En lugar de inflar un layout XML, creamos un layout en el código
        LinearLayout layout = new LinearLayout(parent.getContext());
        layout.setOrientation(LinearLayout.VERTICAL);
        layout.setPadding(16, 16, 16, 16);

        // Creamos el TextView para el título
        TextView name = new TextView(parent.getContext());
        name.setTextSize(18);
        name.setTextColor(Color.BLACK);
        layout.addView(name);

        // Creamos el TextView para el cantante
        TextView price = new TextView(parent.getContext());
        price.setTextSize(14);
        price.setTextColor(Color.GRAY);
        layout.addView(price);

        // Ajustamos el layout y retornamos el ViewHolder con las vistas creadas
        return new ProductViewHolder(layout, name, price);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        Product product = productList.get(position);

        // Asignamos el producto y precio a los TextViews creados

//        holder.name.setText(product.getName());
//        holder.singer.setText(product.getPrecio().toString());

        // Manejamos el click en cada item
        holder.itemView.setOnClickListener(v -> listener.onProductClick(product));
    }



    @Override
    public int getItemCount() {
        return productList.size();
    }

    // ViewHolder se encarga de mantener las referencias a las vistas
    public static class ProductViewHolder extends RecyclerView.ViewHolder {
        public TextView name, price;

        public ProductViewHolder(View itemView, TextView name, TextView price) {
            super(itemView);
            this.name = name;
            this.price = price;
        }
    }
    public void getProductById(String id, Callback<Product> callback) {
        Call<Product> call = productService.getProductById(id);
        call.enqueue(callback);
    }

    public void createProduct(Product product, Callback<Product> callback) {
        Call<Product> call = productService.createProduct(product);
        call.enqueue(callback);
    }

    public void getProductPrice(String id, Callback<Double> callback) {
        Call<Double> call = productService.getProductPrice(id);
        call.enqueue(callback);
    }
    public void getAllProducts(Callback<List<Product>> callback){
        Call<List<Product>> call = productService.getAllProducts();
        call.enqueue(callback);
    }
}
