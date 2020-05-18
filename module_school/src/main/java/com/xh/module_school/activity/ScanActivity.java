package com.xh.module_school.activity;

import android.content.Intent;
import android.hardware.Camera;
import android.os.Bundle;
import android.os.Vibrator;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.google.android.material.tabs.TabLayout;
import com.jaeger.library.StatusBarUtil;
import com.xh.module.base.BackActivity;
import com.xh.module.base.utils.AudioController;
import com.xh.module.base.utils.RouteUtils;
import com.xh.module_school.R;
import com.xh.module_school.R2;

import butterknife.BindView;
import butterknife.ButterKnife;
import cn.bingoogolapple.qrcode.core.QRCodeView;
import cn.bingoogolapple.qrcode.zxing.ZXingView;

/**
 * 扫描条码/二维码   人脸识别的Activity
 */
@Route(path = RouteUtils.School_Activity_Scan)
public class ScanActivity extends BackActivity implements QRCodeView.Delegate {
    private static final String TAG = ScanActivity.class.getSimpleName();

    /**
     * 人脸识别
     */
    public static final int MODE_FACE = 666;
    /**
     * 二维码识别
     */
    public static final int MODE_QRCODE = 777;

    /**
     * 默认的识别模式
     */
    private int mode = MODE_QRCODE;

    private int mCameraFacing = Camera.CameraInfo.CAMERA_FACING_BACK;

    @BindView(R2.id.zxingview)
    ZXingView mZXingView;

    @BindView(R2.id.flashIv)
    ImageView flashIv;

    @BindView(R2.id.cameraChangeIv)
    ImageView cameraChangeIv;

    @BindView(R2.id.tabLayout)
    TabLayout tabLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_scan);
//        setSupportActionBar((Toolbar) findViewById(R.id.toolbar));

        ButterKnife.bind(this);

        mZXingView.setDelegate(this);

        flashIv.setTag(false);
        flashIv.setImageResource(R.drawable.ic_flash_light_close);

        flashIv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (v.getTag().equals(true)) {
                    mZXingView.closeFlashlight();
                    flashIv.setTag(false);
                    flashIv.setImageResource(R.drawable.ic_flash_light_close);
                } else {
                    mZXingView.openFlashlight();
                    flashIv.setTag(true);
                    flashIv.setImageResource(R.drawable.ic_flash_light_open);
                }
            }
        });

        cameraChangeIv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mZXingView.stopCamera();
                if (mCameraFacing == Camera.CameraInfo.CAMERA_FACING_BACK) {
                    setCameraFacing(Camera.CameraInfo.CAMERA_FACING_FRONT);
                } else {
                    setCameraFacing(Camera.CameraInfo.CAMERA_FACING_BACK);
                }
                setMode(mode);
            }
        });

        tabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                if (tab.getText().equals("二维码")) {
                    setMode(MODE_QRCODE);
                } else if (tab.getText().equals("人脸")) {
                    setMode(MODE_FACE);
                }
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });
    }

    /**
     * 设置识别模式
     *
     * @param mode
     */
    private void setMode(int mode) {
        this.mode = mode;
        if (mode == MODE_QRCODE) {
            mZXingView.startSpotAndShowRect(); // 显示扫描框，并开始识别
        } else if (mode == MODE_FACE) {
            mZXingView.stopSpotAndHiddenRect();
        }
    }

    /**
     * 设置前置或者后置摄像头
     *
     * @param cameraFacing
     */
    private void setCameraFacing(int cameraFacing) {
        this.mCameraFacing = cameraFacing;
        mZXingView.startCamera(cameraFacing); // 打开后置摄像头开始预览，
    }

    @Override
    protected void onStart() {
        super.onStart();
        mZXingView.startCamera(mCameraFacing); // 打开后置摄像头开始预览，但是并未开始识别
        setMode(mode);
    }

    @Override
    protected void onStop() {
        mZXingView.stopCamera(); // 关闭摄像头预览，并且隐藏扫描框
        super.onStop();
    }

    @Override
    protected void onDestroy() {
        mZXingView.onDestroy(); // 销毁二维码扫描控件
        super.onDestroy();
    }

    private void vibrate() {
        Vibrator vibrator = (Vibrator) getSystemService(VIBRATOR_SERVICE);
        vibrator.vibrate(200);
    }

    @Override
    public void onScanQRCodeSuccess(String result) {
        // 快门声
        AudioController.newInstance(this).playRaw(R.raw.shutter);

        Intent intent = new Intent(this, QrcodeResultActivity.class);
        intent.putExtra(QrcodeResultActivity.RESULT, result);
//        setResult(RESULT_OK, intent);
        startActivity(intent);
        finish();
    }

    @Override
    public void onCameraAmbientBrightnessChanged(boolean isDark) {
        // 这里是通过修改提示文案来展示环境是否过暗的状态，接入方也可以根据 isDark 的值来实现其他交互效果
        String tipText = mZXingView.getScanBoxView().getTipText();
        String ambientBrightnessTip = "\n环境过暗，请打开闪光灯";
        if (isDark) {
            if (!tipText.contains(ambientBrightnessTip)) {
                mZXingView.getScanBoxView().setTipText(tipText + ambientBrightnessTip);
            }
        } else {
            if (tipText.contains(ambientBrightnessTip)) {
                tipText = tipText.substring(0, tipText.indexOf(ambientBrightnessTip));
                mZXingView.getScanBoxView().setTipText(tipText);
            }
        }
    }

    @Override
    public void onScanQRCodeOpenCameraError() {
        Log.e(TAG, "打开相机出错");
    }

    @Override
    public boolean onPreviewFrame(byte[] data, Camera camera) {
        if (mode == MODE_FACE) {

            return true;
        }
        return false;
    }

    public void onClick(View v) {
        int id = v.getId();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        mZXingView.startSpotAndShowRect(); // 显示扫描框，并开始识别
    }


    @Override
    protected void setStatusBar() {
        StatusBarUtil.setTransparent(this);
    }
}
