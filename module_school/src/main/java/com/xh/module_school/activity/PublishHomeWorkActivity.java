package com.xh.module_school.activity;

import android.Manifest;
import android.content.DialogInterface;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.alibaba.android.arouter.launcher.ARouter;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.listener.OnItemClickListener;
import com.luck.picture.lib.PictureSelector;
import com.luck.picture.lib.config.PictureConfig;
import com.luck.picture.lib.config.PictureMimeType;
import com.luck.picture.lib.entity.LocalMedia;
import com.luck.picture.lib.listener.OnResultCallbackListener;
import com.luck.picture.lib.tools.SdkVersionUtils;
import com.qmuiteam.qmui.skin.QMUISkinManager;
import com.qmuiteam.qmui.widget.dialog.QMUIDialog;
import com.qmuiteam.qmui.widget.dialog.QMUIDialogAction;
import com.tbruyelle.rxpermissions2.RxPermissions;
import com.xh.module.base.BackActivity;
import com.xh.module.base.activity.VideoPlayActivity;
import com.xh.module.base.adapter.NoAddGridImageAdapter;
import com.xh.module.base.entity.Clas;
import com.xh.module.base.entity.Course;
import com.xh.module.base.record.manager.AudioRecordButton;
import com.xh.module.base.repository.DataRepository;
import com.xh.module.base.utils.RouteUtils;
import com.xh.module.base.view.GlideEngine;
import com.xh.module.base.view.MarginDecoration;
import com.xh.module_school.R;
import com.xh.module_school.R2;
import com.xh.module_school.adapter.HomeWorkMediaAdapter;
import com.xh.module_school.entity.VideoVoice;

import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import io.reactivex.functions.Consumer;

/**
 * 发布作业
 */
public class PublishHomeWorkActivity extends BackActivity {

    @BindView(R2.id.addClassIv)
    ImageView addClassIv;
    @BindView(R2.id.courseTv)
    TextView courseTv;
    @BindView(R2.id.classTv)
    TextView classTv;
    @BindView(R2.id.contentEt)
    EditText contentEt;
    @BindView(R2.id.timeTv)
    TextView timeTv;
    @BindView(R2.id.pictureRecycleView)
    RecyclerView pictureRecycleView;
    @BindView(R2.id.mediaRecycleView)
    RecyclerView mediaRecycleView;

    @BindView(R2.id.voiceTv)
    AudioRecordButton voiceTv;

    /**
     * 当前选择的班级
     */
    Course selectCourse;
    /**
     * 选择的班级index数组
     */
    int[] selectCls;

    /**
     * 图片的适配器
     */
    NoAddGridImageAdapter picAdapter;

    /**
     * 音频视频的列表
     */
    List<VideoVoice> mediaList = new ArrayList<>();

