package com.xh.module_school.activity;

import android.os.Bundle;
import android.os.Handler;
import android.text.TextUtils;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;

import com.xh.module.base.BackActivity;
import com.xh.module.base.entity.SchoolmasterMailbox;
import com.xh.module.base.repository.DataRepository;
import com.xh.module.base.repository.impl.SchoolRepository;
import com.xh.module.base.retrofit.IRxJavaCallBack;
import com.xh.module.base.retrofit.ResponseCode;
import com.xh.module.base.retrofit.response.SimpleResponse;
import com.xh.module_school.R;
import com.xh.module_school.R2;

import butterknife.BindView;
import butterknife.ButterKnife;
import jp.wasabeef.richeditor.RichEditor;

/**
 * 发送信件
 */
public class PublishSchoolMailActivity extends BackActivity {

    @BindView(R2.id.editor)
    RichEditor mEditor;
    @BindView(R2.id.controlbar)
    View controlbar;
    @BindView(R2.id.titleEt)
    EditText titleEt;

    @BindView(R2.id.anonymousCb)
    CheckBox anonymousCb;

    Handler mHandler = new Handler();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_publish_school_mail);

        ButterKnife.bind(this);

        mEditor.setPlaceholder("请输入信件内容...");
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_send, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == R.id.send) {
            publishMail();
        }
        return super.onOptionsItemSelected(item);
    }

    /**
     * 发送校长信件
     */
    private void publishMail() {
        String title = titleEt.getText().toString().trim();
        if (TextUtils.isEmpty(title)) {
            showInfoDialogAndDismiss("标题不能为空");
            titleEt.requestFocus();
            return;
        }

        String content = mEditor.getHtml();
        if (TextUtils.isEmpty(content)) {
            showInfoDialogAndDismiss("内容不能为空");
            mEditor.requestFocus();
            return;
        }

        showLoadingDialog("正在发送信件");
        SchoolmasterMailbox mailbox = new SchoolmasterMailbox();
        mailbox.setContent(content);
        mailbox.setCreateUid(DataRepository.userInfo.getUid());
        mailbox.setTitle(title);
        mailbox.setSchoolId(DataRepository.school.getId());
        if (anonymousCb.isChecked()) {
            mailbox.setAnonymous(1);
        } else {
            mailbox.setAnonymous(0);
        }

        SchoolRepository.getInstance().publishSchoolMasterMail(mailbox, new IRxJavaCallBack<SimpleResponse>() {
            @Override
            public void onSuccess(SimpleResponse simpleResponse) {
                dismissDialog();
                if (simpleResponse.getCode() == ResponseCode.RESULT_OK) {
                    showSuccessDialog("发送成功");
                    mHandler.postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            dismissDialog();
                            finish();
                        }
                    }, 1200);
                } else {
                    showFailDialogAndDismiss("发送失败");
                }
                Log.e("TAG", "上传校长信件结果:" + gson.toJson(simpleResponse));
            }

            @Override
            public void onError(Throwable throwable) {
                dismissDialog();
                showFailDialogAndDismiss("发送失败");
                Log.e("TAG", "上传校长信件异常:" + throwable.toString());
            }
        });

    }


}
