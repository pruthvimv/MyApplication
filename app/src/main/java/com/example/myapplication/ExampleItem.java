package com.example.myapplication;

public class ExampleItem {
    private String mImageUrl;
    private String mCreator;
    private int mLikes;

    public ExampleItem(String imageUrl, String creator, int likes) {
        this.mImageUrl = imageUrl;
        this.mCreator = creator;
        this.mLikes = likes;
    }


  public String getmImageUrl() {
        return mImageUrl;
  }

  public String getmCreator() {
        return mCreator;
  }

  public int getmLikes() {
        return mLikes;
  }



}
