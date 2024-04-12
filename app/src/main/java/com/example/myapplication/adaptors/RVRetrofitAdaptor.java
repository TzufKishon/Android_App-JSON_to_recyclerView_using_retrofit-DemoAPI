package com.example.myapplication.adaptors;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import com.bumptech.glide.Glide;
import com.example.myapplication.R;
import com.example.myapplication.networking.ProductResult;
import java.util.List;

public class RVRetrofitAdaptor extends RecyclerView.Adapter<RVRetrofitAdaptor.RVHolderRetrofit> {
    private Context mContext;
    private List<ProductResult> mProductResults;
    private OnItemClickListener mListener;

    public RVRetrofitAdaptor(Context context, List<ProductResult> productResults) {
        this.mContext = context;
        this.mProductResults = productResults;
    }

    public void setOnItemClickListener(OnItemClickListener listener) {
        this.mListener = listener;
    }

    @NonNull
    @Override
    public RVHolderRetrofit onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.rv_product_item, parent, false);
        return new RVHolderRetrofit(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RVHolderRetrofit holder, int position) {
        ProductResult product = mProductResults.get(position);
        holder.tvTitle.setText(product.getTitle());
        Glide.with(mContext)
                .load(product.getProductImage())
                .placeholder(R.drawable.paris)
                .error(R.drawable.paris)
                .into(holder.ivImage);

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mListener != null) {
                    mListener.onItemClick(product.getId());
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return mProductResults.size();
    }

    public static class RVHolderRetrofit extends RecyclerView.ViewHolder {
        TextView tvTitle;
        ImageView ivImage;

        public RVHolderRetrofit(@NonNull View itemView) {
            super(itemView);
            ivImage = itemView.findViewById(R.id.ivImage);
            tvTitle = itemView.findViewById(R.id.tvTitle);
        }
    }

    // Interface for item click
    public interface OnItemClickListener {
        void onItemClick(String productId);
    }
}
