package com.example.nhwltrs.scoutapp2020;

import android.content.Context;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.support.v4.app.Fragment;
import android.util.Log;
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

    // Required empty public constructor
    public TeleOp() {}

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_tele_op, container, false);

        /// Rotation check box obj and listener
        final CheckBox park = (CheckBox)view.findViewById(R.id.telePark);
        park.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                if (park.isChecked()) {
                    DatabaseClass.setParking(true);
                } else {
                    DatabaseClass.setParking(false);
                }
            }
        });

        /// Rotation check box obj and listener
        final CheckBox rotation = view.findViewById(R.id.teleRotation);
        rotation.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                if (rotation.isChecked()) {
                    DatabaseClass.setRotationControl(true);
                } else {
                    DatabaseClass.setRotationControl(false);
                }
            }
        });

        /// Position check box
        final CheckBox pos = view.findViewById(R.id.telePos);
        pos.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                if (pos.isChecked()) {
                    DatabaseClass.setPositionControl(true);
                } else {
                    DatabaseClass.setPositionControl(false);
                }
            }
        });

        /// Hanging + level check boxes
        final CheckBox hang = view.findViewById(R.id.teleHang);
        final CheckBox level = view.findViewById(R.id.teleLevel);

        level.setClickable(false);
        level.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                /*
                 * This is a gross, dirty hack!!
                 * For some reason, executing level.setClickable(false) after declaring level doesn't work.
                 * Because of this, this checks to make sure that hang is clicked before setting the check.
                 */
                if (!hang.isChecked()) {
                    level.setChecked(false);
                    level.setClickable(false);
                    return;
                }

                // Actual database editing
                if(level.isChecked()) {
                    DatabaseClass.setLevel(true);
                } else {
                    DatabaseClass.setLevel(false);
                }
            }
        });

        hang.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                if (hang.isChecked()) {
                    DatabaseClass.setHang(true);
                    level.setClickable(true);
                } else {
                    DatabaseClass.setHang(false);
                    DatabaseClass.setLevel(false);
                    level.setChecked(false);
                    level.setClickable(false);
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
                DatabaseClass.setTeleopBottom(bottom.get_count());
            }
        });
        bottom.dec.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(bottom.get_count() > 0)
                    bottom.dec(1);
                bottom.display();
                DatabaseClass.setTeleopBottom(bottom.get_count());
            }
        });

        outer = new Counter((Button)view.findViewById(R.id.teleOuterUp), (Button)view.findViewById(R.id.teleOuterDown), (TextView)view.findViewById(R.id.teleOuterDisplay));
        outer.setCount(DatabaseClass.getTeleopOuter());
        outer.inc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                outer.inc(1);
                outer.display();
                DatabaseClass.setTeleopOuter(outer.get_count());
            }
        });
        outer.dec.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(outer.get_count() > 0)
                    outer.dec(1);
                outer.display();
                DatabaseClass.setTeleopOuter(outer.get_count());
            }
        });

        inner = new Counter((Button)view.findViewById(R.id.teleInnerUp), (Button)view.findViewById(R.id.teleInnerDown), (TextView)view.findViewById(R.id.teleInnerDisplay));
        inner.setCount(DatabaseClass.getTeleopInner());
        inner.inc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                inner.inc(1);
                inner.display();
                DatabaseClass.setTeleopInner(inner.get_count());
            }
        });
        inner.dec.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(inner.get_count() > 0)
                    inner.dec(1);
                inner.display();
                DatabaseClass.setTeleopInner(inner.get_count());
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
