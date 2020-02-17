package com.example.nhwltrs.scoutapp2020;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.content.DialogInterface;
import android.support.v7.app.AlertDialog;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class NewTeam extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        //Create the New Team activity
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_team);

        //Created the buttons and EditTexts
        Button createTeam = (Button) findViewById(R.id.createTeamButton);
        final EditText teamName = (EditText) findViewById(R.id.teamNameEditText);
        final EditText teamNumber = (EditText) findViewById(R.id.teamNumberEditText);

        //Put a listener on the CreateTeam button
        createTeam.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //Puts name and number into strings
                String checkTeamName = teamName.getText().toString();
                String checkTeamNumber = teamNumber.getText().toString();

                //Checks to make sure a team name has been inputed
                if (checkTeamName.matches("")) {
                    AlertDialog.Builder builder = new AlertDialog.Builder(NewTeam.this);

                    builder.setCancelable(false);
                    builder.setTitle("Error");
                    builder.setMessage("Please input a team name");

                    builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int i) {
                            return;
                        }
                    });
                    builder.show();
                    return;
                }

                //Checks to make sure a team number has been inputted
                if (checkTeamNumber.matches("")) {
                    AlertDialog.Builder builder = new AlertDialog.Builder(NewTeam.this);

                    builder.setCancelable(false);
                    builder.setTitle("Error");
                    builder.setMessage("Please input a valid team number");

                    builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int i) {
                            return;
                        }
                    });

                    builder.show();
                    return;
                }

                //Bluetooth code goes here
                DatabaseClass.makeTeam(Integer.parseInt(checkTeamNumber),checkTeamName);
                finish();
            }
        });
    }
}
