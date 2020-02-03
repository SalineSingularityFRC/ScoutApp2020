package com.example.nhwltrs.scoutapp2020;

/**
 * Created by nhwlt on 1/10/2020.
 */

import android.content.Context;
import android.util.Log;

import org.json.*;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;

public class DatabaseClass {

    public static BluetoothClass bluetooth;
    private static JSONObject tempRobotMatchData;
    public static JSONArray robotMatchData = new JSONArray();
    private static JSONArray tempTeamData = new JSONArray();
    private static JSONArray teamData;
    public static String tag = "7G7 Bluetooth";



    public static void setup(BluetoothClass bluetooth) {
        DatabaseClass.bluetooth=bluetooth;

        ArrayList jsonObjectArray = new ArrayList();
        String currentJSONString = "";

        try {
            FileInputStream fis = new FileInputStream("teamData");
            //FileInputStream fis = bluetooth.activity.openFileInput("teamData");
            Log.i(tag, fis.toString() + "got the input string");
            teamData = new JSONArray(fis.read());
            /*while( (currentJSONString = fis.read()) != null) {
                JSONObject currentObject = new JSONObject(currentJSONString);

                jsonObjectArray.add(currentObject);
            }*/
            Log.i(tag, "finished the fis.read()");
            fis.close();
        } catch (FileNotFoundException e) {
            try {
                FileOutputStream fos = bluetooth.activity.openFileOutput("teamData", Context.MODE_PRIVATE);
                fos.write("".getBytes());
                fos.close();
                FileInputStream fis = bluetooth.activity.openFileInput("teamData");
                teamData=new JSONArray(fis.read());
                fis.close();
            } catch (IOException | JSONException e1) {
                e1.printStackTrace();
            }

        } catch (JSONException | IOException e) {
            e.printStackTrace();
            Log.i(tag, "caught JSONException");
        }
    }

    public static void makeTeam(int teamNumber, String teamName){
        try {
            tempTeamData.put(new JSONObject("{"+
                    "\"team\":" + teamNumber + "," +
                    "\"name\":\"" + teamName + "\"" +
                    "}"));
            send();
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    public static int getTeamNumber(int index){
        try {
            return teamData.getJSONObject(index).getInt("team");
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return -1;
    }

    public static String getTeamName(int index) {
        try {
            return teamData.getJSONObject(index).getString("name");
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return "Error";
    }

    public static int getTeamDatabaseLength(){
        return teamData.length();
    }

    public static int getLocalTeamNumber(int index) {
        try {
            return tempTeamData.getJSONObject(index).getInt("team");
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return -1;
    }

    public static String getLocalTeamName(int index) {
        try {
            return tempTeamData.getJSONObject(index).getString("name");
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return "Error";
    }

    public static int getLocalTeamDatabaseLength(){
        return tempTeamData.length();
    }

    public static void dataSent(String data){
        try {
            teamData=new JSONArray(data);
            FileOutputStream fos = bluetooth.activity.openFileOutput("teamData", bluetooth.activity.MODE_PRIVATE);
            fos.write(teamData.toString().getBytes());
            fos.close();
        }catch (JSONException | IOException e){
            e.printStackTrace();
            return;
        }
        robotMatchData= new JSONArray();
        tempTeamData= new JSONArray();

    }

    private static void send(){
        bluetooth.send("{\"matchData\":"+ robotMatchData.toString()+",\"teamData\":"+tempTeamData.toString()+"}");
        // For the newest and greatest SSSS.py
        bluetooth.send_byte(new byte[] {'\0'});
    }

    public static void createRobotMatch(int teamNumber, String match, boolean onBlue) {
        try {
            tempRobotMatchData = new JSONObject("{" +
                    "\"team\":" + teamNumber + "," +
                    "\"matchID\":\"" + match + "\"," +
                    "\"onBlue\":"+onBlue+"," +
                    "\"startingPos\":Left," +
                    "\"autonMove\":false," +
                    "\"autonBottom\":0," +
                    "\"autonOuter\":0," +
                    "\"autonInner\":0," +
                    "\"teleopBottom\":0," +
                    "\"teleopOuter\":0," +
                    "\"teleopInner\":0," +
                    "\"rotationControl\":false," +
                    "\"positionControl\":false," +
                    "\"hang\":false," +
                    "\"level\":false," +
                    "\"floorCollection\":false," +
                    "\"trench\":false," +
                    "\"upperBay\":0," +
                    "\"lowerBay\":0," +
                    "}");
        } catch(JSONException e){
            e.printStackTrace();
        }
    }

    public static void setStartingPos(String pos){
        try{
            tempRobotMatchData.put("startingPos", pos);
        } catch(JSONException e){
            e.printStackTrace();
        }
    }

    public static void setAutonMove(boolean move) {
        try{
            tempRobotMatchData.put("autonMove", move + "");
        } catch (JSONException e) {
            e.printStackTrace();
        }

    }

    public static void setAutonBottom(int num) {
        try {
            tempRobotMatchData.put("autonBottom", num + "");
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    public static void setAutonOuter(int num) {
        try {
            tempRobotMatchData.put("autonOuter",num + "");
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    public static void setAutonInner(int num) {
        try {
            tempRobotMatchData.put("autonInner",num + "");
        } catch(JSONException e) {
            e.printStackTrace();
        }
    }

    public static void setTeleopBottom(int num) {
        try {
            tempRobotMatchData.put("teleopBottom", num + "");
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    public static void setTeleopOuter(int num) {
        try {
            tempRobotMatchData.put("teleopOuter", num + "");
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    public static void setTeleopInner(int num) {
        try {
            tempRobotMatchData.put("teleopInner", num + "");
        } catch(JSONException e) {
            e.printStackTrace();
        }
    }

    public static void setRotationControl(boolean rot) {
        try {
            tempRobotMatchData.put("rotationControl", rot + "");
        } catch(JSONException e) {
            e.printStackTrace();
        }
    }

    public static void setPositionControl(boolean pos) {
        try {
            tempRobotMatchData.put("positionControl", pos + "");
        } catch(JSONException e) {
            e.printStackTrace();
        }
    }

    public static void setHang(boolean hang) {
        try {
            tempRobotMatchData.put("hang", hang + "");
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    public static void setLevel(boolean lvl) {
        try {
            tempRobotMatchData.put("level", lvl + "");
        } catch(JSONException e) {
            e.printStackTrace();
        }
    }

    public static void setFloorCollection(boolean col) {
        try {
            tempRobotMatchData.put("floorCollection", col + "");
        } catch(JSONException e) {
            e.printStackTrace();
        }
    }

    public static void setTrench(boolean cap) {
        try {
            tempRobotMatchData.put("trench", cap + "");
        } catch(JSONException e) {
            e.printStackTrace();
        }
    }

    public static void setUpperBay(int num) {
        try {
            tempRobotMatchData.put("upperBay", num + "");
        } catch(JSONException e) {
            e.printStackTrace();
        }
    }

    public static void setLowerBay(int num) {
        try {
            tempRobotMatchData.put("lowerBay", num + "");
        } catch(JSONException e) {
            e.printStackTrace();
        }
    }

    public static void finishMatch(){
        if(tempRobotMatchData==null){
            return;
        }
        robotMatchData.put(tempRobotMatchData);
        tempRobotMatchData=null;
        send();
    }
}
