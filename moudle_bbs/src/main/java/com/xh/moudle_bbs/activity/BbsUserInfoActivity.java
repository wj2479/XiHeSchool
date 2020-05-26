package com.xh.moudle_bbs.activity;

import android.os.Bundle;
import android.text.InputType;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.luck.picture.lib.PictureSelector;
import com.luck.picture.lib.config.PictureConfig;
import com.luck.picture.lib.config.PictureMimeType;
import com.luck.picture.lib.entity.LocalMedia;
import com.luck.picture.lib.listener.OnResultCallbackListener;
import com.qmuiteam.qmui.skin.QMUISkinManager;
import com.qmuiteam.qmui.widget.dialog.QMUIBottomSheet;
import com.qmuiteam.qmui.widget.dialog.QMUIDialog;
import com.qmuiteam.qmui.widget.dialog.QMUIDialogAction;
import com.xh.module.base.BackActivity;
import com.xh.module.base.entity.bbs.BbsUser;
import com.xh.module.base.repository.DataRepository;
import com.xh.module.base.repository.impl.BbsRepository;
import com.xh.module.base.retrofit.IRxJavaCallBack;
import com.xh.module.base.retrofit.ResponseCode;
import com.xh.module.base.retrofit.response.SimpleResponse;
import com.xh.module.base.utils.PathUtils;
import com.xh.module.base.view.GlideEngine;
import com.xh.moudle_bbs.R;
import com.xh.moudle_bbs.R2;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import de.hdodenhof.circleimageview.CircleImageView;

/**
 * 论坛用户中心
 */
public class BbsUserInfoActivity extends BackActivity {

    public static final String USERINFO = "userinfo";

    @BindView(R2.id.photoIv)
    CircleImageView photoIv;
    @BindView(R2.id.nickNameTv)
    TextView nickNameTv;

