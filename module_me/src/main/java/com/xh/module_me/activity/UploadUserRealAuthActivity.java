package com.xh.module_me.activity;

import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;

import com.qmuiteam.qmui.skin.QMUISkinManager;
import com.qmuiteam.qmui.widget.dialog.QMUIDialog;
import com.xh.module.base.BackActivity;
import com.xh.module.base.entity.pay.UserRealauth;
import com.xh.module.base.repository.DataRepository;
import com.xh.module.base.repository.impl.PayRepository;
import com.xh.module.base.retrofit.IRxJavaCallBack;
import com.xh.module.base.retrofit.ResponseCode;
import com.xh.module.base.retrofit.response.SimpleResponse;
import com.xh.module_me.R;
import com.xh.module_me.R2;

import java.util.Calendar;
import java.util.Locale;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * 上传用户实名认证信息
 */
public class UploadUserRealAuthActivity extends BackActivity {

    @BindView(R2.id.nameEt)
    EditText nameEt;
    @BindView(R2.id.cardEt)
    EditText cardEt;
    @BindView(R2.id.genderTv)
    TextView genderTv;
    @BindView(R2.id.dateTv)
    TextView dateTv;

    String[] items = {"男", "女"};

    int gender = -1;

    Calendar calendar = Calendar.getInstance(Locale.CHINA);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_upload_user_real_auth);

        ButterKnife.bind(this);
    }

    @OnClick(R2.id.genderTv)
    void onGenderClick() {
        new QMUIDialog.CheckableDialogBuilder(this)
                .setTitle("请选择性别")
                .setCheckedIndex(gender)
                .setSkinManager(QMUISkinManager.defaultInstance(this))
                .addItems(items, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                        if (which == 0) {
                            gender = 1;
                        } else {
                            gender = 0;
                        }
                        genderTv.setText(items[which]);
                    }
                })
                .create(R.style.QMUI_Dialog).show();
    }

    @OnClick(R2.id.dateTv)
    void onDateClick() {
        // 直接创建一个DatePickerDialog对话框实例，并将它显示出来
        new DatePickerDialog(this, AlertDialog.THEME_HOLO_LIGHT, new DatePickerDialog.OnDateSetListener() {
            // 绑定监听器(How the parent is notified that the date is set.)
            @Override
            public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                // 此处得到选择的时间，可以进行你想要的操作
                String date = String.format("%04d%02d%02d", year, monthOfYear + 1, dayOfMonth);
                dateTv.setText(date);
            }
        }, calendar.get(Calendar.YEAR)   // 设置初始日期
                , calendar.get(Calendar.MONTH)
                , calendar.get(Calendar.DAY_OF_MONTH)).show();

    }

    @OnClick(R2.id.submitBut)
    void onSubmit() {
        String name = nameEt.getText().toString().trim();
        if (TextUtils.isEmpty(name)) {
            showInfoDialogAndDismiss("姓名不能为空");
            return;
        }
        String number = cardEt.getText().toString().trim();
        if (TextUtils.isEmpty(number)) {
            showInfoDialogAndDismiss("身份证号码不能为空");
            return;
        }
        if (number.length() != 18) {
            showInfoDialogAndDismiss("身份证号码长度不合法");
            return;
        }

        UserRealauth realauth = new UserRealauth();
        realauth.setRealName(name);
        realauth.setUid(DataRepository.userInfo.getUid());
        realauth.setIdentityCard(number);
        if (gender != -1) {
            realauth.setGender((byte) gender);
        }
        String date = dateTv.getText().toString();
        if (!TextUtils.isEmpty(date)) {
            realauth.setExpiryDate(Integer.parseInt(date));
        }

        PayRepository.getInstance().requestUserRealAuth(realauth, new IRxJavaCallBack<SimpleResponse>() {
            @Override
            public void onSuccess(SimpleResponse simpleResponse) {
                Log.e("TAG", "实名认证:" + gson.toJson(simpleResponse));
                if (simpleResponse.getCode() == ResponseCode.RESULT_OK) {
                    setResult(RESULT_OK);
                    showSuccessDialogAndFinish("认证提交成功，等待审核");
                } else {
                    showFailDialogAndDismiss("提交失败");
                }
            }

            @Override
            public void onError(Throwable throwable) {
                showFailDialogAndDismiss("提交失败");
                Log.e("TAG", "实名认证异常:" + throwable);
            }
        });
    }
}
