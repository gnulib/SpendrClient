package com.integratingfactor.spendr.spendrclient;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;

import org.trustnet.api.client.SpendrClient;
import org.trustnet.util.Submitter;

public class MainActivity extends AppCompatActivity {

    EditText urlInput;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        urlInput = findViewById(R.id.server_url);
        Log.d("MainActivity", "onCreate with url: " + SpendrClient.getBaseUrl());
    }
    @Override
    protected void onPause() {
        super.onPause();
        Log.d("MainActivity", "onPause with url: " + SpendrClient.getBaseUrl());
    }

    @Override
    protected void onStop() {
        super.onStop();
        Log.d("MainActivity", "onStop with url: " + SpendrClient.getBaseUrl());
    }

    @Override
    protected void onResume() {
        super.onResume();
        Log.d("MainActivity", "onResume with url: " + SpendrClient.getBaseUrl());
        if (SpendrClient.getBaseUrl() != null) {
            urlInput.setText(SpendrClient.getBaseUrl());
        }
    }

    public void showId(View view) {
        Intent intent = new Intent(this, ShowIdActivity.class);
        startActivity(intent);
    }

    public void showResource(View view) {
        Intent intent = new Intent(this, ShowResourceActivity.class);
        startActivity(intent);
    }

    public void createResource(View view) {
        Intent intent = new Intent(this, CreateResourceActivity.class);
        startActivity(intent);
    }

    public void xferValue(View view) {
        Intent intent = new Intent(this, XferValueActivity.class);
        startActivity(intent);
    }

    public void updateUrl(View view) {
        // get url from input
        if (urlInput.getText().length() == 0) {
            urlInput.setText(SpendrClient.getBaseUrl());
        } else {
//            SpendrClient.setBaseUrl("http://192.168.1.10:1055");
            SpendrClient.setBaseUrl(urlInput.getText().toString());
        }

    }
}
