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
    ListView list;
    String id,name,address;
    String urlRetrieve = "http://www.silverlightsystems.co.uk/android/json.php";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_retrieve_data);
        //tvResult = (TextView) findViewById(R.id.txtRetrieve);
        list = (ListView) findViewById(R.id.mylists);
        new RetrieveClass().execute();

    }

    class RetrieveClass extends AsyncTask<String, Void, String> {

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
        }

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
                else {
                    Toast.makeText(RetrieveData.this, "Check connection", Toast.LENGTH_SHORT).show();
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
            // String id,name,address;
            try {
                JSONObject jsonObject = new JSONObject(jsondata);
                Log.e("JSONObject", "Testing" + jsonObject);
                JSONArray jsonArray = jsonObject.getJSONArray("result");
                for (int i = 0; i < jsonArray.length(); i++) {
                    JSONObject object = jsonArray.getJSONObject(i);
                    id = object.getString("id");
                    name = object.getString("name");
                    address = object.getString("address");
                    al.add(id);
                    al.add(name);
                    al.add(address);
                   //Toast.makeText(RetrieveData.this, "ID : " + id + "\n" + "Name : " + name + "\n" + "Address : "+address, Toast.LENGTH_SHORT).show();
                }
            ListAdapter adapter = new ListAdapter(getApplicationContext(),al);
            list.setAdapter(adapter);

            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
    }
}
