package org.trustnet.api.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Created by bhadoria on 1/27/19.
 */

public class Opcode {
    @JsonProperty("payload")
    String payload;

    @JsonProperty("description")
    String desc;

    public String getPayload() {
        return payload;
    }

    public void setPayload(String payload) {
        this.payload = payload;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    @Override
    public String toString() {
        return "Opcode{" +
                "payload='" + payload + '\'' +
                ", desc='" + desc + '\'' +
                '}';
    }
}
