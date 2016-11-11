package com.lg.model;

/**
 * Created by dutc on 2016/11/8.
 */
public class LagouModel {
    private boolean success;
    private LagouContent content;

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public LagouContent getContent() {
        return content;
    }

    public void setContent(LagouContent content) {
        this.content = content;
    }
}
