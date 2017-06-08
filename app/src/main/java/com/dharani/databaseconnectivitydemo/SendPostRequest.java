package com.dharani.databaseconnectivitydemo;

import android.content.Context;
import android.os.AsyncTask;
import android.util.Log;
import android.widget.Toast;

import org.json.JSONArray;

import java.io.IOException;

import okhttp3.Call;
import okhttp3.FormBody;
import okhttp3.HttpUrl;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

/*
 reference link : http://androidcss.com/android/test-android-app-php-localhost-wamp/
 */

public class SendPostRequest extends AsyncTask<String,String,String> {
    Context context;
    String name;
    String address;
    String Url = "http://192.168.0.19/phpdemo/insert.php";
//    String retrieveUrl = "http://10.0.0.100/phpdemo/retrieve.php";

    public SendPostRequest(Context context,String name,String address){
        this.context = context;
        this.name = name;
        this.address = address;
    }
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
                .add("name",name)
                .add("address",address)
                .build();


        //An HTTP request. Instances of this class are immutable if their body is null or itself immutable.
        Request request = new Request.Builder()
                .url(Url)
                .post(body)
                .build();


        //A call is a request that has been prepared for execution.
        // A call can be canceled. As this object represents a single request/response pair (stream), it cannot be executed twice.
        Call call = client.newCall(request);
        try {
            response = call.execute();
            if (response.isSuccessful()){
                jsondata = response.body().string();
                Log.d("DATA",jsondata);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return jsondata;
    }




    @Override
    protected void onPostExecute(String s) {
        super.onPostExecute(s);

    }
}
