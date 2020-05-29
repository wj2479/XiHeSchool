package com.xh.module.base.repository.impl;

import com.google.gson.Gson;
import com.xh.module.base.entity.VideoBase;
import com.xh.module.base.repository.ITeachRepository;
import com.xh.module.base.retrofit.ApiManager;
import com.xh.module.base.retrofit.IRxJavaCallBack;
import com.xh.module.base.retrofit.response.SimpleResponse;

import org.json.JSONObject;

import java.util.List;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;
import okhttp3.MediaType;
import okhttp3.RequestBody;

/**
 * 教学模块功能的具体实现
 */
public class TeachRepository implements ITeachRepository {

    private static TeachRepository INSTANCE = null;

    Gson gson = new Gson();

    public static TeachRepository getInstance() {
        if (INSTANCE == null) {
            INSTANCE = new TeachRepository();
        }
        return INSTANCE;
    }

    private TeachRepository() {
    }

    @Override
    public void getRecordVideoList(int schoolType, String gradeName, long courseId, int page, int pageSize, IRxJavaCallBack<SimpleResponse<List<VideoBase>>> callback) {
        ApiManager.getInstance().getSchoolServer().getRecordVideoList(page, pageSize)
                .subscribeOn(Schedulers.io())               //在IO线程进行网络请求
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<SimpleResponse<List<VideoBase>>>() {
                               @Override
                               public void accept(SimpleResponse<List<VideoBase>> simpleResponse) throws Exception {
                                   if (callback != null) {
                                       callback.onSuccess(simpleResponse);
                                   }
                               }
                           }, new Consumer<Throwable>() {
                               @Override
                               public void accept(Throwable throwable) throws Exception {
                                   if (callback != null) {
                                       callback.onError(throwable);
                                   }
                               }
                           }
                );

    }

    @Override
    public void getRecommendRecordVideoList(IRxJavaCallBack<SimpleResponse<List<VideoBase>>> callback) {
        JSONObject json = new JSONObject();

        RequestBody requestBody = RequestBody.create(MediaType.parse("application/json; charset=utf-8"), json.toString());
        ApiManager.getInstance().getSchoolServer().getRecommendRecordVideoList(requestBody)
                .subscribeOn(Schedulers.io())               //在IO线程进行网络请求
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<SimpleResponse<List<VideoBase>>>() {
                               @Override
                               public void accept(SimpleResponse<List<VideoBase>> simpleResponse) throws Exception {
                                   if (callback != null) {
                                       callback.onSuccess(simpleResponse);
                                   }
                               }
                           }, new Consumer<Throwable>() {
                               @Override
                               public void accept(Throwable throwable) throws Exception {
                                   if (callback != null) {
                                       callback.onError(throwable);
                                   }
                               }
                           }
                );
    }
}
