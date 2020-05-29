package com.xh.moudle_bbs.adapter;

import android.app.Activity;
import android.content.Context;
import android.text.TextUtils;
import android.view.View;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.viewholder.BaseViewHolder;
import com.luck.picture.lib.PictureSelector;
import com.luck.picture.lib.entity.LocalMedia;
import com.xh.module.base.Constant;
import com.xh.module.base.entity.bbs.BbsArticle;
import com.xh.module.base.entity.bbs.BbsArticleImage;
import com.xh.module.base.repository.DataRepository;
import com.xh.module.base.utils.PathUtils;
import com.xh.module.base.utils.TimeUtils;
import com.xh.module.base.view.GlideEngine;
import com.xh.moudle_bbs.R;
import com.xh.moudle_bbs.view.MyNineGridImageView;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * 论坛文章适配器
 */
public class ArticleAdapter extends BaseQuickAdapter<BbsArticle, BaseViewHolder> {

    Activity mContext;

    public ArticleAdapter(Activity mContext, @Nullable List<BbsArticle> data) {
        super(R.layout.adapter_article, data);
        this.mContext = mContext;
    }

    @Override
    protected void convert(@NotNull BaseViewHolder holder, BbsArticle item) {
        holder.setText(R.id.contentTv, item.getContent());

        MyNineGridImageView nineGridImageView = holder.findView(R.id.nineGridImageView);
        if (item.getBbsArticleImages().size() > 0) {
            ArticleNineGridImageViewAdapter adapter = new ArticleNineGridImageViewAdapter() {
                @Override
                protected void onItemImageClick(Context context, ImageView imageView, int index, List<BbsArticleImage> list) {
                    List<LocalMedia> mediaList = new ArrayList<>();
                    for (BbsArticleImage bbsArticleImage : list) {
                        LocalMedia localMedia = new LocalMedia();
                        localMedia.setPath(PathUtils.composePath(bbsArticleImage.getAddress()));
                        mediaList.add(localMedia);
                    }

                    PictureSelector.create(mContext)
                            .themeStyle(R.style.picture_default_style)
                            .isNotPreviewDownload(true)
                            .loadImageEngine(GlideEngine.createGlideEngine()) // 请参考Demo GlideEngine.java
                            .openExternalPreview(index, mediaList);
                }
            };
            adapter.setBaseUrl(Constant.SERVER_URL);
            nineGridImageView.setAdapter(adapter);
            nineGridImageView.setVisibility(View.VISIBLE);
            nineGridImageView.setImagesData(item.getBbsArticleImages());

        } else {
            nineGridImageView.setVisibility(View.GONE);
        }

        if (TextUtils.isEmpty(item.getAddress())) {
            holder.setGone(R.id.locationLayout, true);
        } else {
            holder.setVisible(R.id.locationLayout, true);
            holder.setText(R.id.locationTv, item.getAddress());
        }

        long timeStamp = item.getCreateTime() * 1000;
        holder.setText(R.id.timeTv, TimeUtils.showTime(new Date(timeStamp), "MM:dd HH:mm"));

        if (item.getBbsUser() != null) {
            if (item.getBbsUser().getUid().equals(DataRepository.userInfo.getUid())) {
                holder.setVisible(R.id.deleteTv, true);
                holder.setGone(R.id.headIv, true);
                holder.setText(R.id.personTv, "");
            } else {
                holder.setVisible(R.id.headIv, true);
                ImageView headIv = holder.getView(R.id.headIv);
                Glide.with(mContext).load(PathUtils.composePath(item.getBbsUser().getHeadImage())).into(headIv);
                holder.setText(R.id.personTv, item.getBbsUser().getName());
                holder.setGone(R.id.deleteTv, true);
            }
        } else {
            holder.setText(R.id.personTv, "");
        }

    }

}
