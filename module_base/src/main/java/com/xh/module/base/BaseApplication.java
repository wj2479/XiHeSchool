package com.xh.module.base;

import android.app.Application;
import android.content.Context;

import com.alibaba.android.arouter.launcher.ARouter;
import com.danikula.videocache.HttpProxyCacheServer;
import com.tamsiree.rxkit.RxTool;
import com.tencent.bugly.crashreport.CrashReport;
import com.xh.module.BuildConfig;
import com.xh.module.base.qiniu.QiniuTools;
import com.xh.module.base.utils.UIUtils;
import com.zzhoujay.richtext.RichText;

/**
 * 程序的主入口
 */
public class BaseApplication extends Application {

    /**
     * 上下文
     */
    private static BaseApplication instance;

    private HttpProxyCacheServer proxy = null;

    @Override
    public void onCreate() {
        super.onCreate();

        instance = this;
        initRouter(this);

        RxTool.init(this);

        QiniuTools.init();

        RichText.initCacheDir(this);
        // 腾讯bugly初始化
        CrashReport.initCrashReport(getApplicationContext(), "01076d5ac1", BuildConfig.DEBUG);
    }

    public static Context getInstance() {
        return instance;
    }

    private void initRouter(BaseApplication mApplication) {
        // 这两行必须写在init之前，否则这些配置在init过程中将无效
        if (UIUtils.isApkInDebug(instance)) {
            //打印日志
            ARouter.openLog();
            //开启调试模式(如果在InstantRun模式下运行，必须开启调试模式！
            //线上版本需要关闭,否则有安全风险)
            ARouter.openDebug();
        }
        // 尽可能早，推荐在Application中初始化
        ARouter.init(mApplication);
    }

    public static HttpProxyCacheServer getProxy(Context context) {
        BaseApplication app = (BaseApplication) context.getApplicationContext();
        return app.proxy == null ? (app.proxy = app.newProxy()) : app.proxy;
    }

    private HttpProxyCacheServer newProxy() {
        return new HttpProxyCacheServer(this);
    }
}
