package com.example.nhwltrs.scoutapp2020;

//import android.bluetooth.BluetoothClass;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.nhwltrs.scoutapp2020.R;

public class BeginningScreen extends AppCompatActivity {
    public BluetoothClass bluetooth= new BluetoothClass(this);
    private boolean started=false;
    private static String tag = "7G7 Bluetooth";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        //Create the page and set the layout to the page
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_beginning_screen);

        //Create all the buttons
        Button newMatch = (Button)findViewById(R.id.inputDataButton);
        Button goodButton = (Button)findViewById(R.id.goodButton);

        final ImageView goodImage = (ImageView)findViewById(R.id.goodImageView2);

        //Set the listeners for the buttons
        newMatch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.i(tag, "Pressed the new game button");
                Intent teams = new Intent(getApplicationContext(), Teams.class);
                startActivity(teams);
            }
        });

        /*goodButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                goodImage.setVisibility(View.VISIBLE);
            }
        });*/

        Log.i(tag, "Setting up database");
        DatabaseClass.setup(bluetooth);
        Log.i(tag, "Database set up!");

        //Bluetooth code will go here

        final TextView versionNumber = (TextView)findViewById(R.id.versionNumber);
        versionNumber.setText("Version 1.0");
    }

    @Override
    protected void onStart() {
        super.onStart();
        Log.i(tag, "Started the beginning screen");

        bluetooth.setup();
        if(!started){
            bluetooth.send("{\"teamData\":[],\"matchData\":[]}");
            //bluetooth.send_byte(new byte[] {'\0'});
            //bluetooth.set_pending_data("{\"teamData\":[],\"matchData\":[]}");
            started=true;
        }

        //Bluetooth code will go here
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.i(tag, "Destroyed the beginning screen");
        //More bluetooth code
        bluetooth.end();
    }


}
