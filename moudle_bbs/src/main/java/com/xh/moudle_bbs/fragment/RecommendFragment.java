package com.xh.moudle_bbs.fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.listener.OnItemChildClickListener;
import com.chad.library.adapter.base.listener.OnItemClickListener;
import com.qmuiteam.qmui.skin.QMUISkinManager;
import com.qmuiteam.qmui.widget.dialog.QMUIDialog;
import com.qmuiteam.qmui.widget.dialog.QMUIDialogAction;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.listener.OnLoadMoreListener;
import com.scwang.smartrefresh.layout.listener.OnRefreshListener;
import com.xh.module.base.BaseFragment;
import com.xh.module.base.entity.bbs.BbsArticle;
import com.xh.module.base.repository.impl.BbsRepository;
import com.xh.module.base.retrofit.IRxJavaCallBack;
import com.xh.module.base.retrofit.ResponseCode;
import com.xh.module.base.retrofit.response.SimpleResponse;
import com.xh.module.base.utils.RouteUtils;
import com.xh.moudle_bbs.R;
import com.xh.moudle_bbs.R2;
import com.xh.moudle_bbs.adapter.ArticleAdapter;
import com.xh.moudle_bbs.event.BbsEvent;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

/**
 * 推荐主界面
 */
@Route(path = RouteUtils.Bbs_Fragment_Recommend_Home)
public class RecommendFragment extends BaseFragment {

    @BindView(R2.id.refreshLayout)
    SmartRefreshLayout refreshLayout;
    @BindView(R2.id.recyclerView)
    RecyclerView recyclerView;

    /**
     * 记录文章列表
     */
    List<BbsArticle> dataList = new ArrayList<>();

    ArticleAdapter adapter;
    /**
     * 当前那一页
     */
    int page = 1;
    /**
     * 每页显示的数量
     */
    int pageSize = 10;

    public RecommendFragment() {
        // Required empty public constructor
    }

    @Override
    protected int initLayout() {
        return R.layout.fragment_recommend;
    }

    @Override
    protected void initView(View rootView) {
        initRefresh();
        initRecyclerView();
    }

    private void initRefresh() {
        refreshLayout.setEnableRefresh(true);//是否启用下拉刷新功能
        refreshLayout.setEnableLoadMore(false);//是否启用上拉加载功能

        refreshLayout.setOnLoadMoreListener(new OnLoadMoreListener() {
            @Override
            public void onLoadMore(@NonNull RefreshLayout refreshLayout) {
                Log.e("TAG", "加载更多");
                loadMoreInfos();
            }
        });

        refreshLayout.setOnRefreshListener(new OnRefreshListener() {
            @Override
            public void onRefresh(@NonNull RefreshLayout refreshLayout) {
                Log.e("TAG", "刷新");
                loadNewInfos();
            }
        });
    }

