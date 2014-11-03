package com.music.wangfeng.musictext;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import butterknife.ButterKnife;
import butterknife.InjectView;

/**
 * Created by Administrator on 2014/11/3.
 */
public class FragmentItem extends BaseFragment {

  @InjectView(R.id.text)      TextView  textView;
  @InjectView(R.id.back_view) ImageView backView;

  private Enty enty;

  @Override
  public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
    View view = inflater.inflate(R.layout.fragment_item, container, false);
    ButterKnife.inject(this, view);
    return view;
  }


  @Override
  public void onActivityCreated(@Nullable Bundle savedInstanceState) {
    super.onActivityCreated(savedInstanceState);
    if (enty == null) {
      return;
    }
    List<Integer> colorList = new ArrayList<>();
    colorList.add(R.color.cherry_item_option);
    colorList.add(R.color.artist_detail_unselect_bg);
    colorList.add(R.color.white_alpha);
    colorList.add(R.color.title_bg);
    colorList.add(R.color.red);
    colorList.add(R.color.mini_player_bg);
    colorList.add(R.color.album_singer_text);
    colorList.add(R.color.cherry_green);
    if (enty.getTextString() != null && !enty.getTextString().equals("")) {
      int position = (int) (Math.random() * colorList.size());
      Log.d("message",position+"");
      textView.setTextColor(getResources().getColor(colorList.get(position)));
      textView.setText(enty.getTextString());
    }

    if (enty.getMusicUrl() != null && !enty.getMusicUrl().equals("")) {
      Audio audio = new Audio();
      audio.setRemotePlayUri(enty.getMusicUrl());
      audio.setAlbum("asd");
      audio.setArtist("dd");
      audio.setFrom(2);
      audio.setAlbumId("asd");
      audio.setDuration(40000);
      audio.setTitle("ads");
      MyPlayer.play(audio);
    }

    if (enty.getResId() != 0) {
      backView.setImageResource(enty.getResId());
    }
  }

  public void setEnty(Enty enty) {
    this.enty = enty;
  }
}
