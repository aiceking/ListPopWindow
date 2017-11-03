package com.android.listpoplibrary.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.android.listpoplibrary.R;
import com.android.listpoplibrary.model.ImageType;
import com.android.listpoplibrary.model.ListPopModel;
import com.bumptech.glide.Glide;

import java.io.File;
import java.util.List;


/**
 * Created by radio on 2017/10/31.
 */

public class PopStringImgesListAdapter extends BaseAdapter {
    private List<ListPopModel> list;
    private Context context;
    public void setNotify(Context context){
        this.context=context;
        notifyDataSetChanged();
    }
    public PopStringImgesListAdapter(List<ListPopModel> list, Context context) {
        this.list = list;
        this.context = context;
    }

    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public Object getItem(int i) {
        return list.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        final ViewHolder viewHolder;
        if (view == null) {
            view = LayoutInflater.from(context).inflate(R.layout.popwindow_listview_imgs_item, viewGroup, false);
            viewHolder = new ViewHolder();
            viewHolder.ivPopwindowListview=(ImageView)view.findViewById(R.id.iv_popwindow_listview);
            viewHolder.tvPopwindowListview=(TextView) view.findViewById(R.id.tv_popwindow_listview);
            view.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) view.getTag();
        }
        viewHolder.tvPopwindowListview.setText(list.get(i).getContent());
        if (list.get(i).getType()== ImageType.Resources){
        viewHolder.ivPopwindowListview.setImageResource(list.get(i).getImageId());
        } else if (list.get(i).getType()== ImageType.Net){
            Glide.with(context).load(list.get(i).getImagePath())
                    .asBitmap()
                    .centerCrop()
                    .placeholder(R.drawable.default_image)
                    .error(R.drawable.default_image)
                    .into(viewHolder.ivPopwindowListview);
        }
        return view;
    }

    static class ViewHolder {
        ImageView ivPopwindowListview;
        TextView tvPopwindowListview;


    }
}
