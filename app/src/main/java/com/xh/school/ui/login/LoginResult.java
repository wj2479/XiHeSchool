package com.xh.school.ui.login;

import androidx.annotation.Nullable;

import com.xh.module.base.entity.LoginInfo;

/**
 * Authentication result : success (user details) or error message.
 */
class LoginResult {
    @Nullable
    private LoginInfo success;
    @Nullable
    private String error;

    LoginResult(@Nullable String error) {
        this.error = error;
    }

    LoginResult(@Nullable LoginInfo success) {
        this.success = success;
    }

    @Nullable
    LoginInfo getSuccess() {
        return success;
    }

    @Nullable
    String getError() {
        return error;
    }
}
