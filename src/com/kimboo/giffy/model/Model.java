package com.kimboo.giffy.model;

import com.j256.ormlite.field.DatabaseField;

public class Model {
    @DatabaseField(generatedId = true)
    private Long idLocal;

    public Long getIdLocal() {
        return idLocal;
    }

    public void setIdLocal(Long idLocal) {
        this.idLocal = idLocal;
    }

    public Model(Long idLocal) {
        super();
        this.idLocal = idLocal;
    }

    public Model() {
        super();
    }
    
}
