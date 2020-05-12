package com.xh.module.base.repository.impl;

import com.xh.module.base.entity.LoginInfo;
import com.xh.module.base.repository.ILoginRepository;
import com.xh.module.base.retrofit.ApiManager;
import com.xh.module.base.retrofit.IRxJavaCallBack;
import com.xh.module.base.retrofit.response.SimpleResponse;

import org.json.JSONException;
import org.json.JSONObject;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;
import okhttp3.MediaType;
import okhttp3.RequestBody;

/**
 * 登录相关的请求
 */
public class LoginRepository implements ILoginRepository {

    private static LoginRepository INSTANCE = null;

    public static LoginRepository getInstance() {
        if (INSTANCE == null) {
            INSTANCE = new LoginRepository();
        }
        return INSTANCE;
    }

    @Override
    public void login(String username, String password, IRxJavaCallBack<SimpleResponse<LoginInfo>> callback) {
        JSONObject params = new JSONObject();
        try {
            params.put("identifier", username);
            params.put("certificate", password);
        } catch (JSONException e) {
            e.printStackTrace();
        }

        RequestBody requestBody = RequestBody.create(MediaType.parse("application/json; charset=utf-8"), params.toString());

        ApiManager.getInstance().getSchoolServer().login(requestBody)
                .subscribeOn(Schedulers.io())               //在IO线程进行网络请求
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<SimpleResponse<LoginInfo>>() {
                               @Override
                               public void accept(SimpleResponse<LoginInfo> simpleResponse) throws Exception {
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
