package com.winbro.penelope.data;

/**
 * Created by pdavis on 07/11/2016.
 */
public class PartData {

    private long id;
    private String part;
    private String partType;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getPart() {
        return part;
    }

    public void setPart(String part) {
        this.part = part;
    }

    public String getPartType() {
        return partType;
    }

    public void setPartType(String partType) {
        this.partType = partType;
    }
}
