package com.androwep.androidjsondataparsing.jsondatalistview;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.androwep.androidjsondataparsing.R;

import java.util.List;

class CustomAdapter extends BaseAdapter {
    Context applicationContext;
    int sample;
    List<DemoStudent> s;
    private LayoutInflater inflater;

    public CustomAdapter(Context applicationContext, int sample, List<DemoStudent> s) {
        this.applicationContext = applicationContext;
        this.sample = sample;
        this.s = s;
    }

    @Override
    public int getCount() {
        return s.size();
    }

    @Override
    public Object getItem(int i) {
        return s.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View view, ViewGroup parent) {

        if(view == null){
            inflater = (LayoutInflater) applicationContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            view = inflater.inflate(R.layout.sample, parent, false);
        }

        TextView name, department, country;

        name = view.findViewById(R.id.name);
        department = view.findViewById(R.id.department);
        country = view.findViewById(R.id.country);

        name.setText(s.get(i).getName());
        department.setText(s.get(i).getDepartment());
        country.setText(s.get(i).getCountry());

        return view;
    }
}
