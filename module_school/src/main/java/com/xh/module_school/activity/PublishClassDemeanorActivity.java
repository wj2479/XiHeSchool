package com.xh.module_school.activity;

import android.os.Bundle;
import android.os.Handler;
import android.text.TextUtils;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.luck.picture.lib.PictureSelector;
import com.luck.picture.lib.config.PictureConfig;
import com.luck.picture.lib.config.PictureMimeType;
import com.luck.picture.lib.entity.LocalMedia;
import com.luck.picture.lib.listener.OnResultCallbackListener;
import com.xh.module.base.BackActivity;
import com.xh.module.base.entity.ClassDemeanor;
import com.xh.module.base.repository.DataRepository;
import com.xh.module.base.repository.impl.SchoolRepository;
import com.xh.module.base.retrofit.IRxJavaCallBack;
import com.xh.module.base.retrofit.ResponseCode;
import com.xh.module.base.retrofit.response.SimpleResponse;
import com.xh.module.base.utils.PathUtils;
import com.xh.module.base.view.GlideEngine;
import com.xh.module_school.R;
import com.xh.module_school.R2;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import jp.wasabeef.richeditor.RichEditor;

/**
 * 发布班级风采
 */
public class PublishClassDemeanorActivity extends BackActivity {
    private final static String TAG = PublishClassDemeanorActivity.class.getSimpleName();

    @BindView(R2.id.editor)
    RichEditor mEditor;
    @BindView(R2.id.controlbar)
    View controlbar;
    @BindView(R2.id.titleEt)
    EditText titleEt;
    @BindView(R2.id.homeIv)
    ImageView homeIv;
    @BindView(R2.id.tipsTv)
    TextView tipsTv;

    /**
     * 首页展示图片地址
     */
    String homeIvPath = null;

