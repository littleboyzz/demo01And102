package com.example.demo01and102.DAO;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import com.example.demo01and102.DTO.CatDTO;
import com.example.demo01and102.DbHelper.MyDbHelper;

import java.util.ArrayList;

public class CatDAO {
    MyDbHelper dbHelper;
    SQLiteDatabase db;
    static String TAG = "zzzzzzzzzzzzzz";

    public CatDAO(Context context){
        dbHelper = new MyDbHelper(context);
        db = dbHelper.getWritableDatabase();
    }

    public int addCat (CatDTO catDTO){
        ContentValues values = new ContentValues();
        values.put("name", catDTO.getName());
        return (int) db.insert("tb_cat", null, values);
    }

    public ArrayList<CatDTO> getAllCat(){
        ArrayList<CatDTO> list = new ArrayList<>();
        String sql = "SELECT * FROM tb_cat";
        Cursor cursor = db.rawQuery(sql, null);

        if(cursor != null && cursor.getCount() > 0){
            cursor.moveToFirst();
            do {
                int id = cursor.getInt(0);
                String name = cursor.getString(1);

                CatDTO catDTO = new CatDTO();
                catDTO.setId(id);
                catDTO.setName(name);

                list.add(catDTO);
            } while (cursor.moveToNext());
        } else {
            Log.d(TAG, "getAllCat: Không có dữ liệu");
        }

        if(cursor != null){
            cursor.close();
        }

        return list;
    }

    public CatDTO getCatById(int id) {
        CatDTO catDTO = null;
        String[] params = {String.valueOf(id)};
        String sql = "SELECT * FROM tb_cat WHERE id = ?";
        Cursor cursor = db.rawQuery(sql, params);

        if (cursor != null && cursor.getCount() > 0) {
            cursor.moveToFirst();
            catDTO = new CatDTO();
            catDTO.setId(cursor.getInt(0));
            catDTO.setName(cursor.getString(1));
        }

        if (cursor != null) {
            cursor.close();
        }
        return catDTO;
    }

    public boolean updateRow (CatDTO catDTO) {
        ContentValues values = new ContentValues();
        values.put("name", catDTO.getName());
        String[] params = {String.valueOf(catDTO.getId())};
        return db.update("tb_cat", values, "id = ?", params) > 0;
    }

    public boolean deleteRow (int id){
        String[] params = {String.valueOf(id)};
        return db.delete("tb_cat", "id = ?", params) > 0;
    }
}
