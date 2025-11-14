package com.example.demo01and102;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager; // Import cho RecyclerView
import androidx.recyclerview.widget.RecyclerView;

import com.example.demo01and102.Adapter.CatAdapter;
import com.example.demo01and102.DAO.CatDAO;
import com.example.demo01and102.DTO.CatDTO;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    RecyclerView rc_cat;
    Button btnProduct;

    CatDAO catDAO;
    ArrayList<CatDTO> list;
    CatAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main); // Sử dụng layout của bạn

        // Ánh xạ các View
        rc_cat = findViewById(R.id.rc_cat);
        btnProduct = findViewById(R.id.btnProduct);

        // Thiết lập LayoutManager cho RecyclerView
        rc_cat.setLayoutManager(new LinearLayoutManager(this));

        // Khởi tạo DAO
        catDAO = new CatDAO(this);

        // Lấy toàn bộ danh sách danh mục từ cơ sở dữ liệu
        list = catDAO.getAllCat();

        // Khởi tạo Adapter và gán cho RecyclerView
        adapter = new CatAdapter(this, list);
        rc_cat.setAdapter(adapter);

        // Thiết lập sự kiện cho nút chuyển sang ProductActivity
        btnProduct.setOnClickListener(v -> {
            Intent intent = new Intent(MainActivity.this, ProductActivity.class);
            startActivity(intent);
        });
    }
}