    private void initRecyclerView() {
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        adapter = new ArticleAdapter(getActivity(), dataList);
        recyclerView.setAdapter(adapter);

        adapter.setOnItemClickListener(new OnItemClickListener() {
            @Override
            public void onItemClick(@NonNull BaseQuickAdapter<?, ?> adapter, @NonNull View view, int position) {
                if (dataList.size() == 0)
                    return;
            }
        });

        // 先注册需要点击的子控件id（注意，请不要写在convert方法里）
        adapter.addChildClickViewIds(R.id.deleteTv);
        // 设置子控件点击监听
        adapter.setOnItemChildClickListener(new OnItemChildClickListener() {
            @Override
            public void onItemChildClick(@NonNull BaseQuickAdapter adapter, @NonNull View view, final int position) {
                if (dataList.size() == 0)
                    return;
                final BbsArticle article = dataList.get(position);
                new QMUIDialog.MessageDialogBuilder(getContext())
                        .setTitle("提示")
                        .setMessage("确定要删除这篇帖子吗？")
                        .setSkinManager(QMUISkinManager.defaultInstance(getContext()))
                        .addAction("取消", new QMUIDialogAction.ActionListener() {
                            @Override
                            public void onClick(QMUIDialog dialog, int index) {
                                dialog.dismiss();
                            }
                        })
                        .addAction(0, "删除", QMUIDialogAction.ACTION_PROP_NEGATIVE, new QMUIDialogAction.ActionListener() {
                            @Override
                            public void onClick(QMUIDialog dialog, int index) {
                                dialog.dismiss();

                                BbsRepository.getInstance().deletaArticle(article.getId(), new IRxJavaCallBack<SimpleResponse>() {
                                    @Override
                                    public void onSuccess(SimpleResponse simpleResponse) {
                                        Log.e("TAG", "删除:" + simpleResponse.toString());
                                        if (simpleResponse.getCode() == ResponseCode.RESULT_OK) {
                                            if (position != RecyclerView.NO_POSITION && dataList.size() > position) {
                                                dataList.remove(position);
                                                RecommendFragment.this.adapter.notifyItemRemoved(position);
                                                RecommendFragment.this.adapter.notifyItemRangeChanged(position, dataList.size());
                                            }
                                            EventBus.getDefault().post(BbsEvent.DELETE_RECOMMEND_ARTICLE);
                                        } else {
                                            showFailDialogAndDismiss("删除失败");
                                        }
                                    }

                                    @Override
                                    public void onError(Throwable throwable) {
                                        Log.e("TAG", "删除异常:" + throwable.toString());
                                        showFailDialogAndDismiss("删除失败");
                                    }
                                });
                            }
                        })
                        .create(R.style.QMUI_Dialog).show();

            }
        });

        View emptyView = LayoutInflater.from(getContext()).inflate(R.layout.common_empty, null);
        emptyView.setLayoutParams(new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,
                ViewGroup.LayoutParams.MATCH_PARENT));
        TextView tipsTv = emptyView.findViewById(R.id.tv_empty);
        tipsTv.setText("还没有人发送过帖子哦");
        //添加空视图
        adapter.setEmptyView(emptyView);
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onEvent(BbsEvent event) {
        switch (event) {
            case DELETE_MY_ARTICLE:
                loadNewInfos();
                break;
        }
    }

    @Override
    public void onStart() {
        super.onStart();
        loadNewInfos();
    }

    /**
     * 获取最新的文章
     */
    private void loadNewInfos() {
        BbsRepository.getInstance().getRecommendArticles(1, pageSize, new IRxJavaCallBack<SimpleResponse<List<BbsArticle>>>() {
            @Override
            public void onSuccess(SimpleResponse<List<BbsArticle>> response) {
                dataList.clear();
                if (response.getCode() == ResponseCode.RESULT_OK) {
                    Log.e("TAG", "获取论坛列表:" + gson.toJson(response.getData()));
                    dataList.addAll(response.getData());
                }
                adapter.notifyDataSetChanged();
                page = 1;
                hasMore();
                refreshLayout.finishRefresh(500);
            }

            @Override
            public void onError(Throwable throwable) {
                Log.e("TAG", "获取论坛列表:" + throwable.toString());
                refreshLayout.finishRefresh(500);
            }
        });
    }


    /**
     * 获取更多的文章
     */
    private void loadMoreInfos() {
        BbsRepository.getInstance().getRecommendArticles(page + 1, pageSize, new IRxJavaCallBack<SimpleResponse<List<BbsArticle>>>() {
            @Override
            public void onSuccess(SimpleResponse<List<BbsArticle>> response) {
                if (response.getCode() == ResponseCode.RESULT_OK) {
                    Log.e("TAG", "获取论坛资讯:" + gson.toJson(response.getData()));
                    dataList.addAll(response.getData());
                    adapter.notifyDataSetChanged();
                    page += 1;
                    hasMore();
                } else {
                    Log.e("TAG", "获取论坛失败:" + response.toString());
                }
                refreshLayout.finishLoadMore(500);
            }

            @Override
            public void onError(Throwable throwable) {
                Log.e("TAG", "获取论坛资讯异常:" + throwable.toString());
                refreshLayout.finishLoadMore(500);
            }
        });
    }

    /**
     * 判断是否开启加载更多功能
     */
    private void hasMore() {
        // 如果当前的页数都显示完成 则开启加载更多功能
        if (dataList.size() >= page * pageSize) {
            refreshLayout.setEnableLoadMore(true);//是否启用上拉加载功能
        } else {
            refreshLayout.setEnableLoadMore(false);//是否启用上拉加载功能
        }
    }

}
