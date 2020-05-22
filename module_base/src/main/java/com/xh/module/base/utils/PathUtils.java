package com.xh.module.base.utils;

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
        if (path == null)
            return "";
        String newpath = Constant.SERVER_URL;
        if (path.startsWith("/")) {
            newpath += path.substring(1);
        } else {
            newpath += path;
        }
        return newpath;
    }
}
