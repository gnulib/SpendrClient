package org.trustnet.util;

import android.util.Log;

import org.ethereum.crypto.ECKey;
import org.spongycastle.util.encoders.Base64;
import org.spongycastle.util.encoders.Hex;

import java.security.KeyStore;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * Created by bhadoria on 1/26/19.
 */

public class Submitter {
    private ECKey key = new ECKey();

    private int nextSeq = 1;

    private String lastTx = Hex.toHexString(new byte[64]);

    // default instance
    private static Submitter instance = new Submitter();

    // make constructor private
    private Submitter() {}

    // initialize submitter keys from DB
    public static boolean initialize() {
        // fetch application's private key from android keystore
        // TBD

        // lookup encrypted and persisted submitter key from android DB
        // TBD

        // decrypt submitter key using application's private key from android keystore
        // TBD

        // generate ECKey instance from decrypted value
        // TBD

        return false;
    }

    // use a provider pattern
    public static Submitter instance() {
        return instance;
    }

    // get a hex encoded public ID
    public String getHexPublicId() {
        return Hex.toHexString(key.getPubKey());
    }

    // get a base64 encoded private key
    public String getBase64PrivateKey() {
        return Base64.toBase64String(key.getPrivKeyBytes());
    }

    // get submitter's sequence for next transaction
    public int getNextSeq() {
        return nextSeq;
    }

    // set submitter's last transaction id
    public String getLastTx() {
        return lastTx;
    }

    // update submitter after successful transaction submission
    public void success(String txId) {
        lastTx = txId;
        nextSeq++;
    }

    // sign a payload and return base64 encoded signature
    public String sign(long nonce, byte[] payload) throws NoSuchAlgorithmException {
        // build network byte order nonce
        System.out.printf("Nonce: %d\n", nonce);
        byte[] bytes = new byte[8 + payload.length];
        bytes[0] = (byte)(nonce >> 56);
        System.out.printf("byte: %d\n", bytes[0]);
        bytes[1] = (byte)(nonce >> 48);
        System.out.printf("byte: %d\n", bytes[1]);
        bytes[2] = (byte)(nonce >> 40);
        System.out.printf("byte: %d\n", bytes[2]);
        bytes[3] = (byte)(nonce >> 32);
        System.out.printf("byte: %d\n", bytes[3]);
        bytes[4] = (byte)(nonce >> 24);
        System.out.printf("byte: %d\n", bytes[4]);
        bytes[5] = (byte)(nonce >> 16);
        System.out.printf("byte: %d\n", bytes[5]);
        bytes[6] = (byte)(nonce >> 8);
        System.out.printf("byte: %d\n", bytes[6]);
        bytes[7] = (byte)(nonce);
        System.out.printf("byte: %d\n", bytes[7]);
//        for (int i=8; i>0; i--) {
//            bytes[i-1] = (byte)(nonce % 256);
//            nonce = nonce / 256;
//        }
        // append payload
        System.arraycopy(payload,0, bytes, 8, payload.length);
        // create SHA-256 digest
        MessageDigest digest = MessageDigest.getInstance("SHA-256");
        // get signature using private key
        return key.sign(digest.digest(bytes)).toBase64();
    }
}
