package com.example.myapplication.networking;
import com.google.gson.annotations.SerializedName;
public class ProductResult {
  @SerializedName("id")
  String id;

  @SerializedName("title")
  String title;

  @SerializedName("description")
  String description;

  @SerializedName("image")
  String productImage;

  public String getId() {
    return id;
  }

  public String getTitle() {
    return title;
  }

  public String getProductImage() {
    return productImage;
  }

  public String getDescription() {
    return description;
  }
}
