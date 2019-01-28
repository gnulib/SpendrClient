package org.trustnet.api.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Created by bhadoria on 1/27/19.
 */

public class AnchorRequest {
    @JsonProperty("submitter")
    String submitter;

    @JsonProperty("last_tx")
    String lastTx;

    @JsonProperty("next_seq")
    int nextSeq;

    public AnchorRequest(String submitter, String lastTx, int nextSeq) {
        this.submitter = submitter;
        this.lastTx = lastTx;
        this.nextSeq = nextSeq;
    }

    public String getSubmitter() {
        return submitter;
    }

    public void setSubmitter(String submitter) {
        this.submitter = submitter;
    }

    public String getLastTx() {
        return lastTx;
    }

    public void setLastTx(String lastTx) {
        this.lastTx = lastTx;
    }

    public int getNextSeq() {
        return nextSeq;
    }

    public void setNextSeq(int nextSeq) {
        this.nextSeq = nextSeq;
    }
}
