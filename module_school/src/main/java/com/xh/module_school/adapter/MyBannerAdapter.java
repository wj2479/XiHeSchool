package com.xh.module_school.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.xh.module.base.entity.SchoolInformation;
import com.xh.module.base.utils.PathUtils;
import com.xh.module_school.R;
import com.youth.banner.adapter.BannerAdapter;

import java.util.List;

/**
 * 广告轮播图片的适配器
 */
public class MyBannerAdapter extends BannerAdapter<SchoolInformation, MyBannerAdapter.BannerViewHolder> {

    Context mContext;

    public MyBannerAdapter(List<SchoolInformation> datas, Context mContext) {
        super(datas);
        this.mContext = mContext;
    }

    public MyBannerAdapter(List<SchoolInformation> datas) {
        super(datas);
    }

    @Override
    public BannerViewHolder onCreateHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.adapter_banner, null);
        //注意，必须设置为match_parent，这个是viewpager2强制要求的
        view.setLayoutParams(new ViewGroup.LayoutParams(
                ViewGroup.LayoutParams.MATCH_PARENT,
                ViewGroup.LayoutParams.MATCH_PARENT));
        return new BannerViewHolder(view);
    }

    @Override
    public void onBindView(BannerViewHolder holder, SchoolInformation data, int position, int size) {
        holder.tv.setText(data.getTitle());
        Glide.with(mContext).load(PathUtils.composePath(data.getIndexImage())).into(holder.iv);
    }

    class BannerViewHolder extends RecyclerView.ViewHolder {
        ImageView iv;
        TextView tv;

        public BannerViewHolder(@NonNull View view) {
            super(view);
            this.iv = view.findViewById(R.id.imgIv);
            this.tv = view.findViewById(R.id.titleTv);
        }
    }
}
