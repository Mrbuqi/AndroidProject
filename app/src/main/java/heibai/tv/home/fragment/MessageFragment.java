package heibai.tv.home.fragment;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;


import heibai.tv.R;
import heibai.tv.home.adapter.FragmentAdapter;

/**
 * Created by Ozawa on 2017/3/6.
 */
public class MessageFragment extends Fragment {


    private ViewPager mViewPager;
    private TabLayout mTabLayout;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {


        super.onCreateView(inflater, container, savedInstanceState);
        View parentView = inflater.inflate(R.layout.message_fragment, container,
                false);
        initViewPager(parentView);

        return parentView;
    }


    private void initViewPager(View view) {
        // View v = (View)findViewById(R.id.include);
        mViewPager = (ViewPager) view.findViewById(R.id.viewpager);
        mTabLayout = (TabLayout) view.findViewById(R.id.tabs);
        List<String> titles = new ArrayList<>();
        titles.add("首页");
        titles.add("电影");
        titles.add("发现");
        titles.add("电视剧");
        titles.add("综艺");
        titles.add("卫士");
        titles.add("热播");
        titles.add("励志");
        titles.add("图文");
        titles.add("本地");


        for(int i=0;i<titles.size();i++){
            mTabLayout.addTab(mTabLayout.newTab().setText(titles.get(i)));
        }
        List<Fragment> fragments = new ArrayList<>();
        for(int i=0;i<titles.size();i++){
            fragments.add(new M1_messageFragment());
        }
        FragmentAdapter mFragmentAdapteradapter =
                new FragmentAdapter(getActivity().getSupportFragmentManager(), fragments, titles);

        mViewPager. setOffscreenPageLimit(6);
        //给ViewPager设置适配器
        mViewPager.setAdapter(mFragmentAdapteradapter);
        //将TabLayout和ViewPager关联起来。
        mTabLayout.setupWithViewPager(mViewPager);
        //给TabLayout设置适配器
        mTabLayout.setTabsFromPagerAdapter(mFragmentAdapteradapter);
    }
}
