package heibai.tv.home.fragment;

import android.app.Activity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import butterknife.ButterKnife;
import heibai.tv.R;
import heibai.tv.home.activity.DetalisActivity;
import heibai.tv.home.adapter.RecyclerViewAdapter;

/**
 * Created by Ozawa on 2017/3/21.
 */
public class M1_messageFragment extends Fragment {

    boolean isLoading;

    private  SwipeRefreshLayout swipeRefreshLayout;
    private  RecyclerView recyclerView;
    private List<Map<String, Object>> data = new ArrayList<>();
    private RecyclerViewAdapter adapter;
    private Handler handler = new Handler();
    private Activity mActivity;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);
        View parentView = inflater.inflate(R.layout.m1_message_fragment, container,
                false);
        ButterKnife.inject(getActivity());
        initViews(parentView);
        initData();

        return  parentView;
    }

    public void initViews(View view) {

        adapter = new RecyclerViewAdapter(getMyActivity(), data);
        swipeRefreshLayout = (SwipeRefreshLayout)view.findViewById(R.id.SwipeRefreshLayout);
        recyclerView = (RecyclerView)view.findViewById(R.id.recyclerView);
        swipeRefreshLayout.setColorSchemeResources(R.color.colorPrimaryDark);
        swipeRefreshLayout.post(new Runnable() {
            @Override
            public void run() {
                swipeRefreshLayout.setRefreshing(true);
            }
        });

        //下拉所执行的方法
        swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                Log.d("test", "下拉刷新");
                handler.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        data.clear();
                        getData();

                    }
                }, 2000);
            }
        });
        final LinearLayoutManager layoutManager = new LinearLayoutManager(getActivity());
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(adapter);
        recyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
                super.onScrollStateChanged(recyclerView, newState);
                Log.d("test", "StateChanged = " + newState);


            }

            //上滑加载
            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
                Log.d("test", "onScrolled");

                int lastVisibleItemPosition = layoutManager.findLastVisibleItemPosition();
                if (lastVisibleItemPosition + 1 == adapter.getItemCount()) {
                    Log.d("test", "loading executed");

                    boolean isRefreshing = swipeRefreshLayout.isRefreshing();
                    if (isRefreshing) {
                        adapter.notifyItemRemoved(adapter.getItemCount());
                        return;
                    }
                    if (!isLoading) {
                        isLoading = true;
                        handler.postDelayed(new Runnable() {
                            @Override
                            public void run() {
                                getData();
                                Log.d("test", "load more completed");
                                isLoading = false;
                            }
                        }, 1000);
                    }
                }
            }
        });

        //添加点击事件
        adapter.setOnItemClickListener(new RecyclerViewAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(View view, int position) {
                Log.d("test", "item position = " + position);
                Intent intent=new Intent();
                intent.setClass(getMyActivity(),DetalisActivity.class);
                startActivity(intent);

            }

            @Override
            public void onItemLongClick(View view, int position) {

            }
        });
    }


    public void initData() {
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                getData();
            }
        }, 1500);

    }

    /**
     * 获取测试数据
     */
    private void getData() {
        for (int i = 0; i < 10; i++) {
            Map<String, Object> map = new HashMap<>();
            data.add(map);
        }
        adapter.notifyDataSetChanged();
        swipeRefreshLayout.setRefreshing(false);
        adapter.notifyItemRemoved(adapter.getItemCount());
    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);

        mActivity=activity;
    }

    //得到可靠地Activity
    public Activity getMyActivity()
    {
        return mActivity;
    }
}
