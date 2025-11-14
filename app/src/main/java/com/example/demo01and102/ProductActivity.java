package com.example.demo01and102;

import android.os.Bundle;
import android.widget.ListAdapter;
import android.widget.ListView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

//import com.example.demo.Adapter.ProductAdapter;
//import com.example.demo.DAO.ProductDAO;
//import com.example.demo.DTO.ProductDTO;

import com.example.demo01and102.Adapter.ProductAdapter;
import com.example.demo01and102.DAO.ProductDAO;
import com.example.demo01and102.DTO.ProductDTO;

import java.util.ArrayList;

public class ProductActivity extends AppCompatActivity {

    RecyclerView rc_product;
    ArrayList<ProductDTO> list;
    ProductAdapter adapter;
    ProductDAO productDAO;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_product);

        rc_product = findViewById(R.id.rc_product);
        rc_product.setLayoutManager(new LinearLayoutManager(this));

        productDAO = new ProductDAO(this);
        list = productDAO.getAllProduct();

        adapter = new ProductAdapter(this, list);
        rc_product.setAdapter(adapter);
    }
}
