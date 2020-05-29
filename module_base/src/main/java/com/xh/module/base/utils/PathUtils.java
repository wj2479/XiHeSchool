package com.xh.module.base.utils;

import android.text.TextUtils;

import com.xh.module.base.Constant;

/**
 * 处理服务器路径的工具类
 */
public class PathUtils {

    /**
     * 组合路径
     *
     * @param path
     */
    public static String composePath(String path) {
        if (TextUtils.isEmpty(path))
            return "";
        String newpath = null;
        if (path.startsWith("http://") || path.startsWith("https://")) {
            newpath = path;
        } else if (path.startsWith("/")) {
            newpath = Constant.SERVER_URL + path.substring(1);
        } else {
            newpath = Constant.SERVER_URL + path;
        }
        return newpath;
    }
}
