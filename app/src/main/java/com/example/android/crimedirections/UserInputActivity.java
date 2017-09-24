package com.example.android.crimedirections;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.Editable;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class UserInputActivity extends AppCompatActivity {


    EditText lat1Input;
    EditText lat2Input;
    EditText long1Input;
    EditText long2Input;

    Button enterBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_input);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        lat1Input = (EditText) findViewById(R.id.latitude1);
        lat2Input = (EditText) findViewById(R.id.latitude2);
        long1Input = (EditText) findViewById(R.id.longitude1);
        long2Input = (EditText) findViewById(R.id.longitude2);

        enterBtn = (Button) findViewById(R.id.enterBtn);

        enterBtn.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v){
                Intent i = new Intent(UserInputActivity.this,MapsActivity.class);
                i.putExtra("lat1", lat1Input.getText().toString());
                i.putExtra("long1", long1Input.getText().toString());
                i.putExtra("lat2", lat2Input.getText().toString());
                i.putExtra("long2", long2Input.getText().toString());
                startActivity(i);
            }
        });

    }

}
