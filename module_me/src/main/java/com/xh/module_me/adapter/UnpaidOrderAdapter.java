package com.xh.module_me.adapter;

import android.content.Context;
import android.widget.CheckBox;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.viewholder.BaseViewHolder;
import com.xh.module.base.entity.pay.OrderInfo;
import com.xh.module_me.R;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.List;

public class UnpaidOrderAdapter extends BaseQuickAdapter<OrderInfo, BaseViewHolder> {

    Context mContext;

    public UnpaidOrderAdapter(Context mContext, @Nullable List<OrderInfo> data) {
        super(R.layout.adapter_unpaid_order, data);
        this.mContext = mContext;
    }

    @Override
    protected void convert(@NotNull BaseViewHolder holder, OrderInfo orderInfo) {

        holder.setText(R.id.titleTv, orderInfo.getPayItem().getTitle());
        holder.setText(R.id.contentTv, orderInfo.getPayItem().getContent());
        holder.setText(R.id.amountTv, orderInfo.getAmount().toString());
        CheckBox checkBox = holder.getView(R.id.checkbox);
        checkBox.setChecked(orderInfo.isSelected());
    }

}
