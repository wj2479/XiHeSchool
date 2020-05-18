package com.xh.module_school.activity;

import android.content.Intent;
import android.os.Bundle;

import com.qmuiteam.qmui.widget.textview.QMUILinkTextView;
import com.xh.module.base.BackActivity;
import com.xh.module_school.R;
import com.xh.module_school.R2;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * 二维码扫描结果
 */
public class QrcodeResultActivity extends BackActivity {

    public static final String RESULT = "result";

    @BindView(R2.id.resultTv)
    QMUILinkTextView resultTv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_qrcode_result);

        ButterKnife.bind(this);

        resultTv.setOnLinkClickListener(mOnLinkClickListener);
        if (getIntent().hasExtra(RESULT)) {
            String result = getIntent().getStringExtra(RESULT);
            resultTv.setText(result);
        }
    }

    private QMUILinkTextView.OnLinkClickListener mOnLinkClickListener = new QMUILinkTextView.OnLinkClickListener() {
        @Override
        public void onTelLinkClick(String phoneNumber) {
        }

        @Override
        public void onMailLinkClick(String mailAddress) {
        }

        @Override
        public void onWebUrlLinkClick(String url) {
            Intent intent = new Intent("com.xh.school.web");
            intent.putExtra("url", url);
            startActivity(intent);
        }
    };
}
