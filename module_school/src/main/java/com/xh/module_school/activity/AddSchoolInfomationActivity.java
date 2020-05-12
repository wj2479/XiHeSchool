package com.xh.module_school.activity;

import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import com.luck.picture.lib.PictureSelector;
import com.luck.picture.lib.config.PictureMimeType;
import com.luck.picture.lib.entity.LocalMedia;
import com.luck.picture.lib.listener.OnResultCallbackListener;
import com.xh.module.base.BackActivity;
import com.xh.module.base.view.GlideEngine;
import com.xh.module_school.R;
import com.xh.module_school.R2;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import jp.wasabeef.richeditor.RichEditor;

/**
 * 添加学校资讯
 */
public class AddSchoolInfomationActivity extends BackActivity {

    @BindView(R2.id.editor)
    RichEditor mEditor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_school_infomation);

        ButterKnife.bind(this);

        mEditor.setPlaceholder("请输入资讯内容...");
        mEditor.setOnTextChangeListener(new RichEditor.OnTextChangeListener() {
            @Override
            public void onTextChange(String text) {
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_add_school_info, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == R.id.publish) {

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
                    .maxSelectNum(3)
                    .loadImageEngine(GlideEngine.createGlideEngine())
                    .forResult(new OnResultCallbackListener<LocalMedia>() {
                        @Override
                        public void onResult(List<LocalMedia> result) {
                            // onResult Callback

                            Log.e("TAG", "选择图片" + result.size());
                        }

                        @Override
                        public void onCancel() {
                            // onCancel Callback
                        }
                    });
        } else if (view.getId() == R.id.action_insert_link) {

        }
    }
}
