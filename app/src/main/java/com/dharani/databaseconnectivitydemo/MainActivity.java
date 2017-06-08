package com.dharani.databaseconnectivitydemo;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;


public class MainActivity extends AppCompatActivity {

    private EditText etName, etAddress;
    private Button btInsert,btRetrieve;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        etName = (EditText) findViewById(R.id.editText);
        etAddress = (EditText) findViewById(R.id.editText2);
        btInsert = (Button) findViewById(R.id.button);
        btRetrieve = (Button)findViewById(R.id.button2);

        btInsert.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String name = etName.getText().toString();
                String address = etAddress.getText().toString();

                new SendPostRequest(MainActivity.this, name, address).execute();
            }
        });

        btRetrieve.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this,RetrieveData.class);
                startActivity(intent);
            }
        });
    }
}

