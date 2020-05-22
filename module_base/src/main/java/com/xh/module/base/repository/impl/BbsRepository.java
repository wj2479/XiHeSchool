package com.xh.module.base.repository.impl;

import android.text.TextUtils;
import android.util.Log;

import androidx.annotation.Nullable;

import com.google.gson.Gson;
import com.xh.module.base.entity.bbs.BbsArticle;
import com.xh.module.base.entity.bbs.BbsUser;
import com.xh.module.base.repository.IBbsRepository;
import com.xh.module.base.retrofit.ApiManager;
import com.xh.module.base.retrofit.IRxJavaCallBack;
import com.xh.module.base.retrofit.response.SimpleResponse;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.File;
import java.util.List;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;
import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;

/**
 * 论坛相关用户请求
 */
public class BbsRepository implements IBbsRepository {

    private static BbsRepository INSTANCE = null;

    Gson gson = new Gson();

    public static BbsRepository getInstance() {
        if (INSTANCE == null) {
            INSTANCE = new BbsRepository();
        }
        return INSTANCE;
    }

    private BbsRepository() {
    }

    @Override
    public void getBbsUserInfo(long userId, IRxJavaCallBack<SimpleResponse<BbsUser>> callback) {
        ApiManager.getInstance().getSchoolServer().getBbsUserInfo(userId)
                .subscribeOn(Schedulers.io())               //在IO线程进行网络请求
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<SimpleResponse<BbsUser>>() {
                               @Override
                               public void accept(SimpleResponse<BbsUser> simpleResponse) throws Exception {
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
    public void updateUserInfo(BbsUser bbsUser, IRxJavaCallBack<SimpleResponse> callback) {
        String json = gson.toJson(bbsUser);

        Log.e("TAG", "要修改的内容:" + json);

        RequestBody requestBody = RequestBody.create(MediaType.parse("application/json; charset=utf-8"), json);
        ApiManager.getInstance().getSchoolServer().updateBbsUserInfo(requestBody)
                .subscribeOn(Schedulers.io())               //在IO线程进行网络请求
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<SimpleResponse>() {
                               @Override
                               public void accept(SimpleResponse simpleResponse) throws Exception {
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
    public void addBbsUser(BbsUser bbsUser, IRxJavaCallBack<SimpleResponse> callback) {
        String json = gson.toJson(bbsUser);
        Log.e("TAG", "要添加的内容:" + json);
        RequestBody requestBody = RequestBody.create(MediaType.parse("application/json; charset=utf-8"), json);
        ApiManager.getInstance().getSchoolServer().addBbsUser(requestBody)
                .subscribeOn(Schedulers.io())               //在IO线程进行网络请求
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<SimpleResponse>() {
                               @Override
                               public void accept(SimpleResponse simpleResponse) throws Exception {
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
    public void publishArticle(@Nullable BbsArticle article, List<File> files, IRxJavaCallBack<SimpleResponse> callback) {
        MultipartBody.Builder builder = new MultipartBody.Builder()
                .setType(MultipartBody.FORM);
        //用户ID
        if (article.getUid() == null) {
            if (callback != null) {
                callback.onError(new Throwable("用户名为空"));
            }
            return;
        } else {
            builder.addFormDataPart("uid", article.getUid().toString());
        }
        //内容
        if (TextUtils.isEmpty(article.getContent())) {
            if (callback != null) {
                callback.onError(new Throwable("内容为空"));
            }
            return;
        } else {
            builder.addFormDataPart("content", article.getContent());
        }
        //地址
        if (!TextUtils.isEmpty(article.getAddress())) {
            builder.addFormDataPart("address", article.getAddress());
        }
        // 经纬度
        if (article.getLatitude() != null && article.getLongitude() != null) {
            builder.addFormDataPart("longitude", article.getLongitude().toString());
            builder.addFormDataPart("latitude", article.getLatitude().toString());
        }
        // 板块
        builder.addFormDataPart("moduleId", article.getModuleId() == null ? "1" : article.getModuleId().toString());

        //注意，file是后台约定的参数，如果是多图，file[]，如果是单张图片，file就行
        if (files != null) {
            for (File file : files) {
                //这里上传的是多图
                builder.addFormDataPart("file[]", file.getName(), RequestBody.create(MediaType.parse("image/*"), file));
            }
        }

        RequestBody requestBody = builder.build();

        ApiManager.getInstance().getSchoolServer().publishArticle(requestBody)
                .subscribeOn(Schedulers.io())               //在IO线程进行网络请求
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<SimpleResponse>() {
                               @Override
                               public void accept(SimpleResponse simpleResponse) throws Exception {
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
    public void deletaArticle(long articleId, IRxJavaCallBack<SimpleResponse> callback) {
        JSONObject params = new JSONObject();
        try {
            params.put("id", articleId);
        } catch (JSONException e) {
            e.printStackTrace();
        }

        RequestBody requestBody = RequestBody.create(MediaType.parse("application/json; charset=utf-8"), params.toString());
        ApiManager.getInstance().getSchoolServer().deleteArticle(requestBody)
                .subscribeOn(Schedulers.io())               //在IO线程进行网络请求
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<SimpleResponse>() {
                               @Override
                               public void accept(SimpleResponse simpleResponse) throws Exception {
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
    public void getRecommendArticles(int page, int pageSize, IRxJavaCallBack<SimpleResponse<List<BbsArticle>>> callback) {
        ApiManager.getInstance().getSchoolServer().getBbsArticles(page, pageSize)
                .subscribeOn(Schedulers.io())               //在IO线程进行网络请求
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<SimpleResponse<List<BbsArticle>>>() {
                               @Override
                               public void accept(SimpleResponse<List<BbsArticle>> simpleResponse) throws Exception {
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
    public void getArticlesByUserId(long userId, int page, int pageSize, IRxJavaCallBack<SimpleResponse<List<BbsArticle>>> callback) {
        JSONObject params = new JSONObject();
        try {
            params.put("uid", userId);
            params.put("page", page);
            params.put("pagesize", pageSize);
        } catch (JSONException e) {
            e.printStackTrace();
        }

        RequestBody requestBody = RequestBody.create(MediaType.parse("application/json; charset=utf-8"), params.toString());

        ApiManager.getInstance().getSchoolServer().getBbsArticlesByUserId(requestBody)
                .subscribeOn(Schedulers.io())               //在IO线程进行网络请求
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<SimpleResponse<List<BbsArticle>>>() {
                               @Override
                               public void accept(SimpleResponse<List<BbsArticle>> simpleResponse) throws Exception {
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
    public void uploadBbsImgs(List<File> files, IRxJavaCallBack<SimpleResponse<List<String>>> callback) {
        MultipartBody.Builder builder = new MultipartBody.Builder()
                .setType(MultipartBody.FORM);
        //注意，file是后台约定的参数，如果是多图，file[]，如果是单张图片，file就行
        for (File file : files) {
            //这里上传的是多图
            builder.addFormDataPart("file[]", file.getName(), RequestBody.create(MediaType.parse("image/*"), file));
        }
        RequestBody requestBody = builder.build();

        ApiManager.getInstance().getSchoolServer().uploadBbsImage(requestBody)
                .subscribeOn(Schedulers.io())               //在IO线程进行网络请求
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<SimpleResponse<List<String>>>() {
                               @Override
                               public void accept(SimpleResponse<List<String>> simpleResponse) throws Exception {
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
