package com.example.myapplication.networking;
import java.util.List;
import retrofit2.http.GET;
import retrofit2.Call;

public interface APIs {
    String BASE_URL = "https://fakestoreapi.com/";
    @GET("products")
    Call<List<ProductResult>> getProducts();
}
