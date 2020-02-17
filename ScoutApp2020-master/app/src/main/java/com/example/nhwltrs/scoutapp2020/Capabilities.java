package com.example.nhwltrs.scoutapp2020;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.CheckBox;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Button;

public class Capabilities extends Fragment {

    //Variables to send to the database
    Counter upper;
    Counter lower;

    public Capabilities() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_capabilities, container, false);

        final CheckBox collect = (CheckBox) view.findViewById(R.id.capCollect);
        collect.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                if (collect.isChecked()) {
                    // DB foolishness
                    DatabaseClass.setFloorCollection(true);
                } else {
                    // More DB foolishness
                    DatabaseClass.setFloorCollection(false);
                }
            }
        });

        final CheckBox trench = (CheckBox)view.findViewById(R.id.capTrench);
        trench.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                if (trench.isChecked()) {
                    // DB fiddling
                    DatabaseClass.setTrench(true);
                } else {
                    // DB absurdity
                    DatabaseClass.setTrench(false);
                }
            }
        });

        upper = new Counter((Button)view.findViewById(R.id.capUpperUp), (Button)view.findViewById(R.id.capUpperDown), (TextView)view.findViewById(R.id.capUpperDisplay));
        upper.setCount(DatabaseClass.getUpperBay());
        upper.inc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                upper.inc(1);
                upper.display();
                DatabaseClass.setUpperBay(upper.get_count());
            }
        });
        upper.dec.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(upper.get_count() > 0)
                    upper.dec(1);
                upper.display();
                DatabaseClass.setUpperBay(upper.get_count());
            }
        });

        lower = new Counter((Button)view.findViewById(R.id.capLowerUp), (Button)view.findViewById(R.id.capLowerDown), (TextView)view.findViewById(R.id.capLowerDisplay));
        lower.setCount(DatabaseClass.getLowerBay());
        lower.inc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                lower.inc(1);
                lower.display();
                DatabaseClass.setLowerBay(lower.get_count());
            }
        });
        lower.dec.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(lower.get_count() > 0)
                    lower.dec(1);
                lower.display();
                DatabaseClass.setLowerBay(lower.get_count());
            }
        });


        return view;
    }

    @Override
    public void onStop() {
        super.onStop();

        /*DatabaseClass.setUpperBay(upper.get_count());
        DatabaseClass.setLowerBay(lower.get_count());*/
    }
}
