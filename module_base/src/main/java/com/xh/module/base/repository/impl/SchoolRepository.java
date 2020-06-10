package com.xh.module.base.repository.impl;

import com.google.gson.Gson;
import com.xh.module.base.entity.ClassDemeanor;
import com.xh.module.base.entity.ClassId;
import com.xh.module.base.entity.HomeWorkAnnex;
import com.xh.module.base.entity.School;
import com.xh.module.base.entity.SchoolInformation;
import com.xh.module.base.entity.SchoolmasterMailbox;
import com.xh.module.base.entity.SchoolmasterMailboxReply;
import com.xh.module.base.entity.Schoolwork;
import com.xh.module.base.entity.TeacherClass;
import com.xh.module.base.repository.ISchoolRepository;
import com.xh.module.base.retrofit.ApiManager;
import com.xh.module.base.retrofit.IRxJavaCallBack;
import com.xh.module.base.retrofit.response.SimpleResponse;
import com.xh.module.base.utils.LogUtil;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.File;
import java.util.List;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;
import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;

public class SchoolRepository implements ISchoolRepository {

    private static SchoolRepository INSTANCE = null;

    Gson gson = new Gson();

    private SchoolRepository() {
    }

    public static SchoolRepository getInstance() {
        if (INSTANCE == null) {
            INSTANCE = new SchoolRepository();
        }
        return INSTANCE;
    }

