package com.example.tuill.mamusiclist;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

public class FixedTabsPagerAdapter extends FragmentPagerAdapter {

    public FixedTabsPagerAdapter(FragmentManager fm) {
        super(fm);
    }

    @Override
    public Fragment getItem(int position) {
        switch (position) {
            case 0:
                return new SongsFragment();
            case 1:
                return new AlbumsFragment();
            case 2:
                return new ArtistsFragment();
            default:
                return null;
        }
    }

    @Override
    public CharSequence getPageTitle(int position){

        switch (position) {
            case 0:
                return "Songs";
            case 1:
                return "Albums";
            case 2:
                return "Artists";
            default:
                return null;
        }
    }


    @Override
    public int getCount() {
        return 3;
    }
}
