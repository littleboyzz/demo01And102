package com.example.demo01and102.Adapter;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;


import com.example.demo01and102.DTO.ProductDTO;
import com.example.demo01and102.R;

import java.util.ArrayList;

public class ProductAdapter extends RecyclerView.Adapter<ProductAdapter.MyViewHolder> {

    Context context;
    ArrayList<ProductDTO> list;

    public ProductAdapter(Context context, ArrayList<ProductDTO> list) {
        this.context = context;
        this.list = list;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = ((Activity) context).getLayoutInflater();
        View v = inflater.inflate(R.layout.item_product, parent, false);
        return new MyViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        ProductDTO p = list.get(position);
        holder.tv_id.setText(String.valueOf(p.getId()));
        holder.tv_name.setText(p.getName());
        holder.tv_price.setText(String.valueOf(p.getPrice()));

        // Xử lý edit / delete nếu muốn
        holder.img_edit.setOnClickListener(v -> {
            // code sửa sản phẩm
        });
        holder.img_delete.setOnClickListener(v -> {
            // code xoá sản phẩm
        });
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder {
        TextView tv_id, tv_name, tv_price;
        ImageView img_edit, img_delete;

        public MyViewHolder(View itemView) {
            super(itemView);
            tv_id = itemView.findViewById(com.example.demo01and102.R.id.tv_id);
            tv_name = itemView.findViewById(R.id.tv_name);
            tv_price = itemView.findViewById(R.id.tv_price);
            img_edit = itemView.findViewById(R.id.img_edit);
            img_delete = itemView.findViewById(R.id.img_delete);
        }
    }
}