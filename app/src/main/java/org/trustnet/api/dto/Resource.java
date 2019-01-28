package org.trustnet.api.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Created by bhadoria on 1/26/19.
 */

public class Resource {

    @JsonProperty("key")
    String key;

    @JsonProperty("owner")
    String owner;

    @JsonProperty("value")
    int value;

    public Resource() {}

    public Resource(String key, String owner, int value) {
        this.key = key;
        this.owner = owner;
        this.value = value;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public String getOwner() {
        return owner;
    }

    public void setOwner(String owner) {
        this.owner = owner;
    }

    public int getValue() {
        return value;
    }

    public void setValue(int value) {
        this.value = value;
    }
}
