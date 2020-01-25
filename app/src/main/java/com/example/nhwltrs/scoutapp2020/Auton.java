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
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.Spinner;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;


public class Auton extends Fragment {
    private static final String TAG = "Auton";

    //Variables to send to database
    CheckBox initCheckBox;
    Spinner startingPos;
    Counter bottom;
    Counter outer;
    Counter inner;



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
        startingPos = (Spinner) view.findViewById(R.id.startingPositionSpinner);

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
        startingPos.setAdapter(dataAdapter);
        startingPos.setSelection(listSize);

        //Database stuff for startingPosition spinner
        startingPos.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                String startingPosition = startingPos.getSelectedItem().toString();
                //Method for adding it to the database. Commented out right now because the method isn't yet written
                DatabaseClass.setStartingPos(startingPosition);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        //----------------------------------------------------------------------------------------------------------
        //  Movement Checkbox
        //----------------------------------------------------------------------------------------------------------

        initCheckBox = (CheckBox)view.findViewById(R.id.initCheckBox);

        initCheckBox.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //Database code
                if(initCheckBox.isChecked()) {
                    //Tell the database
                    DatabaseClass.setAutonMove(true);
                }
                else {
                    //Tell the database
                    DatabaseClass.setAutonMove(false);
                }
            }
        });

        //----------------------------------------------------------------------------------------------------------
        //  Bottom Port Counter
        //----------------------------------------------------------------------------------------------------------

        bottom = new Counter((Button)view.findViewById(R.id.autonBottomPlus),(Button)view.findViewById(R.id.autonBottomMinus),
                (TextView)view.findViewById(R.id.autonBottomCounter));

        //----------------------------------------------------------------------------------------------------------
        //  Outer Port Counter
        //----------------------------------------------------------------------------------------------------------

        outer = new Counter((Button)view.findViewById(R.id.autonOuterPlus),(Button)view.findViewById(R.id.autonOuterMinus),
                (TextView) view.findViewById(R.id.autonOuterCounter));

        //----------------------------------------------------------------------------------------------------------
        //  Inner Port Counter
        //----------------------------------------------------------------------------------------------------------

        inner = new Counter((Button)view.findViewById(R.id.autonInnerPlus),(Button)view.findViewById(R.id.autonInnerMinus),
                (TextView) view.findViewById(R.id.autonInnerCounter));

        //----------------------------------------------------------------------------------------------------------
        return view;
    }

    @Override
    public void onStop() {
        super.onStop();

        DatabaseClass.setAutonBottom(bottom.get_count());
        DatabaseClass.setAutonOuter(outer.get_count());
        DatabaseClass.setAutonInner(inner.get_count());
    }


}
