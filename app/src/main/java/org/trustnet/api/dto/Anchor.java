package org.trustnet.api.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Created by bhadoria on 1/27/19.
 */

public class Anchor {
    @JsonProperty("submitter")
    String submitter;

    @JsonProperty("last_tx")
    String lastTx;

    @JsonProperty("submitter_seq")
    int submitterSeq;

    @JsonProperty("node_id")
    String nodeId;

    @JsonProperty("shard_id")
    String shardId;

    @JsonProperty("shard_seq")
    long shardSeq;

    @JsonProperty("weight")
    long weight;

    @JsonProperty("shard_parent")
    String shardParent;

    @JsonProperty("shard_uncles")
    String []shardUncles;

    @JsonProperty("anchor_signature")
    String signature;

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

    public int getsubmitterSeq() {
        return submitterSeq;
    }

    public void setsubmitterSeq(int submitterSeq) {
        this.submitterSeq = submitterSeq;
    }

    public String getNodeId() {
        return nodeId;
    }

    public void setNodeId(String nodeId) {
        this.nodeId = nodeId;
    }

    public String getShardId() {
        return shardId;
    }

    public void setShardId(String shardId) {
        this.shardId = shardId;
    }

    public long getShardSeq() {
        return shardSeq;
    }

    public void setShardSeq(long shardSeq) {
        this.shardSeq = shardSeq;
    }

    public long getWeight() {
        return weight;
    }

    public void setWeight(long weight) {
        this.weight = weight;
    }

    public String getShardParent() {
        return shardParent;
    }

    public void setShardParent(String shardParent) {
        this.shardParent = shardParent;
    }

    public String getSignature() {
        return signature;
    }

    public void setSignature(String signature) {
        this.signature = signature;
    }

    public String[] getShardUncles() {
        return shardUncles;
    }

    public void setShardUncles(String[] shardUncles) {
        this.shardUncles = shardUncles;
    }

    @Override
    public String toString() {
        return "Anchor{" +
                "submitter='" + submitter + '\'' +
                ", lastTx='" + lastTx + '\'' +
                ", submitterSeq=" + submitterSeq +
                ", nodeId='" + nodeId + '\'' +
                ", shardId='" + shardId + '\'' +
                ", shardSeq=" + shardSeq +
                ", weight='" + weight + '\'' +
                ", shardParent='" + shardParent + '\'' +
                ", signature='" + signature + '\'' +
                '}';
    }
}
