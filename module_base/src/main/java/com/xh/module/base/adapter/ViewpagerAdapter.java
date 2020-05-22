package com.xh.module.base.adapter;

import android.view.View;
import android.view.ViewGroup;

import androidx.viewpager.widget.PagerAdapter;

import java.util.List;

/**
 * View的适配器
 */
public class ViewpagerAdapter extends PagerAdapter {
    private List<View> mViewList;

    public ViewpagerAdapter(List<View> mViewList) {
        this.mViewList = mViewList;
    }

    @Override
    public int getCount() {//必须实现
        return mViewList.size();
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {//必须实现
        return view == object;
    }

    @Override
    public Object instantiateItem(ViewGroup container, int position) {//必须实现，实例化
        container.addView(mViewList.get(position));
        return mViewList.get(position);
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {//必须实现，销毁
        container.removeView(mViewList.get(position));
    }
}
