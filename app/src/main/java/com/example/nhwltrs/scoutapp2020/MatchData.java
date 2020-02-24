package com.example.nhwltrs.scoutapp2020;

import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Handler;
import android.support.design.widget.TabLayout;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class MatchData extends AppCompatActivity {
    private ViewPager mViewPager;
    Handler handler;
    TextView teamDuringMatchTextView1;
    TextView teamDuringMatchTextView2;
    Button endMatch;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_match_data);

        mViewPager = findViewById(R.id.container);
        setUpViewPager(mViewPager);

        TabLayout tabLayout = findViewById(R.id.tabs);
        tabLayout.setupWithViewPager(mViewPager);

        // Team $NUMBER displays
        teamDuringMatchTextView1 = findViewById(R.id.teamDuringMatchTextView1);
        teamDuringMatchTextView2 = findViewById(R.id.teamDuringMatchTextView2);

        // Send data
        endMatch = (Button)findViewById(R.id.matchEndButton);

        handler = new Handler();

        if(getIntent().hasExtra("Team Number")) {
            String teamNumberString = getIntent().getExtras().toString();
            teamNumberString = teamNumberString.replace("Bundle[{Team Number=","");
            teamNumberString = teamNumberString.replace("}]", "");
            teamDuringMatchTextView2.setText(teamNumberString);
            teamDuringMatchTextView1.setText(teamNumberString);
        }

        endMatch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DatabaseClass.finishMatch();
                finish();
            }
        });
    }

    private void setUpViewPager(ViewPager viewPager) {
        SectionsPagerAdapter adapter = new SectionsPagerAdapter(getSupportFragmentManager());
        adapter.addFragment(new Auton(), "Auton");
        adapter.addFragment(new TeleOp(), "Teleop");
        adapter.addFragment(new Capabilities(), "Capabilities");
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
}
