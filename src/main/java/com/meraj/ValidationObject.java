package com.meraj;

import javax.validation.constraints.AssertFalse;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

public class ValidationObject {

    @Size(min = 3, max = 10)
    private String size;

    @NotNull
    private String notNull;

    @AssertFalse
    private Boolean isFalse;

    public ValidationObject(String size, String notNull, Boolean isFalse) {
        this.size = size;
        this.notNull = notNull;
        this.isFalse = isFalse;
    }

    public String getSize() {
        return size;
    }

    public void setSize(String size) {
        this.size = size;
    }

    public String getNotNull() {
        return notNull;
    }

    public void setNotNull(String notNull) {
        this.notNull = notNull;
    }

    public Boolean getIsFalse() {
        return isFalse;
    }

    public void setIsFalse(Boolean isFalse) {
        this.isFalse = isFalse;
    }
}
