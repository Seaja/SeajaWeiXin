package com.demo.seajia.seajiaweixin;


import android.content.Context;
import android.content.Intent;
import android.location.LocationManager;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.demo.seajia.seajiaweixin.activity.NewsActivity;
import com.demo.seajia.seajiaweixin.bean.Msg;
import com.demo.seajia.seajiaweixin.thread.HttpThread;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends FragmentActivity implements OnClickListener {
    private ViewPager viewPager;

    private FragmentPagerAdapter fragmentPagerAdapter;

    private List<Fragment> fragmentList;

    private LinearLayout mTabChats;
    private LinearLayout mTabContact;
    private LinearLayout mTabDiscover;
    private LinearLayout mTabMe;


    private ImageButton mChartsImg;
    private ImageButton mContactImg;
    private ImageButton mDiscoverImg;
    private ImageButton mMeImg;

    private TextView mChartsText;
    private TextView mContactText;
    private TextView mDiscoverText;
    private TextView mMeText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_index);
        initView();
        initEvent();
    }



    public void chat(View view) {   //进入聊天界面
        Intent intent = new Intent(this, ChatingActivity.class);
        startActivity(intent);
    }

    public void news(View view) {   //进入新闻中心()
        Intent intent = new Intent(this, NewsActivity.class);
        startActivity(intent);
    }

    private void initView() {
        viewPager = (ViewPager) findViewById(R.id.id_view_content);

        mTabChats = (LinearLayout) findViewById(R.id.id_tab_chats);
        mTabContact = (LinearLayout) findViewById(R.id.id_tab_contact);
        mTabDiscover = (LinearLayout) findViewById(R.id.id_tab_discover);
        mTabMe = (LinearLayout) findViewById(R.id.id_tab_me);

        mChartsImg = (ImageButton) findViewById(R.id.id_tab_chats_img);
        mContactImg = (ImageButton) findViewById(R.id.id_tab_contact_img);
        mDiscoverImg = (ImageButton) findViewById(R.id.id_tab_discover_img);
        mMeImg = (ImageButton) findViewById(R.id.id_tab_me_img);

        mChartsText = (TextView) findViewById(R.id.id_tab_chats_text);
        mContactText = (TextView) findViewById(R.id.id_tab_contact_text);
        mDiscoverText = (TextView) findViewById(R.id.id_tab_discover_text);
        mMeText = (TextView) findViewById(R.id.id_tab_me_text);


        fragmentList = new ArrayList<Fragment>();   //创建FragmentList,之后作为FragmentAdapter的参数，传给ViewPager
        Fragment mFragChats = new ChartsFragment();
        Fragment mFragContact = new ContactFragment();
        Fragment mFragDiscover = new DiscoverFragment();
        Fragment mFragMe = new MeFragment();
        fragmentList.add(mFragChats);
        fragmentList.add(mFragContact);
        fragmentList.add(mFragDiscover);
        fragmentList.add(mFragMe);

        fragmentPagerAdapter = new FragmentPagerAdapter(getSupportFragmentManager()) {//创建适配器。v4包。兼容3.0之前的版本.
            @Override
            public Fragment getItem(int position) {
                return fragmentList.get(position);
            }

            @Override
            public int getCount() {
                return fragmentList.size();
            }
        };
        viewPager.setAdapter(fragmentPagerAdapter);//装载适配器

        viewPager.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                resetTabs();
                setSelected(position);
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });

    }

    private void initEvent() {
        mTabChats.setOnClickListener(this);
        mTabContact.setOnClickListener(this);
        mTabDiscover.setOnClickListener(this);
        mTabMe.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        resetTabs();
        switch (v.getId()) {
            case R.id.id_tab_chats:
                setSelected(0);
                viewPager.setCurrentItem(0);
                break;
            case R.id.id_tab_contact:
                setSelected(1);
                viewPager.setCurrentItem(1);
                break;
            case R.id.id_tab_discover:
                setSelected(2);
                viewPager.setCurrentItem(2);
                break;
            case R.id.id_tab_me:
                setSelected(3);
                viewPager.setCurrentItem(3);
                break;
        }
    }

    /**
     * 将Tab变灰
     */
    private void resetTabs() {
        mChartsImg.setImageResource(R.drawable.charts_off);
        mChartsText.setTextColor(getResources().getColor(R.color.off));

        mContactImg.setImageResource(R.drawable.contacts_off);
        mContactText.setTextColor(getResources().getColor(R.color.off));

        mDiscoverImg.setImageResource(R.drawable.discover_off);
        mDiscoverText.setTextColor(getResources().getColor(R.color.off));

        mMeImg.setImageResource(R.drawable.me_off);
        mMeText.setTextColor(getResources().getColor(R.color.off));

    }

    /**
     * 将点击的Tab变亮
     *
     * @param i
     */
    private void setSelected(int i) {
        switch (i) {
            case 0:
                mChartsImg.setImageResource(R.drawable.charts_on);
                mChartsText.setTextColor(getResources().getColor(R.color.on));
                break;
            case 1:
                mContactImg.setImageResource(R.drawable.contacts_on);
                mContactText.setTextColor(getResources().getColor(R.color.on));
                break;
            case 2:
                mDiscoverImg.setImageResource(R.drawable.discover_on);
                mDiscoverText.setTextColor(getResources().getColor(R.color.on));
                break;
            case 3:
                mMeImg.setImageResource(R.drawable.me_on);
                mMeText.setTextColor(getResources().getColor(R.color.on));
                break;
        }
    }


}
