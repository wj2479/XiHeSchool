package com.xh.module_school.entity;

/**
 * 图片+ 2个文字
 *
 * @param <T>
 */
public class Image2Text<T> {
    /**
     * 文字标题
     */
    private String title;
    /**
     * 内容
     */
    private String content;

    /**
     * 图片路径 ，可以是res url path 等
     */
    private T path;

    public Image2Text(String title, String content, T path) {
        this.title = title;
        this.content = content;
        this.path = path;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public T getPath() {
        return path;
    }

    public void setPath(T path) {
        this.path = path;
    }
}
