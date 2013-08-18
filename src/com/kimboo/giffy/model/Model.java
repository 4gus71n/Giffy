package com.kimboo.giffy.model;

import com.j256.ormlite.field.DatabaseField;

public class Model {
    @DatabaseField(generatedId = true)
    private Long id;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
}
