package com.example.formula1tracker;

import android.app.Activity;
import android.content.Context;
import android.media.Image;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.lang.invoke.ConstantCallSite;
import java.util.List;

public class CustomerAdapter extends BaseAdapter {

    Context context;
    List<RowItem> rowItems;

    CustomerAdapter(Context context, List<RowItem> rowItems) {
        this.context = context;
        this.rowItems = rowItems;
    }


    @Override
    public int getCount() {
        return rowItems.size();
    }

    @Override
    public Object getItem(int i) {
        return rowItems.get(i);
    }

    @Override
    public long getItemId(int i) {
        return rowItems.indexOf(getItem(i));
    }


    private class ViewHolder {
        ImageView driver_profile_pic;
        TextView driver_name;
        ImageView driver_car_pic;
    }


    @Override
    public View getView(int i, View convert_view, ViewGroup parent) {

        ViewHolder holder = null;
        LayoutInflater mInflater = (LayoutInflater) context.getSystemService(Activity.LAYOUT_INFLATER_SERVICE);
        if (convert_view == null){
            convert_view = mInflater.inflate(R.layout.list_item, null);
            holder = new ViewHolder();

            holder.driver_name = (TextView) convert_view.findViewById(R.id.driver_name);
            holder.driver_profile_pic = (ImageView) convert_view.findViewById(R.id.driver_profile_pic);
            holder.driver_car_pic = (ImageView) convert_view.findViewById(R.id.driver_car);

            RowItem row_pos = rowItems.get(i);

            holder.driver_name.setText(row_pos.getDriver_name());
            holder.driver_profile_pic.setImageResource(row_pos.getDriver_profile_pic());
            holder.driver_car_pic.setImageResource(row_pos.getDriver_car());

        }

        return convert_view;
    }
}
