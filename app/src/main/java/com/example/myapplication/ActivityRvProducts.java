package com.example.myapplication;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.myapplication.adaptors.RVRetrofitAdaptor;
import com.example.myapplication.networking.ProductResult;
import com.example.myapplication.networking.RetrofitClient;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ActivityRvProducts extends AppCompatActivity implements RVRetrofitAdaptor.OnItemClickListener {
    RecyclerView rvProducts;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rv_prodcts);
        rvProducts = findViewById(R.id.rvProducts);

        getProducts();
    }

    private void getProducts() {
        Call<List<ProductResult>> apiCall = RetrofitClient.getInstance().getApis().getProducts();
        apiCall.enqueue(new Callback<List<ProductResult>>() {
            @Override
            public void onResponse(Call<List<ProductResult>> call, Response<List<ProductResult>> response) {
                List<ProductResult> productResult = response.body();
                Toast.makeText(ActivityRvProducts.this, "Got Products", Toast.LENGTH_SHORT).show();
                setAdapter(productResult);
            }

            @Override
            public void onFailure(Call<List<ProductResult>> call, Throwable t) {
                Toast.makeText(ActivityRvProducts.this, "Error", Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void setAdapter(List<ProductResult> productResults) {
        rvProducts.setLayoutManager(new LinearLayoutManager(this));
        RVRetrofitAdaptor rvRetrofitAdaptor = new RVRetrofitAdaptor(this, productResults);
        rvRetrofitAdaptor.setOnItemClickListener(this); // Set the click listener
        rvProducts.setAdapter(rvRetrofitAdaptor);
    }

    // Implementing onItemClick method of the OnItemClickListener interface
    @Override
    public void onItemClick(String productId) {
        // Handle item click here, start DetailActivity with necessary data
        Intent intent = new Intent(this, DetailActivity.class);
        intent.putExtra("productId", productId);
        startActivity(intent);
    }
}
