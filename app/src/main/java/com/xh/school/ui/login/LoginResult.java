package com.xh.school.ui.login;

import androidx.annotation.Nullable;

import com.xh.module.base.entity.UserBase;

/**
 * Authentication result : success (user details) or error message.
 */
class LoginResult {
    @Nullable
    private UserBase success;
    @Nullable
    private String error;

    LoginResult(@Nullable String error) {
        this.error = error;
    }

    LoginResult(@Nullable UserBase success) {
        this.success = success;
    }

    @Nullable
    UserBase getSuccess() {
        return success;
    }

    @Nullable
    String getError() {
        return error;
    }
}
