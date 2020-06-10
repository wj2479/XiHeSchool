package com.xh.module.base.utils;

import android.content.Context;
import android.os.Build;
import android.provider.Settings;
import android.telephony.TelephonyManager;

public class DeviceTool {

    public static String getIMEI(Context context) {
        String deviceId = null;
        try {
            TelephonyManager tm = (TelephonyManager) context
                    .getSystemService(Context.TELEPHONY_SERVICE);
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.Q) {
                deviceId = Settings.System.getString(context.getContentResolver(), Settings.Secure.ANDROID_ID);
            } else {
                deviceId = tm.getDeviceId();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return deviceId;
    }
}
