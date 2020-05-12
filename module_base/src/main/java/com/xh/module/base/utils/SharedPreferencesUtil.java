package com.xh.module.base.utils;

import android.content.Context;
import android.content.SharedPreferences;

import com.xh.module.base.entity.LoginInfo;

import java.io.File;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.HashSet;
import java.util.Set;

/**
 * Created by  wj
 */
public class SharedPreferencesUtil {
    private static final String FILENAME = "sp";
    private static final String LOGIN = "LOGIN";

    public SharedPreferencesUtil() {
    }

    public static void save(Context con, String key, String value) {
        SharedPreferences sp = con.getSharedPreferences(FILENAME, Context.MODE_PRIVATE);
        sp.edit().putString(key, value).commit();
    }

    public static String get(Context con, String key) {
        SharedPreferences sp = con.getSharedPreferences(FILENAME, Context.MODE_PRIVATE);
        return sp.getString(key, "");
    }

    public static void remove(Context con, String key) {
        SharedPreferences sp = con.getSharedPreferences(FILENAME, Context.MODE_PRIVATE);
        sp.edit().remove(key).commit();
    }

    public static void addStringSet(Context con, String key, String value) {
        SharedPreferences sp = con.getSharedPreferences(FILENAME, Context.MODE_PRIVATE);
        Set<String> set = getStringSet(con, key);
        set.add(value);
        sp.edit().putStringSet(key, set).commit();
    }

    public static void removeStringSet(Context con, String key, String value) {
        SharedPreferences sp = con.getSharedPreferences(FILENAME, Context.MODE_PRIVATE);
        Set<String> set = getStringSet(con, key);
        set.remove(value);
        sp.edit().putStringSet(key, set).commit();
    }

    public static Set<String> getStringSet(Context con, String key) {
        SharedPreferences sp = con.getSharedPreferences(FILENAME, Context.MODE_PRIVATE);
        return sp.getStringSet(key, new HashSet<String>());
    }


    /**
     * 保存登录信息
     *
     * @param ctx
     */
    public static void saveLogin(Context ctx, LoginInfo userInfo) {
        try {
            File file = new File(ctx.getFilesDir(), LOGIN);
            if (file.exists()) {
                file.delete();
            }
            ObjectOutputStream oos = new ObjectOutputStream(ctx.openFileOutput(LOGIN, Context.MODE_PRIVATE));
            oos.writeObject(userInfo);
            oos.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 加载已保存的登录信息
     *
     * @param ctx
     * @return
     */
    public static LoginInfo loadLogin(Context ctx) {
        LoginInfo data = null;
        try {
            File file = new File(ctx.getFilesDir(), LOGIN);
            if (file.exists()) {
                ObjectInputStream ois = new ObjectInputStream(ctx.openFileInput(LOGIN));
                data = (LoginInfo) ois.readObject();
                ois.close();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        if (data == null) {
            data = new LoginInfo();
        }
        return data;
    }

    public static void removeLogin(Context ctx) {
        try {
            File file = new File(ctx.getFilesDir(), LOGIN);
            if (file.exists()) {
                file.delete();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
