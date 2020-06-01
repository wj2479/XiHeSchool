package com.xh.module_school.adapter;

import android.content.Context;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.chad.library.adapter.base.BaseMultiItemQuickAdapter;
import com.chad.library.adapter.base.viewholder.BaseViewHolder;
import com.xh.module_school.R;
import com.xh.module_school.entity.VideoVoice;

import org.jetbrains.annotations.NotNull;

import java.util.List;

public class HomeWorkMediaAdapter extends BaseMultiItemQuickAdapter<VideoVoice, BaseViewHolder> {

    Context mContext;

    public void setmContext(Context mContext) {
        this.mContext = mContext;
    }

    /**
     * Same as QuickAdapter#QuickAdapter(Context,int) but with
     * some initialization data.
     *
     * @param data A new list is created out of this one to avoid mutable list
     */
    public HomeWorkMediaAdapter(List<VideoVoice> data) {
        super(data);
        addItemType(VideoVoice.TYPE_VIDEO, R.layout.adapter_homework_video);
        addItemType(VideoVoice.TYPE_VOICE, R.layout.adapter_homework_voice);
    }

    @Override
    protected void convert(@NotNull BaseViewHolder holder, VideoVoice videoVoice) {
        int a = (int) (videoVoice.getDuration() / 1000);
        int b = (int) (videoVoice.getDuration() % 1000);

        if (b > 0) {
            a += 1;
        }
        if (videoVoice.getItemType() == VideoVoice.TYPE_VIDEO) {
            ImageView iv = holder.getView(R.id.imageIv);
            Glide.with(mContext).load(videoVoice.getPath()).into(iv);

            holder.setText(R.id.tv_duration, a + "\"");
        } else if (videoVoice.getItemType() == VideoVoice.TYPE_VOICE) {
            holder.setText(R.id.tv_duration, a + "\"");
        }
    }
}
