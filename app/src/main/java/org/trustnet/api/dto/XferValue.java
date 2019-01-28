package org.trustnet.api.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Created by bhadoria on 1/27/19.
 */

public class XferValue {

    @JsonProperty("source")
    String source;

    @JsonProperty("destination")
    String destination;

    @JsonProperty("value")
    int value;

    public XferValue() {}

    public XferValue(String source, String destination, int value) {
        this.source = source;
        this.destination = destination;
        this.value = value;
    }

    public String getSource() {
        return source;
    }

    public void setSource(String source) {
        this.source = source;
    }

    public String getDestination() {
        return destination;
    }

    public void setDestination(String destination) {
        this.destination = destination;
    }

    public int getValue() {
        return value;
    }

    public void setValue(int value) {
        this.value = value;
    }
}
