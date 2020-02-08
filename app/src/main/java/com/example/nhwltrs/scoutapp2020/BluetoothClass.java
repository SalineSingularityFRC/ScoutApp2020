package com.example.nhwltrs.scoutapp2020;

import android.bluetooth.BluetoothDevice;
import android.os.Handler;
import android.util.Log;

import com.example.nhwltrs.scoutapp2020.BeginningScreen;
import com.example.nhwltrs.scoutapp2020.DatabaseClass;

import bluetooth.Bluetooth;
import bluetooth.CommunicationCallback;

/**
 * Created by nhwlt on 1/19/2019.
 */

public class BluetoothClass {
    private Bluetooth bluetooth;
    public BeginningScreen activity;
    private boolean setup = false;
    private Handler handler = new Handler();
    private static String tag = "7G7 Bluetooth";
    private static String macAddress;
    private static String match = "B8:27:EB:E8:64:53"; //Put the bluetooth address of you Pi server here
    private String pendingData="";

    private CommunicationCallback CCB = new CommunicationCallback() {
        @Override
        public void onConnect(BluetoothDevice device) {
            Log.i(tag, "Connected to device " + device.getName() + " at " + device.getAddress() + "!");
            bluetooth.send(pendingData);
        }

        @Override
        public void onDisconnect(BluetoothDevice device, String message) {
            Log.i(tag,"Disconnected from device " + device.getName() + " at " + device.getAddress() + "!");
            if(pendingData.length()!=0) {
                reconnect();
            }
        }

        @Override
        public void onMessage(String message) {
            pendingData="";
            DatabaseClass.dataSent(message);
            Log.i(tag,"Data transfer complete!");
        }

        @Override
        public void onError(String message) {
            Log.e(tag,"Generic error: " + message);
        }

        @Override
        public void onConnectError(BluetoothDevice device, String message) {
            Log.e(tag,"Failed to connect: " + message);
            reconnect();
        }
    };

    public BluetoothClass (BeginningScreen a){
        activity=a;
        bluetooth = new Bluetooth(activity);
        bluetooth.setCommunicationCallback(CCB);
    }

    public void setup(){
        if(!setup) {
            bluetooth.onStart();
        }
        if(!bluetooth.isEnabled()){
            bluetooth.enable();
        }
        setup=true;
    }

    private void reconnect(){
        handler.postDelayed(new Runnable() {
                @Override
                public void run() {
                    bluetooth.connectToAddress(match);
                }
            },3000);
    }

    public void send(String data){
        Log.i(tag,"Entered the send method in bluetooth");
        if(pendingData.length()==0)
            bluetooth.connectToAddress(match);
        //pendingData+=data;
        pendingData=data;
    }

    public void send_byte(byte[] data) {
        if (pendingData.length() == 0)
            bluetooth.connectToAddress(match);

        pendingData = new String(data);
    }

    public void end(){
        bluetooth.onStop();
    }

}