    /**
     * 音视频的适配器
     */
    HomeWorkMediaAdapter mediaAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_publish_work);
        ButterKnife.bind(this);

        initView();
        initData();
    }

    private void initView() {
        // 图片的
        pictureRecycleView.setLayoutManager(new GridLayoutManager(this, 3));
        pictureRecycleView.addItemDecoration(new MarginDecoration(8));
        picAdapter = new NoAddGridImageAdapter(this);
        pictureRecycleView.setNestedScrollingEnabled(false);
        pictureRecycleView.setAdapter(picAdapter);

        // 音视频
        mediaRecycleView.setLayoutManager(new LinearLayoutManager(this));
        mediaAdapter = new HomeWorkMediaAdapter(mediaList);
        mediaAdapter.setmContext(this);
        mediaAdapter.setOnItemClickListener(new OnItemClickListener() {
            @Override
            public void onItemClick(@NonNull BaseQuickAdapter<?, ?> adapter, @NonNull View view, int position) {
                VideoVoice videoVoice = mediaList.get(0);
                ARouter.getInstance().build(RouteUtils.Base_Activity_Video_Play)
                        .withString(VideoPlayActivity.PATH, videoVoice.getPath())
                        .withBoolean(VideoPlayActivity.AUTO_PLAY, true)
                        .navigation();
            }
        });
        mediaRecycleView.setNestedScrollingEnabled(false);
        mediaRecycleView.setAdapter(mediaAdapter);

        voiceTv.setHasRecordPromission(false);
    }

    private void initData() {
        if (DataRepository.courseListMap == null) {
            showInfoDialogAndDismiss("没有获取到任课信息");
            return;
        }

        selectCourse = DataRepository.courseListMap.entrySet().iterator().next().getKey();
        courseTv.setText(selectCourse.getCourseName());
        selectCls = new int[]{0};
        setClass();
    }

    private void setClass() {
        List<Clas> clasList = DataRepository.courseListMap.get(selectCourse);
        StringBuilder sb = new StringBuilder();

        for (int i = 0; i < selectCls.length; i++) {
            Clas clas = clasList.get(i);
            sb.append(clas.getGradeName() + " " + clas.getName());
            if (i < selectCls.length - 1) {
                sb.append("\n");
            }
        }

        classTv.setText(sb.toString());
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_publish, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == R.id.publish) {
        }
        return super.onOptionsItemSelected(item);
    }

    @OnClick(R2.id.courseTv)
    void onCourseTvClick() {
        if (DataRepository.courseListMap == null) {
            showInfoDialogAndDismiss("没有获取到任课信息");
            return;
        }

        int checkedIndex = -1;

        int index = 0;
        String[] items = new String[DataRepository.courseListMap.entrySet().size()];
        for (Map.Entry<Course, List<Clas>> entry : DataRepository.courseListMap.entrySet()) {
            if (selectCourse != null && selectCourse.equals(entry.getKey())) {
                checkedIndex = index;
            }
            items[index] = entry.getKey().getCourseName();
            index++;
        }

        new QMUIDialog.CheckableDialogBuilder(this)
                .setTitle("请选择课程")
                .setCheckedIndex(checkedIndex)
                .setSkinManager(QMUISkinManager.defaultInstance(this))
                .addItems(items, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();

                        int index = 0;
                        for (Map.Entry<Course, List<Clas>> entry : DataRepository.courseListMap.entrySet()) {
                            if (index == which) {
                                selectCourse = entry.getKey();
                                courseTv.setText(selectCourse.getCourseName());
                                setClass();
                                break;
                            }
                            index++;
                        }
                    }
                })
                .create(R.style.QMUI_Dialog).show();
    }

    @OnClick(R2.id.classTv)
    void onClassClick() {
        if (selectCourse == null) {
            return;
        }

        List<Clas> clasList = DataRepository.courseListMap.get(selectCourse);
        String[] items = new String[clasList.size()];
        for (int i = 0; i < clasList.size(); i++) {
            items[i] = clasList.get(i).getGradeName() + " " + clasList.get(i).getName();
        }

        final QMUIDialog.MultiCheckableDialogBuilder builder = new QMUIDialog.MultiCheckableDialogBuilder(this)
                .setTitle("请选择班级")
                .setCheckedItems(selectCls)
                .setSkinManager(QMUISkinManager.defaultInstance(this))
                .addItems(items, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                    }
                });
        builder.addAction("取消", new QMUIDialogAction.ActionListener() {
            @Override
            public void onClick(QMUIDialog dialog, int index) {
                dialog.dismiss();
            }
        });
        builder.addAction("确认", new QMUIDialogAction.ActionListener() {
            @Override
            public void onClick(QMUIDialog dialog, int index) {
                int len = builder.getCheckedItemIndexes().length;
                selectCls = new int[len];
                for (int i = 0; i < len; i++) {
                    selectCls[i] = builder.getCheckedItemIndexes()[i];
                }
                setClass();
                dialog.dismiss();
            }
        });
        builder.create(R.style.QMUI_Dialog).show();
    }

    @OnClick(R2.id.picTv)
    void onPicTvClick() {
        PictureSelector.create(this)
                .openGallery(PictureMimeType.ofImage())// 全部.PictureMimeType.ofAll()、图片.ofImage()、视频.ofVideo()、音频.ofAudio()
                .imageEngine(GlideEngine.createGlideEngine())// 外部传入图片加载引擎，必传项
//                        .theme(themeId)// 主题样式设置 具体参考 values/styles   用法：R.style.picture.white.style v2.3.3后 建议使用setPictureStyle()动态方式
                .isWeChatStyle(true)// 是否开启微信图片选择风格
                .isWithVideoImage(false)// 图片和视频是否可以同选,只在ofAll模式下有效
                .isMaxSelectEnabledMask(true)// 选择数到了最大阀值列表是否启用蒙层效果
                //.isAutomaticTitleRecyclerTop(false)// 连续点击标题栏RecyclerView是否自动回到顶部,默认true
                //.loadCacheResourcesCallback(GlideCacheEngine.createCacheEngine())// 获取图片资源缓存，主要是解决华为10部分机型在拷贝文件过多时会出现卡的问题，这里可以判断只在会出现一直转圈问题机型上使用
                //.setOutputCameraPath()// 自定义相机输出目录，只针对Android Q以下，例如 Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DCIM) +  File.separator + "Camera" + File.separator;
                //.setButtonFeatures(CustomCameraView.BUTTON_STATE_BOTH)// 设置自定义相机按钮状态
//                .maxSelectNum(maxSelectNum)// 最大图片选择数量
                .minSelectNum(1)// 最小选择数量
                //.minVideoSelectNum(1)// 视频最小选择数量
                //.closeAndroidQChangeVideoWH(!SdkVersionUtils.checkedAndroid_Q())// 关闭在AndroidQ下获取图片或视频宽高相反自动转换
                .imageSpanCount(4)// 每行显示个数
                .isReturnEmpty(false)// 未选择数据时点击按钮是否可以返回
                .closeAndroidQChangeWH(true)//如果图片有旋转角度则对换宽高,默认为true
                .closeAndroidQChangeVideoWH(!SdkVersionUtils.checkedAndroid_Q())// 如果视频有旋转角度则对换宽高,默认为false
                //.isAndroidQTransform(false)// 是否需要处理Android Q 拷贝至应用沙盒的操作，只针对compress(false); && .isEnableCrop(false);有效,默认处理
                .setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_UNSPECIFIED)// 设置相册Activity方向，不设置默认使用系统
                .isOriginalImageControl(true)// 是否显示原图控制按钮，如果设置为true则用户可以自由选择是否使用原图，压缩、裁剪功能将会失效
                //.bindCustomPlayVideoCallback(new MyVideoSelectedPlayCallback(getContext()))// 自定义视频播放回调控制，用户可以使用自己的视频播放界面
                //.bindCustomCameraInterfaceListener(new MyCustomCameraInterfaceListener())// 提供给用户的一些额外的自定义操作回调
                //.cameraFileName(System.currentTimeMillis() +".jpg")    // 重命名拍照文件名、如果是相册拍照则内部会自动拼上当前时间戳防止重复，注意这个只在使用相机时可以使用，如果使用相机又开启了压缩或裁剪 需要配合压缩和裁剪文件名api
                //.renameCompressFile(System.currentTimeMillis() +".jpg")// 重命名压缩文件名、 如果是多张压缩则内部会自动拼上当前时间戳防止重复
                //.renameCropFileName(System.currentTimeMillis() + ".jpg")// 重命名裁剪文件名、 如果是多张裁剪则内部会自动拼上当前时间戳防止重复
                .selectionMode(PictureConfig.MULTIPLE)// 多选 or 单选
                .isPreviewImage(true)// 是否可预览图片
                .isPreviewVideo(true)// 是否可预览视频
                //.querySpecifiedFormatSuffix(PictureMimeType.ofJPEG())// 查询指定后缀格式资源
                .isCamera(true)// 是否显示拍照按钮
                //.isMultipleSkipCrop(false)// 多图裁剪时是否支持跳过，默认支持
                //.isMultipleRecyclerAnimation(false)// 多图裁剪底部列表显示动画效果
                .isZoomAnim(true)// 图片列表点击 缩放效果 默认true
                //.imageFormat(PictureMimeType.PNG)// 拍照保存图片格式后缀,默认jpeg,Android Q使用PictureMimeType.PNG_Q
                .isEnableCrop(false)// 是否裁剪
                //.basicUCropConfig()//对外提供所有UCropOptions参数配制，但如果PictureSelector原本支持设置的还是会使用原有的设置
                .isCompress(true)// 是否压缩
                //.compressQuality(80)// 图片压缩后输出质量 0~ 100
                .synOrAsy(true)//同步true或异步false 压缩 默认同步
                //.queryMaxFileSize(10)// 只查多少M以内的图片、视频、音频  单位M
                //.compressSavePath(getPath())//压缩图片保存地址
                //.sizeMultiplier(0.5f)// glide 加载图片大小 0~1之间 如设置 .glideOverride()无效 注：已废弃
                //.glideOverride(160, 160)// glide 加载宽高，越小图片列表越流畅，但会影响列表图片浏览的清晰度 注：已废弃
