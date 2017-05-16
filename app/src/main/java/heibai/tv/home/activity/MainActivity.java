package heibai.tv.home.activity;

import android.content.Intent;
import android.os.Bundle;

import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;

import heibai.tv.R;
import heibai.tv.home.adapter.TabFragmentAdapter;
import heibai.tv.home.fragment.HomeFragment;
import heibai.tv.home.fragment.MessageFragment;
import heibai.tv.home.fragment.MineFragment;
import view.TabContainerView;


public class MainActivity extends AppCompatActivity implements ViewPager.OnPageChangeListener{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initViews();

    }

    /**
     * tab图标集合
     */
    private final int ICONS_RES[][] = {
            {
                    R.mipmap.ic_home_normal,
                    R.mipmap.ic_home_focus
            },
            {
                    R.mipmap.ic_message_normal,
                    R.mipmap.ic_message_focus
            },

            {
                    R.mipmap.ic_mine_normal,
                    R.mipmap.ic_mine_focus
            },

            {
                    R.mipmap.ic_mine_normal,
                    R.mipmap.ic_mine_focus
            }

    };

    /** tab 颜色值*/
    private final int[] TAB_COLORS = new int []{
            R.color.main_bottom_tab_textcolor_normal,
            R.color.main_bottom_tab_textcolor_selected};

    private Fragment[] fragments = {
            new HomeFragment(),
            new MessageFragment(),
            new MineFragment(),
            new MineFragment()
    };

    private void initViews(){

        TabFragmentAdapter mAdapter = new TabFragmentAdapter(getSupportFragmentManager(), fragments);
        ViewPager mPager = (ViewPager) findViewById(R.id.tab_pager);
        //设置当前可见Item左右可见page数，次范围内不会被销毁
        mPager.setOffscreenPageLimit(4);
        mPager.setAdapter(mAdapter);

        TabContainerView mTabLayout = (TabContainerView) findViewById(R.id.ll_tab_container);
        mTabLayout.setOnPageChangeListener(this);

        mTabLayout.initContainer(getResources().getStringArray(R.array.tab_main_title), ICONS_RES, TAB_COLORS, true);

        int width = getResources().getDimensionPixelSize(R.dimen.tab_icon_width);
        int height = getResources().getDimensionPixelSize(R.dimen.tab_icon_height);
        mTabLayout.setContainerLayout(R.layout.tab_container_view, R.id.iv_tab_icon, R.id.tv_tab_text, width, height);
//        mTabLayout.setSingleTextLayout(R.layout.tab_container_view, R.id.tv_tab_text);
//        mTabLayout.setSingleIconLayout(R.layout.tab_container_view, R.id.iv_tab_icon);

        mTabLayout.setViewPager(mPager);

        mPager.setCurrentItem(getIntent().getIntExtra("tab", 0));
    }

    @Override
    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

    }

    @Override
    public void onPageSelected(int position) {
        for (int index = 0, len = fragments.length; index < len; index ++) {
            fragments[index].onHiddenChanged(index != position);
        }

    }

    @Override
    public void onPageScrollStateChanged(int state) {

    }
    @Override
    protected void onNewIntent(Intent intent) {
        super.onNewIntent(intent);

    }
}