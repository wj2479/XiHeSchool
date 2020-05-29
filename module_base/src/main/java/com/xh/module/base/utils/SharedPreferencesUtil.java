package com.xh.module.base.utils;

import android.content.Context;
import android.content.SharedPreferences;

import com.xh.module.base.entity.Role;
import com.xh.module.base.entity.UserBase;

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
    private static final String ROLE = "ROLE";

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
    public static void saveLogin(Context ctx, UserBase userInfo) {
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
    public static UserBase loadLogin(Context ctx) {
        UserBase data = null;
        try {
            File file = new File(ctx.getFilesDir(), LOGIN);
            if (file.exists()) {
                ObjectInputStream ois = new ObjectInputStream(ctx.openFileInput(LOGIN));
                data = (UserBase) ois.readObject();
                ois.close();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        if (data == null) {
            data = new UserBase();
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

    /**
     * 保存角色信息
     *
     * @param ctx
     */
    public static void saveRole(Context ctx, Role role) {
        try {
            File file = new File(ctx.getFilesDir(), ROLE);
            if (file.exists()) {
                file.delete();
            }
            ObjectOutputStream oos = new ObjectOutputStream(ctx.openFileOutput(ROLE, Context.MODE_PRIVATE));
            oos.writeObject(role);
            oos.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 加载已保存的角色信息
     *
     * @param ctx
     * @return
     */
    public static Role getRole(Context ctx) {
        Role data = null;
        try {
            File file = new File(ctx.getFilesDir(), ROLE);
            if (file.exists()) {
                ObjectInputStream ois = new ObjectInputStream(ctx.openFileInput(ROLE));
                data = (Role) ois.readObject();
                ois.close();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return data;
    }

    /**
     * 移除已经保存的角色信息
     *
     * @param ctx
     */
    public static void removeRole(Context ctx) {
        try {
            File file = new File(ctx.getFilesDir(), ROLE);
            if (file.exists()) {
                file.delete();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
