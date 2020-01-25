package com.example.nhwltrs.scoutapp2020;

import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;

public class MatchInformation extends AppCompatActivity {

    boolean blueTeam;
    boolean abort = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        //Creates the Match Information activity
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_match_information);

        //Creates the buttons, radiobuttons, and edittexts
        Button startMatchButton = (Button)findViewById(R.id.startMatchButton);
        final EditText matchNumberEditText = (EditText)findViewById(R.id.matchNumberEditText);
        final RadioButton allianceBlue = (RadioButton)findViewById(R.id.allianceBlue);
        final RadioButton allianceRed = (RadioButton)findViewById(R.id.allianceRed);

        //Adds a listener for the Start Match Button
        startMatchButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //Checks to see what alliance color the team is
                if(allianceBlue.isChecked()) {
                    blueTeam = true;
                }

                else if (allianceRed.isChecked()) {
                    blueTeam = false;
                }

                //Checks to make sure a match number is put in
                String checkInput = matchNumberEditText.getText().toString();
                if (checkInput.matches("")) {
                    AlertDialog.Builder builder = new AlertDialog.Builder(MatchInformation.this);

                    builder.setCancelable(false);
                    builder.setTitle("");
                    builder.setMessage("Please input what match number this is");

                    builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int i) {
                            return;
                        }
                    });
                    builder.show();
                    return;
                }

                if (getIntent().hasExtra("Team Number")) {
                    String teamNumberString = getIntent().getExtras().toString();
                    teamNumberString = teamNumberString.replace("Bundle[{Team Number=","");
                    teamNumberString = teamNumberString.replace("}]", "");
                    int teamNumber = Integer.parseInt(teamNumberString);
                    DatabaseClass.createRobotMatch(teamNumber,checkInput,blueTeam);
                    //Bluetooth code goes here
                    Intent matchData = new Intent(getApplicationContext(), MatchData.class);
                    matchData.putExtra("Team Number", teamNumber);
                    abort=true;
                    startActivity(matchData);
                }
            }
        });
    }

    protected void onResume() {

        super.onResume();

        if(abort){
            finish();
        }
    }
}
