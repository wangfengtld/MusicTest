package com.music.wangfeng.musictext;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v4.view.ViewPager;
import android.widget.ImageView;
import android.widget.LinearLayout;

import java.util.ArrayList;
import java.util.List;

import butterknife.ButterKnife;
import butterknife.InjectView;


public class MyActivity extends BaseActivity implements ViewPager.OnPageChangeListener {

  @Override
  public void onPageScrolled(int i, float v, int i2) {

  }

  @Override
  public void onPageSelected(int arg0) {
    for (int i = 0; i < vpFlag.getChildCount(); i++) {
      vpFlag.getChildAt(i).setBackgroundResource(R.drawable.round_flag_unselect);
    }
    vpFlag.getChildAt(arg0 % vpFlag.getChildCount()).setBackgroundResource(R.drawable.round_flag_select);
    bannerSwitchHandler.removeMessages(1);
    bannerSwitchHandler.sendEmptyMessageDelayed(1, bannerSwitchTime);
  }

  @Override
  public void onPageScrollStateChanged(int i) {

  }

  @InjectView(R.id.autoVP)      ViewPager    viewPager;
  @InjectView(R.id.vpindicator) LinearLayout vpFlag;

  private List<Enty>         musicList;
  private ViewPagerAdapter   viewPagerAdapter;
  private List<BaseFragment> itemFragmentList;

  private final long bannerSwitchTime = 240000l;

  private Handler bannerSwitchHandler = new Handler() {
    public void handleMessage(Message msg) {
      switch (msg.what) {
        case 1:
          int totalcount = musicList.size();
          int currentItem = viewPager.getCurrentItem();
          int toItem = currentItem + 1;
          viewPager.setCurrentItem(toItem, true);
      }
    }
  };

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_my);
    ButterKnife.inject(this);
    musicList = new ArrayList<>();
    Enty enty1 = new Enty(R.drawable.lu, "http://music.baidu.com/data/music/file?link=http://yinyueshiting.baidu.com/data2/music/10316788/1031581657600128.mp3?xcode=eebe4128e26f3d74bf4ce45e66333fb40214d4eba2ea5079&song_id=10315816", getString(R.string.lu));
    Enty enty2 = new Enty(R.drawable.qiong, "http://music.baidu.com/data/music/file?link=http://yinyueshiting.baidu.com/data2/music/121338458/1392013968400128.mp3?xcode=add74114788ce786d0b1c27e4e5f3d69e3c6eea7fe42fb59&song_id=13920139", getString(R.string.qiong));
    Enty enty3 = new Enty(R.drawable.hui, "http://music.baidu.com/data/music/file?link=http://yinyueshiting.baidu.com/data2/music/121338482/32151884244800128.mp3?xcode=db60a9abbb0e60b24b1232dbc87a0cfa9689a4c52f7d2884&song_id=32151884", getString(R.string.hui));
    Enty enty4 = new Enty(R.drawable.wo, "http://music.baidu.com/data/music/file?link=http://yinyueshiting.baidu.com/data2/music/124282202/72973453600128.mp3?xcode=db60a9abbb0e60b2e87f92ea096f96abca3d5af4b8fcb093&song_id=7297345", getString(R.string.wo));
    Enty enty5 = new Enty(R.drawable.xi, "http://music.baidu.com/data/music/file?link=http://yinyueshiting.baidu.com/data2/music/376264/376252187200128.mp3?xcode=671016e916059d8e3a273052a1358f9a3da6702bb3296740&song_id=376252", getString(R.string.xi));
    Enty enty6 = new Enty(R.drawable.huan, "http://music.baidu.com/data/music/file?link=http://yinyueshiting.baidu.com/data2/music/376177/376170151200128.mp3?xcode=07697aea92bfa4b97899f45ad41d07d101d3937daeadb851&song_id=376170", getString(R.string.huan));
    Enty enty7 = new Enty(R.drawable.ni, "http://music.baidu.com/data/music/file?link=http://yinyueshiting.baidu.com/data2/music/376497/37649214400128.mp3?xcode=32a0e1e0bb212ce55db9cb256b97c37f37f2cdf478804527&song_id=376492", getString(R.string.ni));

    musicList.add(enty1);
    musicList.add(enty2);
    musicList.add(enty3);
    musicList.add(enty4);
    musicList.add(enty5);
    musicList.add(enty6);
    musicList.add(enty7);
    viewPagerAdapter = new ViewPagerAdapter(getSupportFragmentManager());
    itemFragmentList = new ArrayList<>();
    if (musicList != null && musicList.size() > 0) {
      LinearLayout.LayoutParams ind_params = new LinearLayout.LayoutParams(
        LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT
      );
      for (int i = 0; i < musicList.size(); i++) {
        FragmentItem fragmentItem = new FragmentItem();
        fragmentItem.setEnty(musicList.get(i));
        itemFragmentList.add(fragmentItem);
        ImageView imageView = new ImageView(this);
        ind_params.setMargins(10, 10, 10, 10);
        imageView.setLayoutParams(ind_params);
        if (i == 0) {
          imageView.setBackgroundResource(R.drawable.round_flag_select);
        } else {
          imageView.setBackgroundResource(R.drawable.round_flag_unselect);
        }
        vpFlag.addView(imageView);
      }
    }
    viewPagerAdapter.setBaseFragmentList(itemFragmentList);
    viewPager.setAdapter(viewPagerAdapter);
    viewPager.setOnPageChangeListener(this);
    viewPager.setCurrentItem(musicList.size() * 1000);
    bannerSwitchHandler.sendEmptyMessageDelayed(1, bannerSwitchTime);
  }
}
