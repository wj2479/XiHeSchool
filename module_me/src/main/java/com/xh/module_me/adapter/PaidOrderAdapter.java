package com.xh.module_me.adapter;

import android.content.Context;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.viewholder.BaseViewHolder;
import com.xh.module.base.entity.pay.OrderInfo;
import com.xh.module_me.R;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.List;

/**
 * 已支付订单适配器
 */
public class PaidOrderAdapter extends BaseQuickAdapter<OrderInfo, BaseViewHolder> {

    Context mContext;

    public PaidOrderAdapter(Context mContext, @Nullable List<OrderInfo> data) {
        super(R.layout.adapter_paid_order, data);
        this.mContext = mContext;
    }

    @Override
    protected void convert(@NotNull BaseViewHolder holder, OrderInfo orderInfo) {
        holder.setText(R.id.orderIdTv, "订单ID: " + orderInfo.getPayItem().getId().toString());
        holder.setText(R.id.titleTv, orderInfo.getPayItem().getTitle());
        holder.setText(R.id.contentTv, orderInfo.getPayItem().getContent());
        holder.setText(R.id.amountTv, orderInfo.getAmount().toString());
    }
}
