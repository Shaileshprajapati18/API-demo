package com.ios26.apiproject;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import com.bumptech.glide.Glide;
import java.util.List;

public class ComplexAdapter extends RecyclerView.Adapter<ComplexAdapter.ViewHolder> {

    private List<Product> productList;

    public ComplexAdapter(List<Product> productList) {
        this.productList = productList;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_product, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Product product = productList.get(position);
        holder.textTitle.setText(product.getTitle());

        if (product.getTags() != null && !product.getTags().isEmpty()) {
            String allTags = "Tags: " + String.join(", ", product.getTags());
            holder.textTags.setText(allTags);
        } else {
            holder.textTags.setText("No Tags");
        }

        Glide.with(holder.imageView.getContext())
                .load(product.getThumbnail())
                .placeholder(android.R.drawable.ic_menu_gallery)
                .into(holder.imageView);
    }

    @Override
    public int getItemCount() {
        return productList.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        TextView textTitle, textTags;
        ImageView imageView;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            textTitle = itemView.findViewById(R.id.txtTitle);
            textTags = itemView.findViewById(R.id.txtTags);
            imageView = itemView.findViewById(R.id.imgProduct);
        }
    }
}

