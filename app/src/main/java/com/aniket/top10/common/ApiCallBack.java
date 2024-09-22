package com.aniket.top10.common;

public abstract class ApiCallBack<T> {
    public abstract void onSuccess(T result);
    public abstract void onError(String msg);
}
