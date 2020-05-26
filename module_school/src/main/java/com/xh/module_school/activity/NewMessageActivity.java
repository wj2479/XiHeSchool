package com.xh.module_school.activity;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.alibaba.android.arouter.launcher.ARouter;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.listener.OnItemClickListener;
import com.xh.module.base.BackActivity;
import com.xh.module.base.entity.ImageText;
import com.xh.module.base.utils.RouteUtils;
import com.xh.module_school.R;
import com.xh.module_school.R2;
import com.xh.module_school.adapter.MsgTypeAdapter;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * 新消息
 */
public class NewMessageActivity extends BackActivity {

    @BindView(R2.id.recyclerView)
    RecyclerView recyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_message);
        ButterKnife.bind(this);

        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        List<ImageText> imageTextList = new ArrayList<>();
        ImageText it1 = new ImageText("系统消息", R.drawable.ic_system);
        ImageText it2 = new ImageText("账号消息", R.drawable.ic_safe);
        ImageText it3 = new ImageText("其他消息", R.drawable.ic_vip);
        imageTextList.add(it1);
        imageTextList.add(it2);
        imageTextList.add(it3);

        MsgTypeAdapter adapter = new MsgTypeAdapter(this, imageTextList);
        adapter.setOnItemClickListener(new OnItemClickListener() {
            @Override
            public void onItemClick(@NonNull BaseQuickAdapter<?, ?> adapter, @NonNull View view, int position) {
                showInfoDialogAndDismiss("暂无新消息");
            }
        });
        recyclerView.setAdapter(adapter);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_msg_setting, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == R.id.msgsetting) {
            ARouter.getInstance().build(RouteUtils.Me_Activity_Msg_Setting).navigation();
        }
        return super.onOptionsItemSelected(item);
    }
}
