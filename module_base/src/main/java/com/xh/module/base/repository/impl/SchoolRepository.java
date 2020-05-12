package com.xh.module.base.repository.impl;

import com.xh.module.base.entity.School;
import com.xh.module.base.entity.SchoolInformation;
import com.xh.module.base.repository.ISchoolRepository;
import com.xh.module.base.retrofit.ApiManager;
import com.xh.module.base.retrofit.IRxJavaCallBack;
import com.xh.module.base.retrofit.response.SimpleResponse;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.List;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;
import okhttp3.MediaType;
import okhttp3.RequestBody;

public class SchoolRepository implements ISchoolRepository {

    private static SchoolRepository INSTANCE = null;

    public static SchoolRepository getInstance() {
        if (INSTANCE == null) {
            INSTANCE = new SchoolRepository();
        }
        return INSTANCE;
    }

    @Override
    public void getSchoolInfoById(long schoolId, IRxJavaCallBack<SimpleResponse<School>> callback) {
        JSONObject params = new JSONObject();
        try {
            params.put("id", schoolId);
        } catch (JSONException e) {
            e.printStackTrace();
        }

        RequestBody requestBody = RequestBody.create(MediaType.parse("application/json; charset=utf-8"), params.toString());

        ApiManager.getInstance().getSchoolServer().getschoolInfoById(requestBody)
                .subscribeOn(Schedulers.io())               //在IO线程进行网络请求
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<SimpleResponse<School>>() {
                               @Override
                               public void accept(SimpleResponse<School> simpleResponse) throws Exception {
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
    public void getSchoolInfomationById(long schoolId, IRxJavaCallBack<SimpleResponse<List<SchoolInformation>>> callback) {
        ApiManager.getInstance().getSchoolServer().getschoolInfomationById(schoolId)
                .subscribeOn(Schedulers.io())               //在IO线程进行网络请求
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<SimpleResponse<List<SchoolInformation>>>() {
                               @Override
                               public void accept(SimpleResponse<List<SchoolInformation>> simpleResponse) throws Exception {
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