    @Override
    public void getSchoolInfoById(long schoolId, IRxJavaCallBack<SimpleResponse<School>> callback) {
        JSONObject params = new JSONObject();
        try {
            params.put("id", schoolId);
        } catch (JSONException e) {
            e.printStackTrace();
        }

        RequestBody requestBody = RequestBody.create(MediaType.parse("application/json; charset=utf-8"), params.toString());

        ApiManager.getInstance().getSchoolServer().getSchoolInfoById(requestBody)
                .subscribeOn(Schedulers.io())               //在IO线程进行网络请求
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<SimpleResponse<School>>() {
                               @Override
                               public void accept(SimpleResponse<School> simpleResponse) throws Exception {
                                   if (callback != null) {
                                       callback.onSuccess(simpleResponse);
                                   }
                               }
                           }, new Consumer<Throwable>() {
                               @Override
                               public void accept(Throwable throwable) throws Exception {
                                   if (callback != null) {
                                       callback.onError(throwable);
                                   }
                               }
                           }
                );
    }

    @Override
    public void getSchoolInfomationById(long schoolId, int page, int pageSize, IRxJavaCallBack<SimpleResponse<List<SchoolInformation>>> callback) {
        ApiManager.getInstance().getSchoolServer().getSchoolInfomationById(schoolId, page, pageSize)
                .subscribeOn(Schedulers.io())               //在IO线程进行网络请求
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<SimpleResponse<List<SchoolInformation>>>() {
                               @Override
                               public void accept(SimpleResponse<List<SchoolInformation>> simpleResponse) throws Exception {
                                   if (callback != null) {
                                       callback.onSuccess(simpleResponse);
                                   }
                               }
                           }, new Consumer<Throwable>() {
                               @Override
                               public void accept(Throwable throwable) throws Exception {
                                   if (callback != null) {
                                       callback.onError(throwable);
                                   }
                               }
                           }
                );
    }

    @Override
    public void uploadSchoolInfomationImgs(List<File> files, IRxJavaCallBack<SimpleResponse<List<String>>> callback) {

        MultipartBody.Builder builder = new MultipartBody.Builder()
                .setType(MultipartBody.FORM);
        //注意，file是后台约定的参数，如果是多图，file[]，如果是单张图片，file就行
        for (File file : files) {
            //这里上传的是多图
            builder.addFormDataPart("file[]", file.getName(), RequestBody.create(MediaType.parse("image/*"), file));
        }

        RequestBody requestBody = builder.build();

        ApiManager.getInstance().getSchoolServer().uploadInfoImage(requestBody)
                .subscribeOn(Schedulers.io())               //在IO线程进行网络请求
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<SimpleResponse<List<String>>>() {
                               @Override
                               public void accept(SimpleResponse<List<String>> simpleResponse) throws Exception {
                                   if (callback != null) {
                                       callback.onSuccess(simpleResponse);
                                   }
                               }
                           }, new Consumer<Throwable>() {
                               @Override
                               public void accept(Throwable throwable) throws Exception {
                                   if (callback != null) {
                                       callback.onError(throwable);
                                   }
                               }
                           }
                );

    }

    @Override
    public void addSchoolInfomation(SchoolInformation schoolInformation, IRxJavaCallBack<SimpleResponse> callback) {
        String json = gson.toJson(schoolInformation);
        RequestBody requestBody = RequestBody.create(MediaType.parse("application/json; charset=utf-8"), json);

        ApiManager.getInstance().getSchoolServer().addSchoolInfomation(requestBody)
                .subscribeOn(Schedulers.io())               //在IO线程进行网络请求
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<SimpleResponse>() {
                               @Override
                               public void accept(SimpleResponse simpleResponse) throws Exception {
                                   if (callback != null) {
                                       callback.onSuccess(simpleResponse);
                                   }
                               }
                           }, new Consumer<Throwable>() {
                               @Override
                               public void accept(Throwable throwable) throws Exception {
                                   if (callback != null) {
                                       callback.onError(throwable);
                                   }
                               }
                           }
                );
    }

    @Override
    public void publishSchoolMasterMail(SchoolmasterMailbox mail, IRxJavaCallBack<SimpleResponse> callback) {
        String json = gson.toJson(mail);
        LogUtil.e("TAG", "添加的信件" + json);
        RequestBody requestBody = RequestBody.create(MediaType.parse("application/json; charset=utf-8"), json);

        ApiManager.getInstance().getSchoolServer().publishSchoolMasterMail(requestBody)
                .subscribeOn(Schedulers.io())               //在IO线程进行网络请求
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<SimpleResponse>() {
                               @Override
                               public void accept(SimpleResponse simpleResponse) throws Exception {
                                   if (callback != null) {
                                       callback.onSuccess(simpleResponse);
                                   }
                               }
                           }, new Consumer<Throwable>() {
                               @Override
                               public void accept(Throwable throwable) throws Exception {
                                   if (callback != null) {
                                       callback.onError(throwable);
                                   }
                               }
                           }
                );
    }

    @Override
    public void getReceivedSchoolMasterMails(long schoolId, int page, int pageSize, IRxJavaCallBack<SimpleResponse<List<SchoolmasterMailbox>>> callback) {
        ApiManager.getInstance().getSchoolServer().getReceivedSchoolMasterMails(schoolId, page, pageSize)
                .subscribeOn(Schedulers.io())               //在IO线程进行网络请求
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<SimpleResponse<List<SchoolmasterMailbox>>>() {
                               @Override
                               public void accept(SimpleResponse<List<SchoolmasterMailbox>> simpleResponse) throws Exception {
                                   if (callback != null) {
                                       callback.onSuccess(simpleResponse);
                                   }
                               }
                           }, new Consumer<Throwable>() {
                               @Override
                               public void accept(Throwable throwable) throws Exception {
                                   if (callback != null) {
                                       callback.onError(throwable);
                                   }
                               }
                           }
                );

    }

    @Override
    public void getSendSchoolMasterMails(long schoolId, long uid, int page, int pageSize, IRxJavaCallBack<SimpleResponse<List<SchoolmasterMailbox>>> callback) {
        ApiManager.getInstance().getSchoolServer().getSendSchoolMasterMails(schoolId, uid, page, pageSize)
                .subscribeOn(Schedulers.io())               //在IO线程进行网络请求
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<SimpleResponse<List<SchoolmasterMailbox>>>() {
                               @Override
                               public void accept(SimpleResponse<List<SchoolmasterMailbox>> simpleResponse) throws Exception {
                                   if (callback != null) {
                                       callback.onSuccess(simpleResponse);
                                   }
                               }
                           }, new Consumer<Throwable>() {
                               @Override
                               public void accept(Throwable throwable) throws Exception {
                                   if (callback != null) {
                                       callback.onError(throwable);
                                   }
                               }
                           }
                );
    }

    @Override
    public void addSchoolMailBoxReply(SchoolmasterMailboxReply reply, IRxJavaCallBack<SimpleResponse> callback) {
        String json = gson.toJson(reply);
        LogUtil.e("TAG", "添加的回复:" + json);
        RequestBody requestBody = RequestBody.create(MediaType.parse("application/json; charset=utf-8"), json);

        ApiManager.getInstance().getSchoolServer().addSchoolMailBoxReply(requestBody)
                .subscribeOn(Schedulers.io())               //在IO线程进行网络请求
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<SimpleResponse>() {
                               @Override
                               public void accept(SimpleResponse simpleResponse) throws Exception {
                                   if (callback != null) {
                                       callback.onSuccess(simpleResponse);
                                   }
                               }
                           }, new Consumer<Throwable>() {
                               @Override
                               public void accept(Throwable throwable) throws Exception {
                                   if (callback != null) {
                                       callback.onError(throwable);
                                   }
                               }
                           }
                );
    }

    @Override
    public void getSchoolMailBoxReplys(long mailboxId, int page, int pageSize, IRxJavaCallBack<SimpleResponse<List<SchoolmasterMailboxReply>>> callback) {
        ApiManager.getInstance().getSchoolServer().getSchoolMailBoxReplys(mailboxId, page, pageSize)
                .subscribeOn(Schedulers.io())               //在IO线程进行网络请求
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<SimpleResponse<List<SchoolmasterMailboxReply>>>() {
                               @Override
                               public void accept(SimpleResponse<List<SchoolmasterMailboxReply>> simpleResponse) throws Exception {
                                   if (callback != null) {
                                       callback.onSuccess(simpleResponse);
                                   }
                               }
                           }, new Consumer<Throwable>() {
                               @Override
                               public void accept(Throwable throwable) throws Exception {
                                   if (callback != null) {
                                       callback.onError(throwable);
                                   }
                               }
                           }
                );
    }

    @Override
    public void addClassDemeanor(ClassDemeanor demeanor, IRxJavaCallBack<SimpleResponse> callback) {
        String json = gson.toJson(demeanor);
        LogUtil.e("TAG", "添加的班级风采:" + json);
        RequestBody requestBody = RequestBody.create(MediaType.parse("application/json; charset=utf-8"), json);

        ApiManager.getInstance().getSchoolServer().addClassDemeanor(requestBody)
                .subscribeOn(Schedulers.io())               //在IO线程进行网络请求
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<SimpleResponse>() {
                               @Override
                               public void accept(SimpleResponse simpleResponse) throws Exception {
                                   if (callback != null) {
                                       callback.onSuccess(simpleResponse);
                                   }
                               }
                           }, new Consumer<Throwable>() {
                               @Override
                               public void accept(Throwable throwable) throws Exception {
                                   if (callback != null) {
                                       callback.onError(throwable);
                                   }
                               }
                           }
                );
    }

    @Override
    public void uploadClassDemeanorImgs(List<File> files, IRxJavaCallBack<SimpleResponse<List<String>>> callback) {
        MultipartBody.Builder builder = new MultipartBody.Builder()
                .setType(MultipartBody.FORM);
        //注意，file是后台约定的参数，如果是多图，file[]，如果是单张图片，file就行
        for (File file : files) {
            //这里上传的是多图
            builder.addFormDataPart("file[]", file.getName(), RequestBody.create(MediaType.parse("image/*"), file));
        }

        RequestBody requestBody = builder.build();

        ApiManager.getInstance().getSchoolServer().uploadClassDemeanorImage(requestBody)
                .subscribeOn(Schedulers.io())               //在IO线程进行网络请求
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<SimpleResponse<List<String>>>() {
                               @Override
                               public void accept(SimpleResponse<List<String>> simpleResponse) throws Exception {
                                   if (callback != null) {
                                       callback.onSuccess(simpleResponse);
                                   }
                               }
                           }, new Consumer<Throwable>() {
                               @Override
                               public void accept(Throwable throwable) throws Exception {
                                   if (callback != null) {
                                       callback.onError(throwable);
                                   }
                               }
                           }
                );
    }

    @Override
    public void getClassDemeanors(long classId, int page, int pageSize, IRxJavaCallBack<SimpleResponse<List<ClassDemeanor>>> callback) {
        ApiManager.getInstance().getSchoolServer().getClassDemeanorById(classId, page, pageSize)
                .subscribeOn(Schedulers.io())               //在IO线程进行网络请求
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<SimpleResponse<List<ClassDemeanor>>>() {
                               @Override
                               public void accept(SimpleResponse<List<ClassDemeanor>> simpleResponse) throws Exception {
                                   if (callback != null) {
                                       callback.onSuccess(simpleResponse);
                                   }
                               }
                           }, new Consumer<Throwable>() {
                               @Override
                               public void accept(Throwable throwable) throws Exception {
                                   if (callback != null) {
                                       callback.onError(throwable);
                                   }
                               }
                           }
                );

    }

    @Override
    public void getTeacherClassInfoById(long uid, IRxJavaCallBack<SimpleResponse<List<List<TeacherClass>>>> callback) {
        ApiManager.getInstance().getSchoolServer().getTeacherClassInfoById(uid)
                .subscribeOn(Schedulers.io())               //在IO线程进行网络请求
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<SimpleResponse<List<List<TeacherClass>>>>() {
                               @Override
                               public void accept(SimpleResponse<List<List<TeacherClass>>> simpleResponse) throws Exception {
                                   if (callback != null) {
                                       callback.onSuccess(simpleResponse);
                                   }
                               }
                           }, new Consumer<Throwable>() {
                               @Override
                               public void accept(Throwable throwable) throws Exception {
                                   if (callback != null) {
                                       callback.onError(throwable);
                                   }
                               }
                           }
                );

    }

    @Override
    public void addHomeWork(String clasIds, long courseId, String title, String content, long createUid, IRxJavaCallBack<SimpleResponse<List<ClassId>>> callback) {
        JSONObject params = new JSONObject();
        try {
            params.put("clasIds", clasIds);
            params.put("courseId", courseId);
            params.put("title", title);
            params.put("content", content);
            params.put("createUid", createUid);
        } catch (JSONException e) {
            e.printStackTrace();
        }

        RequestBody requestBody = RequestBody.create(MediaType.parse("application/json; charset=utf-8"), params.toString());
        ApiManager.getInstance().getSchoolServer().addHomeWork(requestBody)
                .subscribeOn(Schedulers.io())               //在IO线程进行网络请求
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<SimpleResponse<List<ClassId>>>() {
                               @Override
                               public void accept(SimpleResponse<List<ClassId>> simpleResponse) throws Exception {
                                   if (callback != null) {
                                       callback.onSuccess(simpleResponse);
                                   }
                               }
                           }, new Consumer<Throwable>() {
                               @Override
                               public void accept(Throwable throwable) throws Exception {
                                   if (callback != null) {
                                       callback.onError(throwable);
                                   }
                               }
                           }
                );
    }

    @Override
    public void addHomeWorkAnnex(List<HomeWorkAnnex> annexList, IRxJavaCallBack<SimpleResponse> callback) {
        String json = gson.toJson(annexList);
        LogUtil.e("TAG", "添加的作业附件:" + json);

        RequestBody requestBody = RequestBody.create(MediaType.parse("application/json; charset=utf-8"), json);

        ApiManager.getInstance().getSchoolServer().addHomeWorkAnnex(requestBody)
                .subscribeOn(Schedulers.io())               //在IO线程进行网络请求
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<SimpleResponse>() {
                               @Override
                               public void accept(SimpleResponse simpleResponse) throws Exception {
                                   if (callback != null) {
                                       callback.onSuccess(simpleResponse);
                                   }
                               }
                           }, new Consumer<Throwable>() {
                               @Override
                               public void accept(Throwable throwable) throws Exception {
                                   if (callback != null) {
                                       callback.onError(throwable);
                                   }
                               }
                           }
                );
    }

    @Override
    public void getHomeWorkByClasId(long clasId, String date, IRxJavaCallBack<SimpleResponse<List<Schoolwork>>> callback) {
        ApiManager.getInstance().getSchoolServer().getHomeWorkByClasId(clasId, date)
                .subscribeOn(Schedulers.io())               //在IO线程进行网络请求
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<SimpleResponse<List<Schoolwork>>>() {
                               @Override
                               public void accept(SimpleResponse<List<Schoolwork>> simpleResponse) throws Exception {
                                   if (callback != null) {
                                       callback.onSuccess(simpleResponse);
                                   }
                               }
                           }, new Consumer<Throwable>() {
                               @Override
                               public void accept(Throwable throwable) throws Exception {
                                   if (callback != null) {
                                       callback.onError(throwable);
                                   }
                               }
                           }
                );
    }

}
