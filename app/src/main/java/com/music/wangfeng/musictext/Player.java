package com.music.wangfeng.musictext;

import android.content.Context;

import rx.Observable;

/**
 * Created by AZ on 14-9-15.
 */
public interface Player {
  public static final int STATE_STOPPED   = 0;
  public static final int STATE_PREPARING = 1;
  public static final int STATE_PLAYING   = 2;
  public static final int STATE_PAUSED    = 3;

  void play(Audio audio);

  void pause();

  void resume();

  void stop();

  void seek(long position);

  Observable<Integer> getState();

  Observable<Audio> getPlayingAudio();

  Observable<Boolean> onPlayComplete();

  Observable<Integer> getPosition();

  int getPositionValue();

  Observable<Integer> getDuration();

  String getName(Context context);

  void release();

  String getId();
}
