package com.music.wangfeng.musictext;

import android.content.Context;
import android.media.MediaPlayer;
import android.util.Log;

import java.io.IOException;

import rx.Observable;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;
import rx.subjects.BehaviorSubject;
import rx.subjects.PublishSubject;

/**
 * Created by AZ on 14-9-15.
 */
public class LocalPlayer implements Player, MediaPlayer.OnErrorListener, MediaPlayer.OnPreparedListener, MediaPlayer.OnSeekCompleteListener, MediaPlayer.OnCompletionListener {
  private BehaviorSubject<Integer> state        = BehaviorSubject.create();
  private BehaviorSubject<Audio>   playingAudio = BehaviorSubject.create();
  private BehaviorSubject<Integer> duration     = BehaviorSubject.create();
  private PublishSubject<Boolean>  onCompletion = PublishSubject.create();

  protected MediaPlayer player;

  public LocalPlayer() {
    player = new MediaPlayer();
    player.setOnErrorListener(this);
    player.setOnPreparedListener(this);
    player.setOnSeekCompleteListener(this);
    player.setOnCompletionListener(this);

    playingAudio.onNext(null);
    state.onNext(STATE_STOPPED);
  }

  @Override public void play(Audio audio) {
    playingAudio.onNext(audio);
    state.onNext(Player.STATE_PREPARING);

    player.reset();
    try {
      player.setDataSource(getPlayUri(audio));
      player.prepareAsync();
    } catch (IOException e) {
      onError(e);
    }
  }

  private String getPlayUri(Audio audio) {
    String dataSource;
    if (audio.getFrom() == Audio.LOCAL) {
      dataSource = audio.getLocalPlayUri();
    } else {
      dataSource = audio.getRemotePlayUri();
    }
    return dataSource;
  }

  private void onError(Exception e) {
    state.onNext(Player.STATE_STOPPED);
  }

  @Override public void pause() {
    player.pause();
    state.onNext(STATE_PAUSED);
    Log.d("LocalPlayer", "Pause local player...");
  }

  @Override public void stop() {
    player.stop();
    state.onNext(STATE_STOPPED);
  }

  @Override public void seek(long position) {
    state.onNext(STATE_PREPARING);
    player.seekTo((int) position);
  }

  @Override public Observable<Integer> getState() {
    return state.observeOn(AndroidSchedulers.mainThread());
  }

  @Override public Observable<Audio> getPlayingAudio() {
    return playingAudio.observeOn(AndroidSchedulers.mainThread());
  }

  @Override public Observable<Boolean> onPlayComplete() {
    return onCompletion;
  }

  @Override public Observable<Integer> getPosition() {

    return state.flatMap(state -> {
      if (state == STATE_PLAYING) {
        return Observable.create((Subscriber<? super Integer> subscriber) -> {
          while (!subscriber.isUnsubscribed()) {
            if (player.isPlaying()) {
              int position = player.getCurrentPosition();
              Log.d("LocalPlayer", "Get position info: " + position + " - " + player.getDuration());
              subscriber.onNext(position);
            }
            try {
              Thread.sleep(1000);
            } catch (InterruptedException ignored) {
              break;
            }
          }
          subscriber.onCompleted();
          Log.d("LocalPlayer", "Unsubscribe position info");
        }).subscribeOn(Schedulers.newThread())
            .observeOn(AndroidSchedulers.mainThread());
      }
      return Observable.create((Subscriber<? super Integer> subscriber) -> {
        if (state == STATE_PAUSED) {
          subscriber.onNext(player.getCurrentPosition());
        } else {
          subscriber.onNext(0);
        }
      });
    });

  }

  @Override public int getPositionValue() {
    return player.getCurrentPosition();
  }

  @Override public Observable<Integer> getDuration() {
    return duration.observeOn(AndroidSchedulers.mainThread());
  }

  @Override public String getName(Context context) {
    return "我的手机";
  }

  @Override public void resume() {
    state.onNext(STATE_PREPARING);
    player.start();
    state.onNext(STATE_PLAYING);
  }

  @Override public boolean onError(MediaPlayer mediaPlayer, int i, int i2) {
    return false;
  }

  @Override public void release() {
    player.release();
    player = null;

    state.onCompleted();
    playingAudio.onCompleted();
    onCompletion.onCompleted();
    duration.onCompleted();
  }

  @Override public String getId() {
    return "0";
  }

  @Override public void onPrepared(MediaPlayer mediaPlayer) {
    duration.onNext(mediaPlayer.getDuration());
    mediaPlayer.start();
    state.onNext(STATE_PLAYING);
  }

  @Override public void onSeekComplete(MediaPlayer mediaPlayer) {
    mediaPlayer.start();
    state.onNext(STATE_PLAYING);
  }

  @Override public void onCompletion(MediaPlayer mediaPlayer) {
    state.onNext(STATE_STOPPED);
    onCompletion.onNext(true);
  }


}