    Handler mHandler = new Handler();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_publish_class_demeanor);
        ButterKnife.bind(this);

        mEditor.setPlaceholder("请输入风采内容...");
        mEditor.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if (hasFocus) {
                    controlbar.setVisibility(View.VISIBLE);
                } else {
                    controlbar.setVisibility(View.GONE);
                }
            }
        });
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_publish, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == R.id.publish) {
            publishDemeanor();
        }
        return super.onOptionsItemSelected(item);
    }

    public void onClick(View view) {
        if (view.getId() == R.id.action_undo) {
            mEditor.undo();
        } else if (view.getId() == R.id.action_redo) {
            mEditor.redo();
        } else if (view.getId() == R.id.action_bold) {
            mEditor.setBold();
        } else if (view.getId() == R.id.action_italic) {
            mEditor.setItalic();
        } else if (view.getId() == R.id.action_delete_line) {
            mEditor.setStrikeThrough();
        } else if (view.getId() == R.id.action_underline) {
            mEditor.setUnderline();
        } else if (view.getId() == R.id.action_heading1) {
            mEditor.setHeading(1);
        } else if (view.getId() == R.id.action_heading2) {
            mEditor.setHeading(2);
        } else if (view.getId() == R.id.action_heading3) {
            mEditor.setHeading(3);
        } else if (view.getId() == R.id.action_heading4) {
            mEditor.setHeading(4);
        } else if (view.getId() == R.id.action_heading5) {
            mEditor.setHeading(5);
        } else if (view.getId() == R.id.action_heading6) {
            mEditor.setHeading(6);
        } else if (view.getId() == R.id.action_txt_color) {
        } else if (view.getId() == R.id.action_align_left) {
            mEditor.setAlignLeft();
        } else if (view.getId() == R.id.action_align_center) {
            mEditor.setAlignCenter();
        } else if (view.getId() == R.id.action_align_right) {
            mEditor.setAlignRight();
        } else if (view.getId() == R.id.action_insert_image) {
            PictureSelector.create(this)
                    .openGallery(PictureMimeType.ofImage())
                    .isWeChatStyle(true)// 是否开启微信图片选择风格
                    .selectionMode(PictureConfig.SINGLE)
                    .loadImageEngine(GlideEngine.createGlideEngine())
                    .enableCrop(true)
                    .withAspectRatio(16, 9)
                    .freeStyleCropEnabled(true)
                    .forResult(new OnResultCallbackListener<LocalMedia>() {
                        @Override
                        public void onResult(List<LocalMedia> result) {
                            // onResult Callback
                            List<File> files = new ArrayList<>();
                            for (int i = 0; i < result.size(); i++) {
                                LocalMedia media = result.get(i);
                                File file = new File(media.getCutPath());
                                if (file.exists())
                                    files.add(file);
                            }

                            SchoolRepository.getInstance().uploadClassDemeanorImgs(files, new IRxJavaCallBack<SimpleResponse<List<String>>>() {
                                @Override
                                public void onSuccess(SimpleResponse<List<String>> response) {

                                    if (response.getCode() == ResponseCode.RESULT_OK) {
                                        List<String> pathList = response.getData();
                                        for (int i = 0; i < pathList.size(); i++) {
                                            String path = pathList.get(i);
                                            Log.e("TAG", "上传图片结果:" + PathUtils.composePath(path));

                                            mEditor.insertImage(PathUtils.composePath(path), "\" style=\" width:100%");
                                        }
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
        } else if (view.getId() == R.id.action_insert_link) {

        }
    }

    /**
     * 发表班级风采
     */
    private void publishDemeanor() {
        String title = titleEt.getText().toString().trim();
        if (TextUtils.isEmpty(title)) {
            showInfoDialogAndDismiss("标题不能为空");
            titleEt.requestFocus();
            return;
        }
        if (TextUtils.isEmpty(homeIvPath)) {
            showInfoDialogAndDismiss("展示图片不能为空");
            return;
        }

        String content = mEditor.getHtml();
        if (TextUtils.isEmpty(content)) {
            showInfoDialogAndDismiss("内容不能为空");
            mEditor.requestFocus();
            return;
        }

        showLoadingDialog("正在添加班级风采");

        ClassDemeanor demeanor = new ClassDemeanor();
        demeanor.setContent(mEditor.getHtml());
        demeanor.setCreateUid(DataRepository.userInfo.getUid());
        demeanor.setTitle(title);
        demeanor.setIndexImage(homeIvPath);
        demeanor.setClasId(DataRepository.role.getCla_id());

        SchoolRepository.getInstance().addClassDemeanor(demeanor, new IRxJavaCallBack<SimpleResponse>() {
            @Override
            public void onSuccess(SimpleResponse simpleResponse) {
                dismissDialog();
                if (simpleResponse.getCode() == ResponseCode.RESULT_OK) {
                    showSuccessDialogAndFinish("发布成功");
                } else {
                    showFailDialogAndDismiss("发布失败");
                }
                Log.e("TAG", "上传资讯结果:" + gson.toJson(simpleResponse));
            }

            @Override
            public void onError(Throwable throwable) {
                dismissDialog();
                showFailDialogAndDismiss("发布失败");
                Log.e("TAG", "上传资讯异常:" + throwable.toString());
            }
        });
    }

    /**
     * 上传首页图片
     */
    @OnClick(R2.id.homeLayout)
    void onHomePageClick() {
        PictureSelector.create(this)
                .openGallery(PictureMimeType.ofImage())
                .isWeChatStyle(true)// 是否开启微信图片选择风格
                .selectionMode(PictureConfig.SINGLE)
                .loadImageEngine(GlideEngine.createGlideEngine())
                .enableCrop(true)
                .withAspectRatio(16, 9)
                .freeStyleCropEnabled(false)
                .forResult(new OnResultCallbackListener<LocalMedia>() {
                    @Override
                    public void onResult(List<LocalMedia> result) {
                        // onResult Callback
                        final LocalMedia media = result.get(0);

                        List<File> files = new ArrayList<>();
                        files.add(new File(media.getCutPath()));
                        showLoadingDialog("正在上传图片");
                        SchoolRepository.getInstance().uploadSchoolInfomationImgs(files, new IRxJavaCallBack<SimpleResponse<List<String>>>() {
                            @Override
                            public void onSuccess(SimpleResponse<List<String>> response) {
                                dismissDialog();
                                if (response.getCode() == ResponseCode.RESULT_OK) {
                                    showSuccessDialogAndDismiss("图片上传成功");
                                    List<String> pathList = response.getData();
                                    for (int i = 0; i < pathList.size(); i++) {
                                        homeIvPath = pathList.get(i);
                                        Log.e("TAG", "上传图片结果:" + homeIvPath);
                                        Glide.with(PublishClassDemeanorActivity.this).load(media.getCutPath()).into(homeIv);
                                    }
                                    tipsTv.setVisibility(View.INVISIBLE);
                                }
                            }

                            @Override
                            public void onError(Throwable throwable) {
                                dismissDialog();
                                showFailDialogAndDismiss("图片上传失败");
                            }
                        });

                    }

                    @Override
                    public void onCancel() {
                        // onCancel Callback
                    }
                });

    }

}
