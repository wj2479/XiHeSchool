package com.xh.module.base.view;

import android.graphics.Rect;
import android.view.View;

import androidx.recyclerview.widget.RecyclerView;

/**
 * RecyclerView使用GridLayoutManager间距设置
 */
public class MarginDecoration extends RecyclerView.ItemDecoration {

    private int space;

    public MarginDecoration(int space) {
        this.space = space;
    }

    @Override
    public void getItemOffsets(Rect outRect, View view, RecyclerView parent, RecyclerView.State state) {
        //不是第一个的格子都设一个左边和底部的间距
        outRect.left = space;
        outRect.bottom = space;
    }
}
