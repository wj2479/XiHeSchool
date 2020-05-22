package com.xh.module_school.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.xh.module_school.R;
import com.xh.module_school.entity.Image2Text;

import org.jetbrains.annotations.Nullable;

import java.util.List;

/**
 * 一张图片带两个textview的适配器
 */
public class Image2TextAdapter extends BaseAdapter {

    Context mContext;
    List<Image2Text> data;

    public Image2TextAdapter(Context mContext, @Nullable List<Image2Text> data) {
        this.mContext = mContext;
        this.data = data;
    }

    @Override
    public int getCount() {
        return data == null ? 0 : data.size();
    }

    @Override
    public Object getItem(int position) {
        return data.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder viewHolder = null;
        if (convertView == null) {
            convertView = LayoutInflater.from(mContext).inflate(R.layout.adapter_image_2_textview, null);
            viewHolder = new ViewHolder();
            viewHolder.titleTv = convertView.findViewById(R.id.tv_user);
            viewHolder.contentTv = convertView.findViewById(R.id.tv_content);
            viewHolder.iconIv = convertView.findViewById(R.id.iv_icon);
            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }

        Image2Text imageText = data.get(position);
        viewHolder.titleTv.setText(imageText.getTitle());
        viewHolder.contentTv.setText(imageText.getContent());
        Glide.with(mContext).load(imageText.getPath()).into(viewHolder.iconIv);

        return convertView;
    }

    class ViewHolder {
        TextView titleTv, contentTv;
        ImageView iconIv;
    }
}
