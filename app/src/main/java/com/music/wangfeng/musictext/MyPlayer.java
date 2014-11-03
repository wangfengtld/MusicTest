package com.music.wangfeng.musictext;

/**
 * Created by Administrator on 2014/11/3.
 */
public class MyPlayer {
  private static LocalPlayer localPlayer;
  public static void play(Audio audio){
    if(localPlayer == null){
      localPlayer = new LocalPlayer();
    }
    localPlayer.play(audio);
  }
}
