package com.xh.module_teach.adapter;

import android.content.Context;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.viewholder.BaseViewHolder;
import com.xh.module.base.entity.VideoBase;
import com.xh.module.base.utils.PathUtils;
import com.xh.module.base.utils.TimeUtils;
import com.xh.module_teach.R;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.Date;
import java.util.List;

/**
 * 点播视频展示
 */
public class RecordVideoAdapter extends BaseQuickAdapter<VideoBase, BaseViewHolder> {

    Context mContext;

    public RecordVideoAdapter(Context mContext, @Nullable List<VideoBase> data) {
        super(R.layout.adapter_record_video_item, data);
        this.mContext = mContext;
    }

    @Override
    protected void convert(@NotNull BaseViewHolder holder, VideoBase videoBase) {
        ImageView thumbnailIv = holder.getView(R.id.thumbnailIv);
        Glide.with(mContext).load(PathUtils.composePath(videoBase.getIndexImage())).into(thumbnailIv);

        holder.setText(R.id.titleTv, videoBase.getTitle());
        holder.setText(R.id.introTv, videoBase.getContent());
        holder.setText(R.id.playSumTv, videoBase.getPlaySum() + "次播放");

        long timeStamp = videoBase.getCreateTime() * 1000;
        holder.setText(R.id.timeTv, TimeUtils.showTime(new Date(timeStamp), "MM月dd日"));
    }
}
