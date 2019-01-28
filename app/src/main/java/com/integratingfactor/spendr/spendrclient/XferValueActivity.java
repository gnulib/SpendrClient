package com.integratingfactor.spendr.spendrclient;

import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.trustnet.api.client.SpendrClient;
import org.trustnet.api.dto.Resource;

public class XferValueActivity extends AppCompatActivity {

    TextView result;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_xfer_value);
        result = findViewById(R.id.xfer_result);
    }

    private class SubmitXferRequest extends AsyncTask<String, Integer, ResponseEntity<? extends Object>> {


        @Override
        protected ResponseEntity<? extends Object> doInBackground(String... strings) {
            String source = strings[0];
            String destination = strings[1];
            Integer value = Integer.valueOf(strings[2], 10);
            return SpendrClient.instance().xferValue(source, destination, value);
        }

        @Override
        protected void onPostExecute(ResponseEntity<? extends Object> response) {
            result.setText(response.getBody().toString());
        }
    }

    public void submit(View view) {
        // find the transfer parameters
        EditText xferSource = findViewById(R.id.xfer_source);
        EditText xferDestination = findViewById(R.id.xfer_destination);
        EditText xferValue = findViewById(R.id.xfer_value);

        // submit to background task for fetch and display
        new XferValueActivity.SubmitXferRequest().execute(xferSource.getText().toString(),
                xferDestination.getText().toString(),
                xferValue.getText().toString());
    }
}
