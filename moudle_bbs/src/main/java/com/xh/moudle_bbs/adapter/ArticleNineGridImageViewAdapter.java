package com.xh.moudle_bbs.adapter;

import android.content.Context;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.jaeger.ninegridimageview.NineGridImageViewAdapter;
import com.xh.module.base.entity.bbs.BbsArticleImage;
import com.xh.module.base.utils.PathUtils;
import com.xh.moudle_bbs.R;

public class ArticleNineGridImageViewAdapter extends NineGridImageViewAdapter<BbsArticleImage> {

    private String baseUrl = "";

    public void setBaseUrl(String baseUrl) {
        this.baseUrl = baseUrl;
    }

    @Override
    protected void onDisplayImage(Context context, ImageView imageView, BbsArticleImage media) {
        RequestOptions requestOptions = new RequestOptions()
                .error(R.drawable.ic_default_pic);

        Glide.with(context)
                .load(PathUtils.composePath(media.getAddress()))
                .apply(requestOptions)
                .into(imageView);
    }

    @Override
    protected ImageView generateImageView(Context context) {
        return super.generateImageView(context);
    }
}
