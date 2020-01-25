package com.example.nhwltrs.scoutapp2020;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.TextView;
import android.view.ViewGroup;

public class TeleOp extends Fragment {
    private static final String TAG = "TeleOp";

    public TeleOp() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_tele_op, container, false);

        /// Rotation check box obj and listener
        final CheckBox rotation = (CheckBox)view.findViewById(R.id.teleRotation);
        rotation.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                if (rotation.isChecked()) {
                    // DB magic ~~
                } else {
                    // More DB magic
                }
            }
        });

        /// Position check box
        final CheckBox pos = (CheckBox)view.findViewById(R.id.telePos);
        pos.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                if (pos.isChecked()) {
                    // DB magic !!
                } else {
                    // Different DB magic
                }
            }
        });

        /// Hanging check box
        final CheckBox hang = (CheckBox)view.findViewById(R.id.teleHang);
        hang.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                if (hang.isChecked()) {
                    // DB *** magic ***
                } else {
                    // DB magic once more
                }
            }
        });

        Counter bottom = new Counter((Button)view.findViewById(R.id.teleBottomDown), (Button)view.findViewById(R.id.teleBottomUp), (TextView)view.findViewById(R.id.teleBottomDisplay));
        Counter outer = new Counter((Button)view.findViewById(R.id.teleOuterDown), (Button)view.findViewById(R.id.teleOuterUp), (TextView)view.findViewById(R.id.teleOuterDisplay));
        Counter inner = new Counter((Button)view.findViewById(R.id.teleInnerDown), (Button)view.findViewById(R.id.teleInnerUp), (TextView)view.findViewById(R.id.teleInnerDisplay));

        return view;
    }
}
