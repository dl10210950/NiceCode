package com.duanlian.nicecodes.base;


import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

import java.util.ArrayList;
import java.util.List;

/**
 * 自定义FragmentPagerAdapter
 */
public class CustomPagerAdapter<T extends Fragment> extends FragmentPagerAdapter {
    List<T> mFragments;
    List<String> mTitles;

    public CustomPagerAdapter(FragmentManager fm) {
        super(fm);
    }

    /**
     * 设置当前adapter的页面集合
     *
     * @param fragments
     */
    public void setPagers(List<T> fragments) {
        mFragments = fragments;
    }

    /**
     * 添加一个页面
     *
     * @param fragment
     */
    public void addPager(T fragment) {
        if (mFragments == null) {
            ArrayList<T> fragments = new ArrayList<T>();
            fragments.add(fragment);
            setPagers(fragments);
        } else {
            mFragments.add(fragment);
        }
    }

    /**
     * 添加一组页面
     *
     * @param fragments
     */
    public void addPagers(List<T> fragments) {
        if (mFragments == null) {
            setPagers(fragments);
        } else {
            mFragments.addAll(fragments);
        }
    }

    /**
     * 设置页面的标题
     *
     * @param titles
     */
    public void setTitles(List<String> titles) {
        mTitles = titles;
    }

    /**
     * 添加一个页面的标题
     *
     * @param title
     */
    public void addTitle(String title) {
        if (mTitles == null) {
            ArrayList<String> titles = new ArrayList<String>();
            titles.add(title);
            setTitles(titles);
        } else {
            mTitles.add(title);
        }
    }

    /**
     * 添加一组页面的标题
     *
     * @param titles
     */
    public void addTitles(List<String> titles) {
        if (mTitles == null) {
            setTitles(titles);
        } else {
            mTitles.addAll(titles);
        }
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return mTitles == null ? null : mTitles.get(position);
    }

    @Override
    public Fragment getItem(int position) {
        return mFragments == null ? null : mFragments.get(position);
    }

    @Override
    public int getCount() {
        return mFragments == null ? 0 : mFragments.size();
    }

}
