package com.rahimlis.app;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.content.res.ResourcesCompat;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.fragment.app.FragmentPagerAdapter;
import androidx.fragment.app.FragmentStatePagerAdapter;
import androidx.viewpager.widget.PagerAdapter;
import androidx.viewpager2.adapter.FragmentStateAdapter;
import androidx.viewpager2.widget.ViewPager2;

import com.google.android.material.tabs.TabLayout;
import com.google.android.material.tabs.TabLayoutMediator;
import com.rahimlis.badgedtablayout.BadgedTabLayout;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    int counter = 1;

    /**
     * The {@link PagerAdapter} that will provide
     * fragments for each of the sections. We use a
     * {@link FragmentPagerAdapter} derivative, which will keep every
     * loaded fragment in memory. If this becomes too memory intensive, it
     * may be best to switch to a
     * {@link FragmentStatePagerAdapter}.
     */
    private SectionsPagerAdapter mSectionsPagerAdapter;

    /**
     * The {@link ViewPager2} that will host the section contents.
     */
    private ViewPager2 mViewPager;

    /**
     * The {@link BadgedTabLayout} that will host the tabs layout.
     */
    private BadgedTabLayout tabLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        // Create the adapter that will return a fragment for each of the three
        // primary sections of the activity.
        mSectionsPagerAdapter = new SectionsPagerAdapter(this);

        // Set up the ViewPager with the sections adapter.
        mViewPager = findViewById(R.id.container);
        mViewPager.setAdapter(mSectionsPagerAdapter);


        tabLayout = findViewById(R.id.tabs);

        new TabLayoutMediator(tabLayout, mViewPager, new TabLayoutMediator.TabConfigurationStrategy() {

            @Override
            public void onConfigureTab(@NonNull TabLayout.Tab tab, int position) {
                tab.setText(getPageTitle(position));
            }

        }).attach();

        tabLayout.setIcon(0, R.drawable.ic_favorite);
        tabLayout.setIcon(1, R.drawable.ic_shopping);


        tabLayout.setBadgeText(0, String.valueOf(counter));

        tabLayout.setBadgeText(2, "13213131");

        tabLayout.setTabFont(ResourcesCompat.getFont(this, R.font.trench));

        tabLayout.setBadgeTruncateAt(TextUtils.TruncateAt.MIDDLE);
        // tabLayout.setTabTextSize(14f);
//        tabLayout.setTabMode(TabLayout.MODE_SCROLLABLE);
//        tabLayout.isSpanText(true);
//        tabLayout.setMaxWidthText(5000);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onClick(View v) {
        if (tabLayout == null)
            return;

        if (v.getId() == R.id.button_add) {
            tabLayout.setBadgeText(tabLayout.getSelectedTabPosition(), String.valueOf(++counter));
        } else {
            tabLayout.setBadgeText(tabLayout.getSelectedTabPosition(), null);
        }
    }

    /**
     * A {@link FragmentPagerAdapter} that returns a fragment corresponding to
     * one of the sections/tabs/pages.
     */
    public static class SectionsPagerAdapter extends FragmentStateAdapter {

        public SectionsPagerAdapter(FragmentActivity fa) {
            super(fa);
        }

        @Override
        public Fragment createFragment(int position) {
            // getItem is called to instantiate the fragment for the given page.
            // Return a PlaceholderFragment (defined as a static inner class below).
            return PlaceHolderFragment.newInstance(position + 1);
        }

        @Override
        public int getItemCount() {
            // Show 3 total pages.
            return 3;
        }

    }

    public CharSequence getPageTitle(int position) {
        switch (position) {
            case 0:
                return "Section";
            case 1:
                return "";
            case 2:
                return "SECT 3";
        }
        return null;
    }

}
