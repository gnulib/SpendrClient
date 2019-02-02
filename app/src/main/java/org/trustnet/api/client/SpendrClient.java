package org.trustnet.api.client;

import android.util.Base64;
import android.util.Log;

import org.springframework.web.client.RestClientException;
import org.trustnet.api.dto.Resource;
import org.trustnet.api.dto.SubmitRequest;
import org.trustnet.api.dto.XferValue;
import org.trustnet.util.Submitter;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.web.client.RestTemplate;
import org.trustnet.api.dto.Anchor;
import org.trustnet.api.dto.AnchorRequest;
import org.trustnet.api.dto.Opcode;
import org.trustnet.api.dto.SubmitResult;
import org.trustnet.api.dto.Transaction;

/**
 * Client library for spendr test application
 * Created by bhadoria on 1/26/19.
 */

public class SpendrClient {
    private static final Logger logger = LoggerFactory.getLogger(SpendrClient.class);

    private RestTemplate restTemplate;
    private static String baseUrl;

    static private SpendrClient client;

    static {
        Submitter.initialize("test-driver-for-double-spending");
    }
    static public void setBaseUrl(String url) {
        baseUrl = url;
    }

    static public String getBaseUrl() {
        return baseUrl;
    }

    static public SpendrClient instance() {
        if (client == null) {
            client = new SpendrClient();
        }
        return client;
    }

    private SpendrClient() {
        this.restTemplate = new RestTemplate();
        // Add the String message converter
        restTemplate.getMessageConverters().add(new MappingJackson2HttpMessageConverter());
    }

    public ResponseEntity<? extends Object> getResource(String key) {
        try {
            return restTemplate.getForEntity(baseUrl + "/resources/" + key, Resource.class);
        } catch (Exception e) {
            return new ResponseEntity<Object>(e.getMessage(), HttpStatus.SERVICE_UNAVAILABLE);
        }
    }

    public ResponseEntity<? extends Object> submitTransaction(Opcode payload) {
        try {
            // create a transaction request
            SubmitRequest txRequest = Submitter.instance().newRequest(payload.getPayload());
            Log.d("Submitting Request", txRequest.toString());

            // submit transaction
            ResponseEntity<SubmitResult> response = restTemplate.postForEntity(baseUrl + "/transactions",
                    txRequest,
                    SubmitResult.class);
            logger.debug("Submit response: {}", response.toString());
            Log.d("Submit Response", response.toString());

            // for success case, update submitter client with result
            if (response.getStatusCode() == HttpStatus.CREATED || response.getStatusCode() == HttpStatus.OK) {
                Submitter.instance().success(response.getBody().getTxId());
            }

            // pass back result
            return response;
        } catch (Exception e) {
            return new ResponseEntity<Object>(e.getMessage(), HttpStatus.SERVICE_UNAVAILABLE);
        }
    }

    public ResponseEntity<? extends Object> createResource(String key, Integer value) {
        try {
            // fetch the payload for creating a resource
            Opcode payload = restTemplate.postForEntity(baseUrl + "/opcode/create",
                    new Resource(key, Submitter.instance().getHexPublicId(), value.intValue()),
                    Opcode.class).getBody();
            logger.debug("Got payload: {}", payload.toString());
            Log.d("Got payload", payload.toString());

            // submit transaction using the payload
            return submitTransaction(payload);
        } catch (Exception e) {
            return new ResponseEntity<Object>(e.getMessage(), HttpStatus.SERVICE_UNAVAILABLE);
        }
    }

    public ResponseEntity<? extends Object> xferValue(String source, String destination, Integer value) {
        try {
            // fetch the payload for transferring value
            Opcode payload = restTemplate.postForEntity(baseUrl + "/opcode/xfer",
                    new XferValue(source, destination, value.intValue()),
                    Opcode.class).getBody();
            logger.debug("Got payload: {}", payload.toString());
            Log.d("Got payload", payload.toString());

            // submit transaction using the payload
            return submitTransaction(payload);
        } catch (Exception e) {
            return new ResponseEntity<Object>(e.getMessage(), HttpStatus.SERVICE_UNAVAILABLE);
        }
    }

}
