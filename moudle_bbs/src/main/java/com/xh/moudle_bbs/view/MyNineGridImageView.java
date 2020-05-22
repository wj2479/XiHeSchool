package com.xh.moudle_bbs.view;

import android.content.Context;
import android.util.AttributeSet;

import com.jaeger.ninegridimageview.NineGridImageView;
import com.xh.module.base.entity.bbs.BbsArticleImage;

public class MyNineGridImageView extends NineGridImageView<BbsArticleImage> {
    public MyNineGridImageView(Context context) {
        super(context);
    }

    public MyNineGridImageView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }
}
