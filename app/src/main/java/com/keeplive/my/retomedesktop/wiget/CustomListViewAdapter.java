package com.keeplive.my.retomedesktop.wiget;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.keeplive.my.retomedesktop.R;

import java.util.List;
import java.util.Map;

/**
 * Created by scx on 2020/6/20.
 */

public class CustomListViewAdapter extends BaseAdapter {
    private Context context;
    private List<Map<String, Object>> list;
    private int selectItem = -1;

    public CustomListViewAdapter(Context context, List<Map<String, Object>> maps,String str) {
        this.context = context;
        this.list = maps;
    }



    @Override
    public int getCount() {
        // TODO Auto-generated method stub
        return list.size();
    }

    @Override
    public Object getItem(int position) {
        // TODO Auto-generated method stub
        return list==null?0:list.get(position);
    }

    @Override
    public long getItemId(int position) {
        // TODO Auto-generated method stub

        return position;
    }

    public void setSelectItem(int selectItem) {
        this.selectItem = selectItem;
    }

    @SuppressLint("ResourceAsColor")
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        // TODO Auto-generated method stub
        ViewHolder viewHolder ;
        if (convertView == null) {
            viewHolder = new ViewHolder();
            convertView = LayoutInflater.from(context).inflate(R.layout.item_module, null);
            viewHolder.itemTextView =  convertView.findViewById(R.id.itemName);
            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();

        }
        viewHolder.itemTextView.setText(list.get(position).get("itemName").toString());


        if (selectItem == position) {
            viewHolder.itemTextView.setTextColor(Color.parseColor("#0000ff"));
        } else {
            viewHolder.itemTextView.setTextColor(Color.parseColor("#333333"));
        }
        return convertView;
    }

    private class ViewHolder {
        private TextView itemTextView;
    }


}
