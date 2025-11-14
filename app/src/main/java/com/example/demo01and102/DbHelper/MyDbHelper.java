package com.example.demo01and102.DbHelper;



import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class MyDbHelper extends SQLiteOpenHelper {

    private static final String DATABASE_NAME = "my_database.db";
    private static final int DATABASE_VERSION = 1;

    public MyDbHelper(@Nullable Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        // 1. Tạo bảng Category
        String sqlCat = "CREATE TABLE tb_cat (id INTEGER NOT NULL PRIMARY KEY AUTOINCREMENT, name TEXT NOT NULL);";
        db.execSQL(sqlCat);

        // 2. Tạo bảng Product
        String sqlProduct = "CREATE TABLE tb_product (id INTEGER NOT NULL PRIMARY KEY AUTOINCREMENT, name TEXT NOT NULL, price REAL NOT NULL DEFAULT 0, id_cat INTEGER, FOREIGN KEY (id_cat) REFERENCES tb_cat (id))";
        db.execSQL(sqlProduct);

        // 3. THÊM DỮ LIỆU MẪU CHO BẢNG CATEGORY
        db.execSQL("INSERT INTO tb_cat(name) VALUES ('Điện thoại');");
        db.execSQL("INSERT INTO tb_cat(name) VALUES ('Laptop');");
        db.execSQL("INSERT INTO tb_cat(name) VALUES ('Phụ kiện');");
        db.execSQL("INSERT INTO tb_cat(name) VALUES ('Đồ gia dụng');");

        // 4. THÊM DỮ LIỆU MẪU CHO BẢNG PRODUCT
        db.execSQL("INSERT INTO tb_product(name, price, id_cat) VALUES ('iPhone 15 Pro', 25000000, 1);");
        db.execSQL("INSERT INTO tb_product(name, price, id_cat) VALUES ('Macbook Air M2', 28000000, 2);");
        db.execSQL("INSERT INTO tb_product(name, price, id_cat) VALUES ('Sạc dự phòng', 500000, 3);");
    }
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        if (oldVersion != newVersion) {
            db.execSQL("DROP TABLE IF EXISTS tb_product");
            db.execSQL("DROP TABLE IF EXISTS tb_cat");
            onCreate(db); // Tạo lại bảng khi nâng cấp
        }
    }
}

