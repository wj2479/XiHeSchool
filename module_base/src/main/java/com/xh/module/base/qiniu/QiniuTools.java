package com.xh.module.base.qiniu;

import com.qiniu.android.common.FixedZone;
import com.qiniu.android.storage.Configuration;
import com.qiniu.android.storage.UploadManager;
import com.xh.module.base.Constant;
import com.xh.module.base.repository.DataRepository;

/**
 * 七牛云工具
 */
public class QiniuTools {

    private static UploadManager uploadManager = null;

    public static void init() {
        Configuration config = new Configuration.Builder()
                .connectTimeout(10)           // 链接超时。默认10秒
                .useHttps(true)               // 是否使用https上传域名
                .responseTimeout(60)          // 服务器响应超时。默认60秒
                .zone(FixedZone.zone0)        // 设置区域，不指定会自动选择。指定不同区域的上传域名、备用域名、备用IP。
                .build();
        // 重用uploadManager。一般地，只需要创建一个uploadManager对象
        uploadManager = new UploadManager(config, 3);//配置3个线程数并发上传；不配置默认为3，只针对file.size>4M生效。线程数建议不超过5，上传速度主要取决于上行带宽，带宽很小的情况单线程和多线程没有区别

        QiniuAuth qiniuAuth = QiniuAuth.create(Constant.QINIU_AccessKey, Constant.QINIU_SecretKey);
        DataRepository.qiniuToken = qiniuAuth.uploadToken(Constant.QINIU_Bucket);
    }

    public static UploadManager getUploadManager() {
        return uploadManager;
    }
}
