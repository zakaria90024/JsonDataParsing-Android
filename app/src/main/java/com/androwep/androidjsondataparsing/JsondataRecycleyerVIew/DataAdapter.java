package com.androwep.androidjsondataparsing.JsondataRecycleyerVIew;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.androwep.androidjsondataparsing.R;

import java.util.ArrayList;

public class DataAdapter extends RecyclerView.Adapter<DataViewHolder> {
    ArrayList<DataList> list;
    Context context;

    public DataAdapter() {
    }

    public DataAdapter(ArrayList<DataList> list, Context context) {
        this.list = list;
        this.context = context;
    }



    @NonNull
    @Override
    public DataViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        return new DataViewHolder(LayoutInflater.from(context).inflate(R.layout.row_layout,parent,false));
    }

    @Override
    public void onBindViewHolder(@NonNull DataViewHolder holder, int position) {
        DataList curent = list.get(position);
        holder.name.setText(curent.getName());
        holder.depertment.setText(curent.getDepartment());
    }

    @Override
    public int getItemCount() {
        return list.size();
    }
}
