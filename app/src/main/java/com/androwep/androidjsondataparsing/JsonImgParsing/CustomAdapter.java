package com.androwep.androidjsondataparsing.JsonImgParsing;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.androwep.androidjsondataparsing.R;
import com.nostra13.universalimageloader.core.ImageLoader;

import java.io.InputStream;
import java.util.List;

public class CustomAdapter extends BaseAdapter {
    Context applicationContext;
    int sample;
    List<CarModule> s;
    public CustomAdapter(Context applicationContext, int car_sample, List<CarModule> s) {
        this.applicationContext = applicationContext;
        this.sample = car_sample;
        this.s = s;
    }

    @Override
    public int getCount() {
        return s.size();
    }

    @Override
    public Object getItem(int position) {
        return s.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater layoutInflater;

        if(convertView == null){
            layoutInflater = (LayoutInflater) applicationContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = layoutInflater.inflate(R.layout.car_sample, parent, false);
        }

        TextView t1;

        ImageView imageView;

        t1 = convertView.findViewById(R.id.t1);
        imageView = convertView.findViewById(R.id.imgId);


        t1.setText(s.get(position).getName());

        ImageLoader.getInstance().displayImage(s.get(position).getImg(), imageView);
        //Default options will be used




        return convertView;
    }
}
