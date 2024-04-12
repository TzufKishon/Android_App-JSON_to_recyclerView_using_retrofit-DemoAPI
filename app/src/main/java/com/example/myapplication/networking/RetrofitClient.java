package com.example.myapplication.networking;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetrofitClient {
    private static RetrofitClient instance = null;
    private final APIs apis;

    private RetrofitClient(){
        Retrofit retrofit = new Retrofit.Builder().baseUrl(APIs.BASE_URL).addConverterFactory(GsonConverterFactory.create()).build();
        apis = retrofit.create(APIs.class);
    }
    public static synchronized RetrofitClient getInstance(){
        if(instance == null)
            instance = new RetrofitClient();
        return instance;
    }
    public APIs getApis(){
        return apis;
    }
    public void fetchProductDetails(String productId, final OnProductDetailsFetchedListener listener) {
        Call<List<ProductResult>> call = apis.getProducts();
        call.enqueue(new Callback<List<ProductResult>>() {
            @Override
            public void onResponse(Call<List<ProductResult>> call, Response<List<ProductResult>> response) {
                if (response.isSuccessful()) {
                    List<ProductResult> productList = response.body();
                    if (productList != null && !productList.isEmpty()) {
                        for (ProductResult product : productList) {
                            if (product.getId().equals(productId)) {
                                String description = product.getDescription();
                                String imageUrl = product.getProductImage();
                                String[] productDetails = new String[]{description, imageUrl};
                                listener.onProductDetailsFetched(productDetails);
                                return;
                            }
                        }
                    }
                }
                // If product details are not found or response is not successful
                listener.onProductDetailsFetchFailed();
            }

            @Override
            public void onFailure(Call<List<ProductResult>> call, Throwable t) {
                // Handle failure case
                listener.onProductDetailsFetchFailed();
            }
        });
    }
    public interface OnProductDetailsFetchedListener {
        void onProductDetailsFetched(String[] productDetails);

        void onProductDetailsFetchFailed();
    }
}
