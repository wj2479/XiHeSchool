package com.xh.module_teach.adapter;

import android.content.Context;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.xh.module.base.entity.VideoBase;
import com.xh.module.base.utils.PathUtils;
import com.youth.banner.adapter.BannerAdapter;

import java.util.List;

/**
 * 推荐点播视频的适配器
 */
public class RecommendVideoBannerAdapter extends BannerAdapter<VideoBase, RecommendVideoBannerAdapter.BannerViewHolder> {

    Context mContext;

    public RecommendVideoBannerAdapter(List<VideoBase> datas, Context mContext) {
        super(datas);
        this.mContext = mContext;
    }

    public RecommendVideoBannerAdapter(List<VideoBase> datas) {
        super(datas);
    }

    @Override
    public BannerViewHolder onCreateHolder(ViewGroup parent, int viewType) {
        ImageView imageView = new ImageView(parent.getContext());
        //注意，必须设置为match_parent，这个是viewpager2强制要求的
        imageView.setLayoutParams(new ViewGroup.LayoutParams(
                ViewGroup.LayoutParams.MATCH_PARENT,
                ViewGroup.LayoutParams.MATCH_PARENT));
        imageView.setScaleType(ImageView.ScaleType.CENTER_CROP);
        return new BannerViewHolder(imageView);
    }

    @Override
    public void onBindView(BannerViewHolder holder, VideoBase data, int position, int size) {
        Glide.with(mContext).load(PathUtils.composePath(data.getIndexImage())).into(holder.imageView);
    }

    class BannerViewHolder extends RecyclerView.ViewHolder {
        ImageView imageView;

        public BannerViewHolder(@NonNull ImageView view) {
            super(view);
            this.imageView = view;
        }
    }
}
