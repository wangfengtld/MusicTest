package com.music.wangfeng.musictext;

import android.support.v4.app.Fragment;

/**
 * Created by Administrator on 2014/10/14.
 */
public class BaseFragment extends Fragment {
  public BaseFragment() {
  }

  protected void replaceLayout(int layoutID, BaseFragment baseFragment) {
    getChildFragmentManager().beginTransaction()
        .replace(layoutID, baseFragment)
        .commit();
  }
}
