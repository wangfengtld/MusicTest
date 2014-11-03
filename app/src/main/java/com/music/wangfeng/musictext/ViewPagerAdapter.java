package com.music.wangfeng.musictext;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.view.ViewGroup;

import java.util.List;

/**
 * Created by wangfeng on 14-10-21.
 */
public class ViewPagerAdapter extends FragmentStatePagerAdapter {

  private List<BaseFragment> baseFragmentList;
  private int totalPage;

  public List<BaseFragment> getBaseFragmentList() {
    return baseFragmentList;
  }

  public void setBaseFragmentList(List<BaseFragment> baseFragmentList) {
    this.baseFragmentList = baseFragmentList;
    this.totalPage = baseFragmentList.size();
  }

  public ViewPagerAdapter(FragmentManager fm) {
    super(fm);
  }

  @Override
  public Fragment getItem(int i) {
    return baseFragmentList.get(i % totalPage);
  }

  @Override
  public int getCount() {
    return Integer.MAX_VALUE;
  }

  @Override
  public void destroyItem(ViewGroup container, int position, Object object) {
    super.destroyItem(container, position, object);
  }
}
