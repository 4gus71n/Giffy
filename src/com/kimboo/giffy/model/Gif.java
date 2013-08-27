package com.kimboo.giffy.model;

import java.util.Collection;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.field.ForeignCollectionField;

public class Gif extends Model {
    
    /** client-side fields */
    @ForeignCollectionField(eager = false)
    private Collection<GifFrame> frames;
    /** server-side fields */
    @DatabaseField(unique = true)
    private Long id; 
    @DatabaseField
    private Long likesAmount;
    @DatabaseField
    private Long followersAmount;
    @DatabaseField
    private Long score;
    //TODO: Config relationship
    private User uploader;
    @DatabaseField
    private String thumbUrl;
    @DatabaseField
    private String fullUrl;
    @DatabaseField
    private GifPortrait view;
    
    public Gif() {
        super();
    }
    
    public Gif(Long idLocal) {
        super(idLocal);
    }

    public Gif(Long idLocal, Collection<GifFrame> frames, Long id,
            Long likesAmount, Long followersAmount, Long score, User uploader, String thumbUrl,
            String fullUrl, GifPortrait view) {
        super(idLocal);
        this.frames = frames;
        this.id = id;
        this.likesAmount = likesAmount;
        this.followersAmount = followersAmount;
        this.score = score;
        this.uploader = uploader;
        this.thumbUrl = thumbUrl;
        this.fullUrl = fullUrl;
        this.view = view;
    }

    public Collection<GifFrame> getFrames() {
        return frames;
    }

    public void setFrames(Collection<GifFrame> frames) {
        this.frames = frames;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getLikesAmount() {
        return likesAmount;
    }

    public void setLikesAmount(Long likesAmount) {
        this.likesAmount = likesAmount;
    }

    public Long getFollowersAmount() {
        return followersAmount;
    }

    public void setFollowersAmount(Long followersAmount) {
        this.followersAmount = followersAmount;
    }

    public Long getScore() {
        return score;
    }

    public void setScore(Long score) {
        this.score = score;
    }

    public User getUploader() {
        return uploader;
    }

    public void setUploader(User uploader) {
        this.uploader = uploader;
    }

    public String getThumbUrl() {
        return thumbUrl;
    }

    public void setThumbUrl(String thumbUrl) {
        this.thumbUrl = thumbUrl;
    }

    public String getFullUrl() {
        return fullUrl;
    }

    public void setFullUrl(String fullUrl) {
        this.fullUrl = fullUrl;
    }

    public GifPortrait getView() {
        return view;
    }

    public void setView(GifPortrait view) {
        this.view = view;
    }

    @Override
    public String toString() {
        return "Gif [idLocal=" + getIdLocal() + ", frames=" + frames
                + ", id=" + id + ", likesAmount=" + likesAmount + ", followersAmount="
                + followersAmount + ", score=" + score + ", uploader=" + uploader + ", thumbUrl="
                + thumbUrl + ", fullUrl=" + fullUrl + ", view=" + view + "]";
    }

}
