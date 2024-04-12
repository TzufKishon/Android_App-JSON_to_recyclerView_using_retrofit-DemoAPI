package com.example.myapplication;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import com.bumptech.glide.Glide;
import com.example.myapplication.networking.RetrofitClient;

public class DetailActivity extends AppCompatActivity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.dt_product);

        // Retrieve data passed from previous activity
        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            String productId = extras.getString("productId");

            // Display the data as needed in your detail layout
            ImageView imageView = findViewById(R.id.ivImage2);
            TextView descriptionTextView = findViewById(R.id.descriptionTextView);

            // Use RetrofitClient to fetch product details
            RetrofitClient.getInstance().fetchProductDetails(productId, new RetrofitClient.OnProductDetailsFetchedListener() {
                @Override
                public void onProductDetailsFetched(String[] productDetails) {
                    // If product details are fetched successfully
                    String description = productDetails[0];
                    String imageUrl = productDetails[1];

                    // Load image using Glide
                    Glide.with(DetailActivity.this).load(imageUrl).placeholder(R.drawable.paris).into(imageView);

                    descriptionTextView.setText(description);
                }

                @Override
                public void onProductDetailsFetchFailed() {
                    // Handle case when fetching fails
                    Toast.makeText(DetailActivity.this, "Failed to load product details", Toast.LENGTH_SHORT).show();
                }
            });
        } else {
            // Handle case when no data is passed
            Toast.makeText(this, "No data available", Toast.LENGTH_SHORT).show();
        }

        // Find and set click listener for the back button
        findViewById(R.id.records_BTN_back).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Finish the current activity to go back to the previous one
                finish();
            }
        });
    }
}
