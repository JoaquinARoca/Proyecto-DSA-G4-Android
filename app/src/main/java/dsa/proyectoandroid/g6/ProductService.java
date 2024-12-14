package dsa.proyectoandroid.g6;
import dsa.proyectoandroid.g6.models.Product;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;

public interface ProductService {
    @POST("/productos")
    Call<Product> createProduct(@Body Product product);

    @GET("/productos/{id}")
    Call<Product> getProductById(@Path("id") String id);

    @GET("/productos/{id}/precio")
    Call<Double> getProductPrice(@Path("id") String id);
}

