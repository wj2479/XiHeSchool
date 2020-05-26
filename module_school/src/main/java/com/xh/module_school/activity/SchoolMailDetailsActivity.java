package com.xh.module_school.activity;

import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.widget.EditText;
import android.widget.TextView;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.xh.module.base.BackActivity;
import com.xh.module.base.entity.SchoolmasterMailbox;
import com.xh.module.base.entity.SchoolmasterMailboxReply;
import com.xh.module.base.repository.DataRepository;
import com.xh.module.base.repository.impl.SchoolRepository;
import com.xh.module.base.retrofit.IRxJavaCallBack;
import com.xh.module.base.retrofit.ResponseCode;
import com.xh.module.base.retrofit.response.SimpleResponse;
import com.xh.module.base.utils.TimeUtils;
import com.xh.module_school.R;
import com.xh.module_school.R2;
import com.xh.module_school.adapter.MailboxReplyAdapter;
import com.xh.module_school.entity.MailboxReply;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * 校长信件详情
 */
public class SchoolMailDetailsActivity extends BackActivity {

    public static final String MAILBOX = "mailbox";
    @BindView(R2.id.titleTv)
    TextView titleTv;
    @BindView(R2.id.personTv)
    TextView personTv;
    @BindView(R2.id.timeTv)
    TextView timeTv;
    @BindView(R2.id.contentTv)
    TextView contentTv;
    @BindView(R2.id.recyclerView)
    RecyclerView recyclerView;
    @BindView(R2.id.inputEt)
    EditText inputEt;

    SchoolmasterMailbox mailbox;

    /**
     * 当前那一页
     */
    int page = 1;
    /**
     * 每页显示的数量
     */
    int pageSize = 20;

    /**
     * 记录信箱回复列表
     */
    List<MailboxReply> dataList = new ArrayList<>();
    MailboxReplyAdapter adpter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_school_mail_details);

        ButterKnife.bind(this);

        initData();
        initView();
    }

    private void initData() {
        if (getIntent().hasExtra(MAILBOX)) {
            mailbox = getIntent().getParcelableExtra(MAILBOX);

            titleTv.setText(mailbox.getTitle());
            contentTv.setText(mailbox.getContent());

            if (mailbox.getCreateUid().equals(DataRepository.userInfo.getUid())) {
                personTv.setText("我");
            } else {
                if (mailbox.getAnonymous() == 0) {
                    if (mailbox.getUserBase() != null) {
                        personTv.setText("" + mailbox.getUserBase().getRealName());
                    } else {
                        personTv.setText("未知");
                    }
                } else {
                    personTv.setText("匿名用户");
                }
            }


            long timeStamp = mailbox.getCreateTime() * 1000;
            timeTv.setText(TimeUtils.showTime(new Date(timeStamp), "MM:dd HH:mm"));

            loadReplys();
        } else {
            finish();
        }

    }

    private void initView() {
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        adpter = new MailboxReplyAdapter(dataList);
        adpter.setmContext(this);
        recyclerView.setAdapter(adpter);
    }

    @OnClick(R2.id.sendBut)
    void onSendClick() {
        String content = inputEt.getText().toString().trim();
        if (TextUtils.isEmpty(content)) {
            showInfoDialogAndDismiss("回复内容不能为空");
            return;
        }

        SchoolmasterMailboxReply reply = new SchoolmasterMailboxReply();
        reply.setSchoolmasterMailboxId(mailbox.getId());
        reply.setContent(content);
        reply.setReplyUid(DataRepository.userInfo.getUid());

        showLoadingDialog("正在添加回复");

        SchoolRepository.getInstance().addSchoolMailBoxReply(reply, new IRxJavaCallBack<SimpleResponse>() {
            @Override
            public void onSuccess(SimpleResponse simpleResponse) {
                dismissDialog();
                if (simpleResponse.getCode() == ResponseCode.RESULT_OK) {
                    showSuccessDialogAndDismiss("回复成功");
                    inputEt.setText("");
                    loadReplys();
                } else {
                    showFailDialogAndDismiss("回复失败");
                }
            }

            @Override
            public void onError(Throwable throwable) {
                dismissDialog();
                showFailDialogAndDismiss("回复失败");
            }
        });
    }

    /**
     * 加载回复
     */
    private void loadReplys() {
        SchoolRepository.getInstance().getSchoolMailBoxReplys(mailbox.getId(), 1, pageSize, new IRxJavaCallBack<SimpleResponse<List<SchoolmasterMailboxReply>>>() {
            @Override
            public void onSuccess(SimpleResponse<List<SchoolmasterMailboxReply>> response) {
                if (response.getCode() == ResponseCode.RESULT_OK) {
                    Log.e("TAG", "获取信箱回复:" + gson.toJson(response.getData()));
                    dataList.clear();
                    for (SchoolmasterMailboxReply reply : response.getData()) {
                        dataList.add(new MailboxReply(reply));
                    }

                    adpter.notifyDataSetChanged();
                    page = 1;
                }
            }

            @Override
            public void onError(Throwable throwable) {
                Log.e("TAG", "获取信箱回复:" + throwable.toString());
            }
        });

    }
}
