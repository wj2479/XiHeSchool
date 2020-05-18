package com.xh.module.base.db;

import android.database.sqlite.SQLiteDatabase;

import com.xh.module.base.BaseApplication;
import com.xh.module.base.db.dao.DaoMaster;
import com.xh.module.base.db.dao.DaoSession;

/**
 * Created by Administrator on 2016/11/30 0030.
 */

public class DBManager {

    private DaoMaster.DevOpenHelper mHelper;
    private SQLiteDatabase db;
    private DaoMaster mDaoMaster;
    private DaoSession mDaoSession;

    private static DBManager greenDaoUtils;

    private DBManager() {
    }

    public static DBManager getInstance() {
        if (greenDaoUtils == null) {
            greenDaoUtils = new DBManager();
        }
        return greenDaoUtils;
    }

    private void initGreenDao() {
        mHelper = new DaoMaster.DevOpenHelper(BaseApplication.getInstance(), "cz.xh.school", null);
        db = mHelper.getWritableDatabase();
        mDaoMaster = new DaoMaster(db);
        mDaoSession = mDaoMaster.newSession();
    }

    public DaoSession getmDaoSession() {
        if (mDaoMaster == null) {
            initGreenDao();
        }
        return mDaoSession;
    }
}
