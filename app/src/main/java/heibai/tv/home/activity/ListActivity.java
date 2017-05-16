package heibai.tv.home.activity;

import android.graphics.Point;
import android.os.Build;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.RecyclerView.OnScrollListener;
import android.transition.Explode;
import android.view.Window;
import android.widget.FrameLayout;
import android.widget.RelativeLayout;

import com.shuyu.gsyvideoplayer.GSYVideoPlayer;
import com.shuyu.gsyvideoplayer.utils.CommonUtil;
import com.shuyu.gsyvideoplayer.utils.Debuger;
import com.shuyu.gsyvideoplayer.utils.ListVideoUtil;

import java.util.ArrayList;
import java.util.List;

import heibai.tv.R;
import heibai.tv.home.adapter.RecyclerBaseAdapter;
import heibai.tv.home.holder.RecyclerItemViewHolder;
import heibai.tv.home.listener.SampleListener;
import heibai.tv.home.model.VideoModel;

/**
 * Created by Ozawa on 2017/5/8.
 */
public class ListActivity extends AppCompatActivity {


    LinearLayoutManager linearLayoutManager;

    RecyclerBaseAdapter recyclerBaseAdapter;

    List<VideoModel> dataList = new ArrayList<>();

    ListVideoUtil listVideoUtil;
    int lastVisibleItem;
    int firstVisibleItem;

    FrameLayout videoFullContainer;
    RecyclerView listItemRecycler;
    @Override
    public void onCreate(Bundle savedInstanceState) {


        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            getWindow().requestFeature(Window.FEATURE_CONTENT_TRANSITIONS);
            getWindow().setEnterTransition(new Explode());
            getWindow().setExitTransition(new Explode());
        }
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list);
        //qButterKnife.bind(this);

        RelativeLayout activity_recycler_view = (RelativeLayout) findViewById(R.id.activity_recycler_view);
        videoFullContainer = (FrameLayout) findViewById(R.id.video_full_container);
        listItemRecycler = (RecyclerView) findViewById(R.id.list_item_recycler);
        initView();

        listVideoUtil.setHideActionBar(true);

        listItemRecycler.addOnScrollListener(new OnScrollListener() {

            @Override
            public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
                super.onScrollStateChanged(recyclerView, newState);
            }

            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
                firstVisibleItem   = linearLayoutManager.findFirstVisibleItemPosition();
                lastVisibleItem = linearLayoutManager.findLastVisibleItemPosition();
                Debuger.printfLog("firstVisibleItem " + firstVisibleItem +" lastVisibleItem " + lastVisibleItem);
                //大于0说明有播放,//对应的播放列表TAG
                if (listVideoUtil.getPlayPosition() >= 0 && listVideoUtil.getPlayTAG().equals(RecyclerItemViewHolder.TAG)) {
                    //当前播放的位置
                    int position = listVideoUtil.getPlayPosition();
                    //不可视的是时候
                    if ((position < firstVisibleItem || position > lastVisibleItem)) {
                        //如果是小窗口就不需要处理
                        if (!listVideoUtil.isSmall() && !listVideoUtil.isFull()) {
                            //小窗口
                            int size = CommonUtil.dip2px(ListActivity.this, 150);
                            //actionbar为true才不会掉下面去
                            listVideoUtil.showSmallVideo(new Point(size, size), true, true);
                        }
                    } else {
                        if (listVideoUtil.isSmall()) {
                            listVideoUtil.smallVideoToNormal();
                        }
                    }
                }
            }
        });

        //小窗口关闭被点击的时候回调处理回复页面
        listVideoUtil.setVideoAllCallBack(new SampleListener() {
            @Override
            public void onPrepared(String url, Object... objects) {
                super.onPrepared(url, objects);
                Debuger.printfLog("Duration " + listVideoUtil.getDuration() + " CurrentPosition " + listVideoUtil.getCurrentPositionWhenPlaying());
            }

            @Override
            public void onQuitSmallWidget(String url, Object... objects) {
                super.onQuitSmallWidget(url, objects);
                //大于0说明有播放,//对应的播放列表TAG
                if (listVideoUtil.getPlayPosition() >= 0 && listVideoUtil.getPlayTAG().equals(RecyclerItemViewHolder.TAG)) {
                    //当前播放的位置
                    int position = listVideoUtil.getPlayPosition();
                    //不可视的是时候
                    if ((position < firstVisibleItem || position > lastVisibleItem)) {
                        //释放掉视频
                        listVideoUtil.releaseVideoPlayer();
                        recyclerBaseAdapter.notifyDataSetChanged();
                    }
                }

            }
        });
    }


    @Override
    public void onBackPressed() {
        if (listVideoUtil.backFromFull()) {
            return;
        }
        super.onBackPressed();
    }


    @Override
    protected void onDestroy() {
        super.onDestroy();
        listVideoUtil.releaseVideoPlayer();
        GSYVideoPlayer.releaseAllVideos();
    }

    private void initView() {
        linearLayoutManager = new LinearLayoutManager(this);
        listItemRecycler.setLayoutManager(linearLayoutManager);

        resolveData();

        recyclerBaseAdapter = new RecyclerBaseAdapter(this, dataList);
        listItemRecycler.setAdapter(recyclerBaseAdapter);


        listVideoUtil = new ListVideoUtil(this);
        listVideoUtil.setFullViewContainer(videoFullContainer);
        listVideoUtil.setHideStatusBar(true);
        recyclerBaseAdapter.setListVideoUtil(listVideoUtil);

    }

    private void resolveData() {
        for (int i = 0; i < 19; i++) {
            VideoModel videoModel = new VideoModel();
            dataList.add(videoModel);
        }
        if (recyclerBaseAdapter != null)
            recyclerBaseAdapter.notifyDataSetChanged();
    }

}