//                        .withAspectRatio(aspect_ratio_x, aspect_ratio_y)// 裁剪比例 如16:9 3:2 3:4 1:1 可自定义
                .hideBottomControls(true)// 是否显示uCrop工具栏，默认不显示
                .isGif(false)// 是否显示gif图片
//                        .freeStyleCropEnabled(cb_styleCrop.isChecked())// 裁剪框是否可拖拽
//                        .circleDimmedLayer(cb_crop_circular.isChecked())// 是否圆形裁剪
                //.setCropDimmedColor(ContextCompat.getColor(getContext(), R.color.app_color_white))// 设置裁剪背景色值
                //.setCircleDimmedBorderColor(ContextCompat.getColor(getApplicationContext(), R.color.app_color_white))// 设置圆形裁剪边框色值
                //.setCircleStrokeWidth(3)// 设置圆形裁剪边框粗细
                .showCropFrame(true)// 是否显示裁剪矩形边框 圆形裁剪时建议设为false
                .showCropGrid(false)// 是否显示裁剪矩形网格 圆形裁剪时建议设为false
                .isOpenClickSound(false)// 是否开启点击声音
                .selectionData(picAdapter.getData())// 是否传入已选图片
                //.isDragFrame(false)// 是否可拖动裁剪框(固定)
                //.videoMinSecond(10)// 查询多少秒以内的视频
                //.videoMaxSecond(15)// 查询多少秒以内的视频
                //.recordVideoSecond(10)//录制视频秒数 默认60s
                //.isPreviewEggs(true)// 预览图片时 是否增强左右滑动图片体验(图片滑动一半即可看到上一张是否选中)
                //.cropCompressQuality(90)// 注：已废弃 改用cutOutQuality()
                .cutOutQuality(90)// 裁剪输出质量 默认100
                .minimumCompressSize(100)// 小于多少kb的图片不压缩
                //.cropWH()// 裁剪宽高比，设置如果大于图片本身宽高则无效
                //.cropImageWideHigh()// 裁剪宽高比，设置如果大于图片本身宽高则无效
                //.rotateEnabled(false) // 裁剪是否可旋转图片
                //.scaleEnabled(false)// 裁剪是否可放大缩小图片
                //.videoQuality()// 视频录制质量 0 or 1
                //.forResult(PictureConfig.CHOOSE_REQUEST);//结果回调onActivityResult code
                .forResult(new MyResultCallback(picAdapter));

    }

    private class MyResultCallback implements OnResultCallbackListener<LocalMedia> {
        private WeakReference<NoAddGridImageAdapter> mAdapterWeakReference;

        public MyResultCallback(NoAddGridImageAdapter adapter) {
            super();
            this.mAdapterWeakReference = new WeakReference<>(adapter);
        }

        @Override
        public void onResult(List<LocalMedia> result) {

            if (mAdapterWeakReference.get() != null) {
                mAdapterWeakReference.get().setList(result);
                mAdapterWeakReference.get().notifyDataSetChanged();
            }
        }

        @Override
        public void onCancel() {

        }
    }

    @OnClick(R2.id.voiceTv)
    void onVoiceTvClick() {
        if (!voiceTv.isHasRecordPromission()) {
            showInfoDialogAndDismiss("长按可录音");
        }
        RxPermissions rxPermissions = new RxPermissions(this);
        rxPermissions.request(Manifest.permission.RECORD_AUDIO, Manifest.permission.WRITE_EXTERNAL_STORAGE)
                .subscribe(new Consumer<Boolean>() {
                    @Override
                    public void accept(Boolean aBoolean) throws Exception {
                        Log.e("TAG", "授权" + aBoolean);
                        if (aBoolean) {
                            voiceTv.setHasRecordPromission(true);
                            voiceTv.setAudioFinishRecorderListener(new AudioRecordButton.AudioFinishRecorderListener() {
                                @Override
                                public void onFinished(float seconds, String filePath) {
                                    Log.e("ATA", "录音文件:" + filePath);

                                    VideoVoice videoVoice = new VideoVoice();
                                    videoVoice.setPath(filePath);
                                    videoVoice.setDuration((long) (seconds * 1000));
                                    videoVoice.setSize(0);
                                    videoVoice.setMimeType(VideoVoice.MIMETYPE_VOICE);
                                    mediaList.add(videoVoice);
                                    mediaAdapter.notifyDataSetChanged();
                                }

                            });
                        } else {
                            voiceTv.setHasRecordPromission(false);
                        }
                    }
                });
    }

    @OnClick(R2.id.videoTv)
    void onVideoTvClick() {
        PictureSelector.create(this)
                .openCamera(PictureMimeType.ofVideo())
                .loadImageEngine(GlideEngine.createGlideEngine())
//                .recordVideoSecond(10)//录制视频秒数 默认60s
                .forResult(new OnResultCallbackListener<LocalMedia>() {
                    @Override
                    public void onResult(List<LocalMedia> result) {
                        // 结果回调
                        for (LocalMedia media : result) {
                            Log.e("TAG", "视频:" + gson.toJson(media));

                            VideoVoice videoVoice = new VideoVoice();
                            videoVoice.setPath(media.getPath());
                            videoVoice.setDuration(media.getDuration());
                            videoVoice.setSize(media.getSize());
                            videoVoice.setMimeType(VideoVoice.MIMETYPE_VIDEO);
                            mediaList.add(videoVoice);
                        }

                        mediaAdapter.notifyDataSetChanged();
                    }

                    @Override
                    public void onCancel() {
                        // 取消
                    }
                });
    }

}
