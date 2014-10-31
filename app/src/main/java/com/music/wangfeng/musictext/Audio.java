package com.music.wangfeng.musictext;

/**
 * Created by AZ on 14-9-15.
 */
public class Audio {
  public static final int LOCAL = 0;

  private String title;
  private String artist;
  private String album;
  private String id;
  private String localPlayUri;
  private int    from;
  private String albumId;
  private long   duration;
  private long   size;
  private String mimeType;
  private String remotePlayUri;

  public String getTitle() {
    return title;
  }

  public void setTitle(String title) {
    this.title = title;
  }

  public String getArtist() {
    return artist;
  }

  public void setArtist(String artist) {
    this.artist = artist;
  }

  public String getAlbum() {
    return album;
  }

  public void setAlbum(String album) {
    this.album = album;
  }

  public void setId(String id) {
    this.id = id;
  }

  public String getId() {
    return id;
  }

  public void setLocalPlayUri(String localPlayUri) {
    this.localPlayUri = localPlayUri;
  }

  public String getLocalPlayUri() {
    return localPlayUri;
  }

  public void setFrom(int from) {
    this.from = from;
  }

  public int getFrom() {
    return from;
  }

  public void setAlbumId(String albumId) {
    this.albumId = albumId;
  }

  public String getAlbumId() {
    return albumId;
  }

  public void setDuration(long duration) {
    this.duration = duration;
  }

  public long getDuration() {
    return duration;
  }

  public void setSize(long size) {
    this.size = size;
  }

  public long getSize() {
    return size;
  }

  public void setMimeType(String mimeType) {
    this.mimeType = mimeType;
  }

  public String getMimeType() {
    return mimeType;
  }

  public String getRemotePlayUri() {
    return remotePlayUri;
  }

  public void setRemotePlayUri(String remotePlayUri) {
    this.remotePlayUri = remotePlayUri;
  }
}
