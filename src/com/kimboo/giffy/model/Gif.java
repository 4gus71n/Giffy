package com.kimboo.giffy.model;

import java.util.Collection;

import com.j256.ormlite.dao.ForeignCollection;
import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.field.ForeignCollectionField;

public class Gif {
    @DatabaseField(generatedId = true)
    private Long id;
    @DatabaseField
    private String filename;
    @ForeignCollectionField(eager = false)
    private Collection<GifFrame> frames;
    
    public Gif() {
        super();
    }

    public Gif(Long id, String filename, Collection<GifFrame> frames) {
        super();
        this.id = id;
        this.filename = filename;
        this.frames = frames;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getFilename() {
        return filename;
    }

    public void setFilename(String filename) {
        this.filename = filename;
    }

    public Collection<GifFrame> getFrames() {
        return frames;
    }

    public void setFrames(Collection<GifFrame> frames) {
        this.frames = frames;
    }
    
}
