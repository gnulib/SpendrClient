package com.integratingfactor.spendr.spendrclient;

import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import org.springframework.http.ResponseEntity;
import org.trustnet.api.client.SpendrClient;

public class CreateResourceActivity extends AppCompatActivity {

    TextView result;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_resource);
        result = findViewById(R.id.create_resource_result);
    }

    public void submitCreateResource(View view) {
        // find the resource creation parameters
        EditText resourceKey = findViewById(R.id.create_resource_key);
        EditText resourceValue = findViewById(R.id.create_resource_value);

        // submit to background task for fetch and display
        new CreateResource().execute(resourceKey.getText().toString(), resourceValue.getText().toString());
    }

    private class CreateResource extends AsyncTask<String, Integer, ResponseEntity<? extends Object>> {

        @Override
        protected ResponseEntity<? extends Object> doInBackground(String... strings) {
            String key = strings[0];
            Integer value = Integer.valueOf(strings[1], 10);
            return SpendrClient.instance().createResource(key, value);
        }

        @Override
        protected void onPostExecute(ResponseEntity<? extends Object> response) {
            result.setText(response.getBody().toString());
        }
    }
}
