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

//        /// Initialization check box obj and listener
//        final CheckBox init = (CheckBox)view.findViewById(R.id.initLn);
//        init.setOnClickListener(new View.OnClickListener() {
//            public void onClick(View v) {
//                if (init.isChecked()) {
//                    // DB magic ~~
//                } else {
//                    // More DB magic
//                }
//            }
//        });

        Counter bottom = new Counter((Button)view.findViewById(R.id.teleBottomDown), (Button)view.findViewById(R.id.teleBottomUp), (TextView)view.findViewById(R.id.teleBottomDisplay));

        return view;
    }
}