    BbsUser bbsUser;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bbs_user_info);

        ButterKnife.bind(this);

        initData();
    }

    private void initData() {
        if (getIntent().hasExtra(USERINFO)) {
            bbsUser = getIntent().getParcelableExtra(USERINFO);
            showUserInfo();
        }
    }

    @OnClick(R2.id.photoLayout)
    void onPhotoClick() {
        QMUIBottomSheet.BottomListSheetBuilder builder = new QMUIBottomSheet.BottomListSheetBuilder(this);
        builder.setGravityCenter(false)
                .setSkinManager(QMUISkinManager.defaultInstance(this))
                .setAddCancelBtn(false)
                .setAllowDrag(false)
                .setNeedRightMark(false)
                .setOnSheetItemClickListener(new QMUIBottomSheet.BottomListSheetBuilder.OnSheetItemClickListener() {
                    @Override
                    public void onClick(QMUIBottomSheet dialog, View itemView, int position, String tag) {
                        dialog.dismiss();

                        switch (tag) {
                            case "Look":
                                List<LocalMedia> mediaList = new ArrayList<>();
                                LocalMedia localMedia = new LocalMedia();
                                localMedia.setPath(PathUtils.composePath(bbsUser.getHeadImage()));
                                mediaList.add(localMedia);
                                PictureSelector.create(BbsUserInfoActivity.this)
                                        .themeStyle(R.style.picture_default_style)
                                        .isNotPreviewDownload(true)
                                        .loadImageEngine(GlideEngine.createGlideEngine()) // 请参考Demo GlideEngine.java
                                        .openExternalPreview(0, mediaList);
                                break;
                            case "Update":
                                pictureSelectSingle();
                                break;
                        }
                    }
                });
        if (bbsUser != null && !TextUtils.isEmpty(bbsUser.getHeadImage())) {
            builder.addItem("查看原图", "Look");
        }
        builder.addItem("更换头像", "Update");

        builder.build().show();
    }

    @OnClick(R2.id.nickNameLayout)
    void onNickNameClick() {
        final QMUIDialog.EditTextDialogBuilder builder = new QMUIDialog.EditTextDialogBuilder(this);
        builder.setTitle("设置社区昵称")
                .setSkinManager(QMUISkinManager.defaultInstance(this))
                .setPlaceholder("在此输入您的社区昵称")
                .setDefaultText(bbsUser == null ? "" : bbsUser.getName())
                .setInputType(InputType.TYPE_CLASS_TEXT)
                .addAction("取消", new QMUIDialogAction.ActionListener() {
                    @Override
                    public void onClick(QMUIDialog dialog, int index) {
                        dialog.dismiss();
                    }
                })
                .addAction("确定", new QMUIDialogAction.ActionListener() {
                    @Override
                    public void onClick(QMUIDialog dialog, int index) {
                        String text = builder.getEditText().getText().toString();
                        if (text != null && text.length() > 0) {
                            dialog.dismiss();
                            updateUserInfo(text, null);
                        }
                    }
                }).create(R.style.QMUI_Dialog).show();
    }

    /**
     * 选择图片 并且上传修改头像
     */
    void pictureSelectSingle() {
        PictureSelector.create(this)
                .openGallery(PictureMimeType.ofImage())
                .isWeChatStyle(true)// 是否开启微信图片选择风格
                .selectionMode(PictureConfig.SINGLE)
                .loadImageEngine(GlideEngine.createGlideEngine())
                .enableCrop(true)
                .withAspectRatio(1, 1)
                .freeStyleCropEnabled(false)
                .forResult(new OnResultCallbackListener<LocalMedia>() {
                    @Override
                    public void onResult(List<LocalMedia> result) {
                        // onResult Callback
                        final LocalMedia media = result.get(0);
                        List<File> files = new ArrayList<>();
                        files.add(new File(media.getCutPath()));
                        // 上传用户头像
                        BbsRepository.getInstance().uploadBbsImgs(files, new IRxJavaCallBack<SimpleResponse<List<String>>>() {
                            @Override
                            public void onSuccess(SimpleResponse<List<String>> response) {
                                dismissDialog();
                                if (response.getCode() == ResponseCode.RESULT_OK) {
                                    String path = response.getData().get(0);

                                    Log.e("TAG", "上传图片路径:" + path);
                                    updateUserInfo(null, path);
                                }
                            }

                            @Override
                            public void onError(Throwable throwable) {

                            }
                        });
                    }

                    @Override
                    public void onCancel() {
                        // onCancel Callback
                    }
                });
    }

    @Override
    public void onBackPressed() {
        setResult(RESULT_FIRST_USER);
        super.onBackPressed();
    }

    /**
     * 更新用户信息
     *
     * @param name
     * @param headImage
     */
    private void updateUserInfo(String name, String headImage) {
        if (TextUtils.isEmpty(name) && TextUtils.isEmpty(headImage)) {
            return;
        }

        // 如果还没有用户  就先创建用户
        if (bbsUser == null) {
            bbsUser = new BbsUser();
            bbsUser.setUid(DataRepository.userInfo.getUid());
            bbsUser.setHeadImage(headImage);
            bbsUser.setName(name);
            bbsUser.setState(1);
            BbsRepository.getInstance().addBbsUser(bbsUser, new IRxJavaCallBack<SimpleResponse>() {
                @Override
                public void onSuccess(SimpleResponse simpleResponse) {
                    if (simpleResponse.getCode() == ResponseCode.RESULT_OK) {
                        showUserInfo();
                        DataRepository.bbsUser = bbsUser;
                    }
                }

                @Override
                public void onError(Throwable throwable) {

                }
            });

        } else {
            if (!TextUtils.isEmpty(headImage)) {
                bbsUser.setHeadImage(headImage);
            }
            if (!TextUtils.isEmpty(name)) {
                bbsUser.setName(name);
            }
            // 修改用户信息
            BbsRepository.getInstance().updateUserInfo(bbsUser, new IRxJavaCallBack<SimpleResponse>() {
                @Override
                public void onSuccess(SimpleResponse simpleResponse) {
                    Log.e("TAG", "用户修改:" + gson.toJson(simpleResponse));
                    if (simpleResponse.getCode() == ResponseCode.RESULT_OK) {
                        Log.e("TAG", "用户修改成功:");
                        showUserInfo();
                        DataRepository.bbsUser = bbsUser;
                    }
                }

                @Override
                public void onError(Throwable throwable) {
                    Log.e("TAG", "头像修改异常:" + throwable.toString());
                }
            });
        }
    }

    /**
     * 显示头像
     */
    private void showUserInfo() {
        if (bbsUser != null) {
            if (!TextUtils.isEmpty(bbsUser.getHeadImage())) {
                Glide.with(this).load(PathUtils.composePath(bbsUser.getHeadImage())).into(photoIv);
            }
            if (!TextUtils.isEmpty(bbsUser.getName())) {
                nickNameTv.setText(bbsUser.getName());
            } else {
                showInfoDialogAndDismiss("请先设置您的社区昵称");
            }
        } else {
            showInfoDialogAndDismiss("请先设置您的社区昵称和头像");
        }
    }

}
