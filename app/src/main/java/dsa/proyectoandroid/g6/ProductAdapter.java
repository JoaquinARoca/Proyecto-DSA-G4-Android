package dsa.proyectoandroid.g6;
import dsa.proyectoandroid.g6.models.Product;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ProductAdapter {
    private ProductService productService;

    public ProductAdapter(String baseUrl) {
        this.productService = RetrofitClient.getRetrofitInstance().create(ProductService.class);
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
}
