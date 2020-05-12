package com.xh.module_school.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.xh.module.base.entity.ImageText;
import com.xh.module_school.R;

import java.util.List;

/**
 * 带图片 + 文字的 适配器
 */
public class ImageTextAdapter extends BaseAdapter {

    Context mContext;
    List<ImageText> imageTextList;

    public ImageTextAdapter(Context mContext, List<ImageText> imageTextList) {
        this.mContext = mContext;
        this.imageTextList = imageTextList;
    }

    @Override
    public int getCount() {
        return imageTextList == null ? 0 : imageTextList.size();
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder viewHolder = null;
        if (convertView == null) {
            convertView = LayoutInflater.from(mContext).inflate(R.layout.gridview_item, null);
            viewHolder = new ViewHolder();
            viewHolder.tv = convertView.findViewById(R.id.text);
            viewHolder.iv = convertView.findViewById(R.id.img);
            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }

        ImageText imageText = imageTextList.get(position);
        viewHolder.tv.setText(imageText.getText());
        Glide.with(mContext).load(imageText.getPath()).into(viewHolder.iv);

        return convertView;
    }

    class ViewHolder {
        TextView tv;
        ImageView iv;
    }
}
