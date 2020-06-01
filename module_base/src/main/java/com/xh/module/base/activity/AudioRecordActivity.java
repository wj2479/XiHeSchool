package com.xh.module.base.activity;

import android.Manifest;
import android.os.Bundle;
import android.util.Log;

import androidx.appcompat.app.AppCompatActivity;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.tbruyelle.rxpermissions2.RxPermissions;
import com.xh.module.R;
import com.xh.module.base.record.manager.AudioRecordButton;
import com.xh.module.base.record.manager.MediaManager;
import com.xh.module.base.utils.RouteUtils;

import io.reactivex.functions.Consumer;

@Route(path = RouteUtils.Base_Activity_Audio_Record)
public class AudioRecordActivity extends AppCompatActivity {

    private AudioRecordButton mEmTvBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_audio_record);
        initView();
        initData();
        initListener();
    }

    private void initView() {
        mEmTvBtn = findViewById(R.id.em_tv_btn);
        //设置不想要可见或者不想被点击
        // mEmTvBtn.setVisibility(View.GONE);//隐藏
        // mEmTvBtn.setCanRecord(false);//重写该方法，设置为不可点击
    }

    private void initData() {
    }

    private void initListener() {
        mEmTvBtn.setHasRecordPromission(false);
        RxPermissions rxPermissions = new RxPermissions(this);
        rxPermissions.request(Manifest.permission.RECORD_AUDIO, Manifest.permission.WRITE_EXTERNAL_STORAGE)
                .subscribe(new Consumer<Boolean>() {
                    @Override
                    public void accept(Boolean aBoolean) throws Exception {
                        Log.e("TAG", "授权" + aBoolean);
                        if (aBoolean) {
                            mEmTvBtn.setHasRecordPromission(true);
                            mEmTvBtn.setAudioFinishRecorderListener((seconds, filePath) -> {
                            });
                        } else {
                            mEmTvBtn.setHasRecordPromission(false);
                        }
                    }
                });
    }

    @Override
    protected void onPause() {
        MediaManager.release();//保证在退出该页面时，终止语音播放
        super.onPause();
    }
}
