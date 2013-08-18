package com.kimboo.giffy.model;

import com.j256.ormlite.field.DatabaseField;

public class Gif extends Model {
    @DatabaseField
    private String hash;
    
    public Gif() {
        super();
    }
    
    public Gif(Long id, String hash) {
        super();
        this.setId(id);
        this.hash = hash;
    }
    
    public String getHash() {
        return hash;
    }
    
    public void setHash(String hash) {
        this.hash = hash;
    }
    
    
}
