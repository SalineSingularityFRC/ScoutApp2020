package com.example.nhwltrs.scoutapp2020;

import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Handler;
import android.os.SystemClock;
import android.support.design.widget.TabLayout;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class MatchData extends AppCompatActivity {

    private SectionsPagerAdapter mSectionsPagerAdapter;
    private ViewPager mViewPager;
    long StartTime, MillisecondTime, TimeBuff, UpdateTime = 0L ;
    Handler handler;
    int Seconds;
    int MilliSeconds;
    TextView matchTimer;
    TextView teamDuringMatchTextView1;
    TextView teamDuringMatchTextView2;
    Button endMatch;
    boolean started = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_match_data);

        mSectionsPagerAdapter = new SectionsPagerAdapter(getSupportFragmentManager());

        mViewPager = (ViewPager) findViewById(R.id.container);
        setUpViewPager(mViewPager);

        TabLayout tabLayout = (TabLayout) findViewById(R.id.tabs);
        tabLayout.setupWithViewPager(mViewPager);
        matchTimer  = (TextView) findViewById(R.id.matchTimer);
        teamDuringMatchTextView1 = (TextView) findViewById(R.id.teamDuringMatchTextView1);
        teamDuringMatchTextView2 = (TextView) findViewById(R.id.teamDuringMatchTextView2);
        final Button startMatch = (Button) findViewById(R.id.matchStartButton);
        endMatch = (Button) findViewById(R.id.matchEndButton);

        handler = new Handler();
        StartTime = SystemClock.uptimeMillis();

       /* if(getIntent().hasExtra("Team Number")) {
            String teamNumberString = getIntent().getExtras().toString();
            teamNumberString = teamNumberString.replace("Bundle[{Team Number=","");
            teamNumberString = teamNumberString.replace("}]", "");
            teamDuringMatchTextView2.setText(teamNumberString);
            teamDuringMatchTextView1.setText(teamNumberString);
        }*/

        startMatch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startMatch.setVisibility(View.INVISIBLE);
                StartTime = SystemClock.uptimeMillis();
                handler.postDelayed(runnable, 0);
                started = true;
            }
        });

        endMatch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DatabaseClass.finishMatch();
                finish();
            }
        });
    }

    public Runnable runnable = new Runnable() {
        public void run() {
            MillisecondTime = SystemClock.uptimeMillis() - StartTime;
            UpdateTime = TimeBuff + MillisecondTime;
            Seconds = (int) (UpdateTime / 1000);
            int timeLeft = 150 - Seconds;
            MilliSeconds = (int) (UpdateTime % 1000);
            MilliSeconds = MilliSeconds / 1000;
            if (timeLeft < 1) {
                matchTimer.setText("Match Over!");
                endMatch.setVisibility(View.VISIBLE);
            } else {
                matchTimer.setText(timeLeft + "");
            }
            handler.postDelayed(this, 0);
        }
    };

    private void setUpViewPager(ViewPager viewPager) {
        SectionsPagerAdapter adapter = new SectionsPagerAdapter(getSupportFragmentManager());
        adapter.addFragment(new Auton(), "Auton");
        //adapter.addFragment(new Sandstorm(this), "Sandstorm");
        adapter.addFragment(new TeleOp(), "Teleop");
        viewPager.setAdapter(adapter);
    }

    public class SectionsPagerAdapter extends FragmentPagerAdapter {

        private final List<Fragment> mFragmentList = new ArrayList<>();
        private final List<String> mFragmentTitleList = new ArrayList<>();

        public void addFragment(Fragment fragment, String title) {
            mFragmentList.add(fragment);
            mFragmentTitleList.add(title);
        }

        public SectionsPagerAdapter(FragmentManager fm) {
            super(fm);
        }


        @Override
        public CharSequence getPageTitle(int position) {
            return mFragmentTitleList.get(position);
        }

        @Override
        public Fragment getItem(int position) {
            return mFragmentList.get(position);
        }

        @Override
        public int getCount() {
            // Show 3 total pages.
            return mFragmentList.size();
        }
    }
    public int getTimer(){
        return Seconds;
    }
}
