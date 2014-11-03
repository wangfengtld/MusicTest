package com.music.wangfeng.musictext;

/**
 * Created by Administrator on 2014/11/3.
 */
public class Enty {
  private String musicUrl;
  private int resId;
  private String textString;

  public int getResId() {
    return resId;
  }

  public void setResId(int resId) {
    this.resId = resId;
  }

  public String getMusicUrl() {
    return musicUrl;
  }

  public void setMusicUrl(String musicUrl) {
    this.musicUrl = musicUrl;
  }

  public String getTextString() {
    return textString;
  }

  public void setTextString(String textString) {
    this.textString = textString;
  }


  public Enty(int resId, String musicUrl, String textString) {
    this.resId = resId;
    this.musicUrl = musicUrl;
    this.textString = textString;
  }
}
