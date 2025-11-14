package com.example.demo01and102.Adapter;



import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;


import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;


import com.example.demo01and102.DAO.CatDAO;
import com.example.demo01and102.DTO.CatDTO;
import com.example.demo01and102.R;

import java.util.ArrayList;


public class CatAdapter
        extends RecyclerView.Adapter<CatAdapter.MyViewHolder> {
    Context context;
    ArrayList<CatDTO> list;
    CatDAO catDao;
    public CatAdapter(Context context, ArrayList<CatDTO> list) {
        this.context = context;
        this.list = list;
        catDao = new CatDAO(context);
    }


    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        //return null;
        LayoutInflater inflater = ((Activity) context).getLayoutInflater();
        View v = inflater.inflate(R.layout.item_category, parent, false);
        return new MyViewHolder(v);
    }


    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        //lấy thông tin của 1 dòng trong list
        int index = position;
        CatDTO catDTO = list.get(position);
        // vắn vào holder
        holder.tv_id.setText(String.valueOf(catDTO.getId()));
        holder.tv_name.setText(catDTO.getName());
        // nút sửa, nút xoá
        holder.img_edit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                updateRow(catDTO, index);

            }
        });
        holder.img_delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //code xử lý khi click vào nút xoá
                deleteRow(catDTO.getId(), index);

            }
        });


    }
    void deleteRow(int id, int index){
        // dialog
        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        builder.setTitle("Xac nhan xoa");
        builder.setMessage("Ban co chac xoa ko ?");
        builder.setPositiveButton("Co", (dialog, which) -> {
            // xoa
            catDao.deleteRow(id);
            list.remove(index);
            notifyDataSetChanged();
            dialog.dismiss();
        });
        builder.setNegativeButton("khong", (dialog, which) -> {
            dialog.dismiss();
        });
        builder.show();
    }
    void updateRow(CatDTO objCat, int index){
        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        LayoutInflater inflater = ((Activity) context).getLayoutInflater();
        View v = inflater.inflate(R.layout.layout_dialog_edit, null);

        builder.setView(v);
        builder.setCancelable(false);
        AlertDialog dialog = builder.create();

        EditText ed_name = v.findViewById(R.id.ed_name);
        ed_name.setText(objCat.getName());
        Button btn_save = v.findViewById(R.id.btn_save);
        btn_save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                objCat.setName(ed_name.getText().toString());
                catDao.updateRow(objCat);
                list.set(index,objCat);
                notifyDataSetChanged();
                dialog.dismiss();

            }
        });
        dialog.show();
    }


    @Override
    public int getItemCount() {
        return list.size();
    }




    public static class MyViewHolder extends RecyclerView.ViewHolder {
        TextView tv_id, tv_name;
        ImageView img_edit, img_delete; // khai báo biến để ánh xạ view 1 dòng
        public MyViewHolder(View itemView) {
            super(itemView);
            tv_id = itemView.findViewById(R.id.tv_id);
            tv_name = itemView.findViewById(R.id.tv_name);
            img_edit = itemView.findViewById(R.id.img_edit);
            img_delete = itemView.findViewById(R.id.img_delete);
        }
    }
}
