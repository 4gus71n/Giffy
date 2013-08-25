
package com.kimboo.giffy.model;

import com.j256.ormlite.field.DatabaseField;

public class GifFrame {
    @DatabaseField(generatedId = true)
    private Long id;
    @DatabaseField(foreign = true)
    private Gif parentGif;
    @DatabaseField
    private Integer delay;
    @DatabaseField
    private String bitmapHashKey;

    public GifFrame() {
        super();
    }

    public GifFrame(Long id, Gif parentGif, Integer delay, String bitmapHashKey) {
        super();
        this.id = id;
        this.parentGif = parentGif;
        this.delay = delay;
        this.bitmapHashKey = bitmapHashKey;
    }

    public Integer getDelay() {
        return delay;
    }

    public void setDelay(Integer delay) {
        this.delay = delay;
    }

    public String getBitmapHashKey() {
        return bitmapHashKey;
    }

    public void setBitmapHashKey(String bitmapHashKey) {
        this.bitmapHashKey = bitmapHashKey;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Gif getParentGif() {
        return parentGif;
    }

    public void setParentGif(Gif parentGif) {
        this.parentGif = parentGif;
    }

}
