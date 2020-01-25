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
                } else {
                    // More DB foolishness
                }
            }
        });

        final CheckBox trench = (CheckBox)view.findViewById(R.id.capTrench);
        trench.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                if (trench.isChecked()) {
                    // DB fiddling
                } else {
                    // DB absurdity
                }
            }
        });

        Counter upper = new Counter((Button)view.findViewById(R.id.capUpperUp), (Button)view.findViewById(R.id.capUpperDown), (TextView)view.findViewById(R.id.capUpperDisplay));
        Counter lower = new Counter((Button)view.findViewById(R.id.capLowerUp), (Button)view.findViewById(R.id.capLowerDown), (TextView)view.findViewById(R.id.capLowerDisplay));

        return view;
    }
}
