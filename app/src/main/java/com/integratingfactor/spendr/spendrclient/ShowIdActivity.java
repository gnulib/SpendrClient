package com.integratingfactor.spendr.spendrclient;

import android.graphics.Bitmap;
import android.graphics.Color;
import android.graphics.drawable.BitmapDrawable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.trustnet.util.Submitter;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.WriterException;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.qrcode.QRCodeWriter;

import static android.graphics.Bitmap.Config.ARGB_8888;

public class ShowIdActivity extends AppCompatActivity {
    private static final Logger logger = LoggerFactory.getLogger(ShowIdActivity.class);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_id);

        // find the text view for displaying public ID
        TextView publicId = findViewById(R.id.publicId);
        publicId.setText(Submitter.instance().getHexPublicId());

        // build the bitmap QR for private key
        QRCodeWriter qrCodeWriter = new QRCodeWriter();
        Bitmap b = Bitmap.createBitmap(320, 320, ARGB_8888);
        try {
            BitMatrix bitMatrix = null;
            bitMatrix = qrCodeWriter.encode(Submitter.instance().getHexPublicId(), BarcodeFormat.QR_CODE, 320, 320);
            for (int x = 0; x < 320; x++) {
                for (int y = 0; y < 320; y++) {
                    b.setPixel(x,y,bitMatrix.get(x,y) ? Color.BLACK : Color.WHITE);
                }
            }
        } catch (WriterException e) {
            logger.error("Failed to fetch private key: {}", e.getMessage());
            int flip = 5;
            int current = Color.BLACK;
            int next = Color.WHITE;
            for (int x = 0; x < 320; x++) {
                for (int y = 0; y < 320; y++) {
                    b.setPixel(x,y,current);
                    flip--;
                    if (flip == 0) {
                        flip = 5;
                        int temp = current;
                        current = next;
                        next = temp;
                    }
                }
            }
        }
        // find the image view for displaying private key as QR code
        ImageView keyQR = findViewById(R.id.keyQR);
        keyQR.setImageDrawable(new BitmapDrawable(getResources(), b));
    }
}
