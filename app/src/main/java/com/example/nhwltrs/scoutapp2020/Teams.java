package com.example.nhwltrs.scoutapp2020;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.TextView;

import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class Teams extends AppCompatActivity {

    ListView list;
    TextView debug;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        //Create the Teams activity
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_teams);

        //Create the button and list
        Button newTeam = (Button) findViewById(R.id.newTeamsButton);
        list = (ListView) findViewById(R.id.teamsListView);

        //Set a listener for the items in the list
        list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                String teamNumber = ((TextView) view.findViewById(R.id.teamNumberTextView)).getText().toString();
                //Match information code goes here
                Intent matchInformation = new Intent(getApplicationContext(), MatchInformation.class);
                matchInformation.putExtra("Team Number", teamNumber);
                startActivity(matchInformation);
                finish();
            }
        });
        //Set a listener for the newTeam button
        newTeam.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //Start New Team activity
                Intent newTeam = new Intent(getApplicationContext(), NewTeam.class);
                startActivity(newTeam);
            }
        });



    }

    protected void onResume() {
        super.onResume();

        //Formatting for the list
        List<HashMap<String, String>> listItems = new ArrayList<>();
        SimpleAdapter adapter = new SimpleAdapter(this, listItems, R.layout.list_item, new String[]{"First Line", "Second Line"}, new int[]{R.id.teamNameTextView, R.id.teamNumberTextView});

        list.setAdapter(adapter);

        //Bluetooth code goes here
        for(int i=0;i<DatabaseClass.getTeamDatabaseLength();i++) {
            HashMap<String, String> resultsMap = new HashMap<>();
            resultsMap.put("First Line", DatabaseClass.getTeamName(i));
            resultsMap.put("Second Line", String.valueOf(DatabaseClass.getTeamNumber(i)));
            listItems.add(resultsMap);
        }

        for(int i=0;i<DatabaseClass.getLocalTeamDatabaseLength();i++) {
            HashMap<String, String> resultsMap = new HashMap<>();
            resultsMap.put("First Line", DatabaseClass.getLocalTeamName(i));
            resultsMap.put("Second Line", String.valueOf(DatabaseClass.getLocalTeamNumber(i)));
            listItems.add(resultsMap);
        }

        adapter.notifyDataSetChanged();
    }
}
