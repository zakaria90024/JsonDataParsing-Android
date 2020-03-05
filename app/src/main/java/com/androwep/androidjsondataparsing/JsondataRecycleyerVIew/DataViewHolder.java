package com.androwep.androidjsondataparsing.JsondataRecycleyerVIew;

import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.androwep.androidjsondataparsing.R;

public class DataViewHolder extends RecyclerView.ViewHolder {
    TextView name, depertment;
    public DataViewHolder(@NonNull View itemView) {
        super(itemView);
        name = itemView.findViewById(R.id.textViewNameId);
        depertment = itemView.findViewById(R.id.textViewDepertmentId);
    }
}
