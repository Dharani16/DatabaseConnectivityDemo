package com.dharani.databaseconnectivitydemo;

import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import okhttp3.Call;
import okhttp3.FormBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

public class RetrieveData extends AppCompatActivity {

    TextView tvResult;
    String urlRetrieve = "http://www.silverlightsystems.co.uk/android/json.php";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_retrieve_data);
        //tvResult = (TextView) findViewById(R.id.txtRetrieve);

        new RetrieveClass().execute();

    }

    class RetrieveClass extends AsyncTask<String, Void, String> {
//
//        @Override
//        protected void onPreExecute() {
//            super.onPreExecute();
//        }

        @Override
        protected String doInBackground(String... params) {

            RequestBody body;
            Response response;
            String jsondata = null;

            OkHttpClient client = new OkHttpClient();

            body = new FormBody.Builder()
                    //change it according to your requirement
                    // am getting two input from user one is name, another one is address
                    .build();
            //An HTTP request. Instances of this class are immutable if their body is null or itself immutable.
            Request request = new Request.Builder()
                    .url(urlRetrieve)
                    .post(body)
                    .build();
            //A call is a request that has been prepared for execution.
            // A call can be canceled. As this object represents a single request/response pair (stream), it cannot be executed twice.
            Call call = client.newCall(request);
            try {
                response = call.execute();
                if (response.isSuccessful()) {
                    jsondata = response.body().string();
                    Log.d("DATA", jsondata);
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
            return jsondata;
        }

        @Override
        protected void onPostExecute(String jsondata) {
            //super.onPostExecute(s);
            ArrayList<String> al = new ArrayList<>();
            try {
                JSONObject jsonObject = new JSONObject(jsondata);
                Log.e("JSONObject", "Testing" + jsonObject);
                JSONArray jsonArray = jsonObject.getJSONArray("result");
                for (int i = 0; i < jsonArray.length(); i++) {
                    JSONObject object = jsonArray.getJSONObject(i);
                    String id = object.getString("id");
                    String name = object.getString("name");
                    String address = object.getString("address");

                    al.add(id);
//
                    //tvResult.setText(id + "\n" + name + "\n" + address);
                    Toast.makeText(RetrieveData.this, "ID : " + id + "\n" + "Name : " + name, Toast.LENGTH_SHORT).show();
                }
                ArrayAdapter<String> madapter = new ArrayAdapter<String>(getApplicationContext(), android.R.layout.simple_list_item_1, al);
                ListView l = (ListView) findViewById(R.id.mylists);
                l.setAdapter(madapter);
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
    }
}
