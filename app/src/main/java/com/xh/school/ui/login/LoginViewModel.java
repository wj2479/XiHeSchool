package com.xh.school.ui.login;

import android.util.Patterns;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.google.gson.Gson;
import com.xh.module.base.entity.UserBase;
import com.xh.module.base.repository.impl.UserRepository;
import com.xh.module.base.retrofit.IRxJavaCallBack;
import com.xh.module.base.retrofit.ResponseCode;
import com.xh.module.base.retrofit.response.SimpleResponse;
import com.xh.module.base.utils.LogUtil;
import com.xh.school.R;


public class LoginViewModel extends ViewModel {

    private MutableLiveData<LoginFormState> loginFormState = new MutableLiveData<>();
    private MutableLiveData<LoginResult> loginResult = new MutableLiveData<>();

    protected Gson gson = new Gson();

    LoginViewModel() {

    }

    LiveData<LoginFormState> getLoginFormState() {
        return loginFormState;
    }

    LiveData<LoginResult> getLoginResult() {
        return loginResult;
    }

    public void login(String username, String password) {

        UserRepository.getInstance().login(username, password, new IRxJavaCallBack<SimpleResponse<UserBase>>() {
            @Override
            public void onSuccess(SimpleResponse<UserBase> simpleResponse) {
                LogUtil.e(LoginViewModel.this.getClass(), gson.toJson(simpleResponse));
                if (simpleResponse.getCode() == ResponseCode.RESULT_OK) {
                    loginResult.setValue(new LoginResult(simpleResponse.getData()));
                } else {
                    loginResult.setValue(new LoginResult(simpleResponse.getMsg()));
                }
            }

            @Override
            public void onError(Throwable throwable) {
                LogUtil.e(LoginViewModel.this.getClass(), throwable.toString());

                loginResult.setValue(new LoginResult("登录失败,请稍候再试"));
            }
        });

    }

    public void loginDataChanged(String username, String password) {
        if (!isUserNameValid(username)) {
            loginFormState.setValue(new LoginFormState(R.string.invalid_username, null));
        } else if (!isPasswordValid(password)) {
            loginFormState.setValue(new LoginFormState(null, R.string.invalid_password));
        } else {
            loginFormState.setValue(new LoginFormState(true));
        }
    }

    // A placeholder username validation check
    private boolean isUserNameValid(String username) {
        if (username == null) {
            return false;
        }
        if (username.contains("@")) {
            return Patterns.EMAIL_ADDRESS.matcher(username).matches();
        } else {
            return !username.trim().isEmpty();
        }
    }

    // A placeholder password validation check
    private boolean isPasswordValid(String password) {
        return password != null && password.trim().length() > 5;
    }
}
