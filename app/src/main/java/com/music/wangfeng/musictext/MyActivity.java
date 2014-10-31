package com.music.wangfeng.musictext;

import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;


public class MyActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my);
      Audio audio = new Audio();
      audio.setRemotePlayUri("http://music.baidu.com/data/music/file?link=http://yinyueshiting.baidu.com/data2/music/376244/3762321414364461128.mp3?xcode=c89e5a5acc8ed39ea10fe6f4a4a5b9ef3430810c144f9ee2&song_id=376232");
      audio.setAlbum("asd");
      audio.setArtist("dd");
      audio.setFrom(2);
      audio.setAlbumId("asd");
      audio.setDuration(4000);
      audio.setTitle("ads");
      LocalPlayer localPlayer = new LocalPlayer();
      localPlayer.play(audio);
    }
}
