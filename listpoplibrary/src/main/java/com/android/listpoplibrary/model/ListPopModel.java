package com.android.listpoplibrary.model;

/**
 * Created by radio on 2017/11/1.
 */

public class ListPopModel {
    private String content;
    private int imageId;
    private String imagePath;
    private ImageType type;
    public ListPopModel(){}
    public ListPopModel(String content, String imagePath, ImageType type) {
        this.content = content;
        this.imagePath = imagePath;
        this.type = type;
    }
    public ListPopModel(String content, int imageId, ImageType type) {
        this.content = content;
        this.imageId = imageId;
        this.type = type;
    }
    public int getImageId() {
        return imageId;
    }

    public void setImageId(int imageId) {
        this.imageId = imageId;
    }

    public String getImagePath() {
        return imagePath;
    }

    public void setImagePath(String imagePath) {
        this.imagePath = imagePath;
    }

    public ImageType getType() {
        return type;
    }

    public void setType(ImageType type) {
        this.type = type;
    }

    public String getContent() {
        return content;
    }
    public void setContent(String content) {
        this.content = content;
    }

}
