package com.imdigitalashish.imagesearchapp;

public class OneItem {

    private String mImageUrl;
    private String mCreator;
    private int mLikes;

    public OneItem(String imageUrl, String creator, int likes) {
        mImageUrl = imageUrl;
        mCreator = creator;
        mLikes = likes;
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
