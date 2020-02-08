package com.example.nhwltrs.scoutapp2020;

import android.content.Context;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.TextView;
import android.view.ViewGroup;

public class TeleOp extends Fragment {
    private static final String TAG = "TeleOp";

    //Variables to send to the database
    Counter bottom;
    Counter outer;
    Counter inner;

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
                    DatabaseClass.setRotationControl(true);
                } else {
                    // More DB magic
                    DatabaseClass.setRotationControl(false);
                }
            }
        });

        /// Position check box
        final CheckBox pos = (CheckBox)view.findViewById(R.id.telePos);
        pos.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                if (pos.isChecked()) {
                    // DB magic !!
                    DatabaseClass.setPositionControl(true);
                } else {
                    // Different DB magic
                    DatabaseClass.setPositionControl(false);
                }
            }
        });

        /// Hanging check box
        final CheckBox hang = (CheckBox)view.findViewById(R.id.teleHang);
        final CheckBox level = (CheckBox)view.findViewById(R.id.teleLevel);
        level.setClickable(false);
        hang.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                if (hang.isChecked()) {
                    // DB *** magic ***
                    DatabaseClass.setHang(true);
                    level.setClickable(true);
                } else {
                    // DB magic once more
                    DatabaseClass.setHang(false);
                    DatabaseClass.setLevel(false);
                    level.setChecked(false);
                    level.setClickable(false);
                }
            }
        });

        level.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(level.isChecked()) {
                    DatabaseClass.setLevel(true);
                }
                else {
                    DatabaseClass.setLevel(false);
                }
            }
        });

        bottom = new Counter((Button)view.findViewById(R.id.teleBottomUp), (Button)view.findViewById(R.id.teleBottomDown), (TextView)view.findViewById(R.id.teleBottomDisplay));
        bottom.setCount(DatabaseClass.getTeleopBottom());
        bottom.inc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                bottom.inc(1);
                bottom.display();
                DatabaseClass.setAutonBottom(bottom.get_count());
            }
        });
        bottom.dec.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(bottom.get_count() > 0)
                    bottom.dec(1);
                bottom.display();
                DatabaseClass.setAutonBottom(bottom.get_count());
            }
        });

        outer = new Counter((Button)view.findViewById(R.id.teleOuterUp), (Button)view.findViewById(R.id.teleOuterDown), (TextView)view.findViewById(R.id.teleOuterDisplay));
        outer.setCount(DatabaseClass.getTeleopOuter());
        outer.inc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                outer.inc(1);
                outer.display();
                DatabaseClass.setAutonOuter(outer.get_count());
            }
        });
        outer.dec.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(outer.get_count() > 0)
                    outer.dec(1);
                outer.display();
                DatabaseClass.setAutonOuter(outer.get_count());
            }
        });

        inner = new Counter((Button)view.findViewById(R.id.teleInnerUp), (Button)view.findViewById(R.id.teleInnerDown), (TextView)view.findViewById(R.id.teleInnerDisplay));
        inner.setCount(DatabaseClass.getTeleopInner());
        inner.inc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                inner.inc(1);
                inner.display();
                DatabaseClass.setAutonInner(inner.get_count());
            }
        });
        inner.dec.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(inner.get_count() > 0)
                    inner.dec(1);
                inner.display();
                DatabaseClass.setAutonInner(inner.get_count());
            }
        });



        return view;
    }

    @Override
    public void onStop() {
        super.onStop();

        /*DatabaseClass.setTeleopBottom(bottom.get_count());
        DatabaseClass.setTeleopOuter(outer.get_count());
        DatabaseClass.setTeleopInner(inner.get_count());*/
    }
}
