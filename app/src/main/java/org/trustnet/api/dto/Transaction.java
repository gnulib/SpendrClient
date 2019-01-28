package org.trustnet.api.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Created by bhadoria on 1/27/19.
 */

public class Transaction {
    @JsonProperty("anchor")
    Anchor anchor;

    @JsonProperty("payload")
    String payload;

    @JsonProperty("tx_signature")
    String signature;

    public Transaction() {}

    public Transaction(Anchor anchor, String payload, String signature) {
        this.anchor = anchor;
        this.payload = payload;
        this.signature = signature;
    }

    public Anchor getAnchor() {
        return anchor;
    }

    public void setAnchor(Anchor anchor) {
        this.anchor = anchor;
    }

    public String getPayload() {
        return payload;
    }

    public void setPayload(String payload) {
        this.payload = payload;
    }

    public String getSignature() {
        return signature;
    }

    public void setSignature(String signature) {
        this.signature = signature;
    }
}
