package com.xh.module.base.retrofit;

public interface IRxJavaCallBack<T> {

    void onSuccess(T t);

    void onError(Throwable throwable);
}
