package com.example.demo01and102.DAO;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import com.example.demo01and102.DTO.ProductDTO;
import com.example.demo01and102.DbHelper.MyDbHelper;

import java.util.ArrayList;

public class ProductDAO {
    private final MyDbHelper dbHelper;
    private final Context context;

    public ProductDAO(Context context) {
        this.context = context;
        dbHelper = new MyDbHelper(context);
    }

    // Thêm sản phẩm
    public int addProduct(ProductDTO product) {
        SQLiteDatabase db = dbHelper.getWritableDatabase(); // Mở CSDL
        ContentValues values = new ContentValues();
        values.put("name", product.getName());
        values.put("price", product.getPrice());
        values.put("id_cat", product.getId_cat());
        long result = db.insert("tb_product", null, values);
        db.close(); // Đóng CSDL ngay sau khi dùng
        return (int) result;
    }

    //Lấy tất cả sản phẩm
    public ArrayList<ProductDTO> getAllProduct() {
        ArrayList<ProductDTO> list = new ArrayList<>();
        SQLiteDatabase db = dbHelper.getReadableDatabase(); // Mở CSDL để đọc
        try {
            Cursor cursor = db.rawQuery("SELECT * FROM tb_product", null);
            if (cursor.moveToFirst()) {
                do {
                    ProductDTO p = new ProductDTO();
                    p.setId(cursor.getInt(0));
                    p.setName(cursor.getString(1));
                    p.setPrice(cursor.getDouble(2));
                    p.setId_cat(cursor.getInt(3));
                    list.add(p);
                } while (cursor.moveToNext());
            } else {
                Log.d("ProductDAO", "Không có dữ liệu trong tb_product");
            }
            cursor.close();
        } catch (Exception e) {
            Log.e("ProductDAO", "Lỗi khi lấy tất cả sản phẩm", e);
        } finally {
            db.close(); // Luôn đóng CSDL dù có lỗi hay không
        }
        return list;
    }

    //Cập nhật sản phẩm
    public boolean updateProduct(ProductDTO product) {
        SQLiteDatabase db = dbHelper.getWritableDatabase(); // Mở CSDL
        ContentValues values = new ContentValues();
        values.put("name", product.getName());
        values.put("price", product.getPrice());
        values.put("id_cat", product.getId_cat());
        String[] params = {String.valueOf(product.getId())};
        int rows = db.update("tb_product", values, "id = ?", params);
        db.close(); // Đóng CSDL
        return rows > 0;
    }

    //Xoá sản phẩm
    public boolean deleteProduct(int id) {
        SQLiteDatabase db = dbHelper.getWritableDatabase(); // Mở CSDL
        String[] params = {String.valueOf(id)};
        int rows = db.delete("tb_product", "id = ?", params);
        db.close(); // Đóng CSDL
        return rows > 0;
    }

    // Các hàm khác như getProductById cũng nên theo cấu trúc tương tự
}