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

public class ShowResourceActivity extends AppCompatActivity {

    TextView owner;
    TextView value;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_resource);

        // find the text views
        owner = findViewById(R.id.res_owner_val);
        value = findViewById(R.id.res_value_val);
    }

    private class FetchResourceInfo extends AsyncTask<String, Integer, ResponseEntity<? extends Object>> {

        @Override
        protected ResponseEntity<? extends Object> doInBackground(String... strings) {
            return SpendrClient.instance().getResource(strings[0]);
        }

        @Override
        protected void onPostExecute(ResponseEntity<? extends Object> response) {
            if (response.getStatusCode() != HttpStatus.OK) {
                value.setText("n/a");
                owner.setText(response.getBody().toString());
            } else {
                Resource resource = (Resource) response.getBody();
                value.setText(String.valueOf(resource.getValue()));
                owner.setText(resource.getOwner());
            }
        }
    }

    public void showResource(View view) {
        // find the resource key
        EditText resourceKey = findViewById(R.id.resource_key);

        // submit to background task for fetch and display
        new FetchResourceInfo().execute(resourceKey.getText().toString());
    }
}
