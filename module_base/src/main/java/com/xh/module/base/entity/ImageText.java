package com.xh.module.base.entity;

/**
 * 图片 文字对象
 */
public class ImageText<T> {

    /**
     * 文字标题
     */
    private String text;

    /**
     * 图片路径 ，可以是res url path 等
     */
    private T path;

    public ImageText(String text, T path) {
        this.text = text;
        this.path = path;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public T getPath() {
        return path;
    }

    public void setPath(T path) {
        this.path = path;
    }
}
