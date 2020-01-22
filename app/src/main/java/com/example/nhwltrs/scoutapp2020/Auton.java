package com.example.nhwltrs.scoutapp2020;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

import java.util.ArrayList;
import java.util.List;


public class Auton extends Fragment {
    private static final String TAG = "Auton";

    String location = "";

    public Auton() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_auton, container, false);

        //----------------------------------------------------------------------------------------------------------
        //  Starting Position Spinner
        //----------------------------------------------------------------------------------------------------------
        final Spinner startingPosition = (Spinner) view.findViewById(R.id.startingPositionSpinner);

        List<String> startingPlaces = new ArrayList<String>();
        startingPlaces.add("Left");
        startingPlaces.add("Middle");
        startingPlaces.add("Right");
        final int listSize = startingPlaces.size();
        startingPlaces.add("Select Position");

        ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(getContext(), android.R.layout.simple_spinner_item, startingPlaces) {
            @Override
            public int getCount() {
                return (listSize);
            }
        };
        dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        startingPosition.setAdapter(dataAdapter);
        startingPosition.setSelection(listSize);

        //Database stuff for startingPosition spinner
        startingPosition.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                String startingPos = startingPosition.getSelectedItem().toString();
                //Method for adding it to the database. Commented out right now because the method isn't yet written
                //DatabaseClass.setStartingPos(startingPos);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        //----------------------------------------------------------------------------------------------------------
        //  Movement Checkbox
        //----------------------------------------------------------------------------------------------------------


        //----------------------------------------------------------------------------------------------------------
        //  Bottom Port Counter
        //----------------------------------------------------------------------------------------------------------

        //----------------------------------------------------------------------------------------------------------
        //  Outer Port Counter
        //----------------------------------------------------------------------------------------------------------

        //----------------------------------------------------------------------------------------------------------
        //  Inner Port Counter
        //----------------------------------------------------------------------------------------------------------

        //----------------------------------------------------------------------------------------------------------
        return view;
    }
}
