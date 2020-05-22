package com.xh.module_me.activity;

import android.os.Bundle;
import android.text.TextUtils;
import android.widget.EditText;

import com.xh.module.base.BackActivity;
import com.xh.module_me.R;
import com.xh.module_me.R2;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * 意见反馈
 */
public class FeedbackActivity extends BackActivity {

    @BindView(R2.id.edt_content)
    EditText contentEt;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_feedback);

        ButterKnife.bind(this);
    }

    @OnClick(R2.id.submitBut)
    void onSubmit() {
        String content = contentEt.getText().toString().trim();
        if (TextUtils.isEmpty(content)) {
            showInfoDialogAndDismiss("内容不能为空");
            return;
        }

        // TODO: 2020/5/18 0018    功能没有实现
        showSuccessDialog("我们已经收到,感谢您的反馈");

        mHandler.postDelayed(new Runnable() {
            @Override
            public void run() {
                dismissDialog();
                finish();
            }
        }, 2000);
    }

}
