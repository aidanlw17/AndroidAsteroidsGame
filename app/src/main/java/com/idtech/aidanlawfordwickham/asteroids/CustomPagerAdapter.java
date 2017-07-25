package com.idtech.aidanlawfordwickham.asteroids;



import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

/**
 * Created by iD Student on 7/25/2017.
 */

public class CustomPagerAdapter extends FragmentStatePagerAdapter {

    public CustomPagerAdapter(FragmentManager manager) {
        super(manager);
    }
    @Override
    public Fragment getItem(int i){
        switch(i) {
            case 1: Fragment arenaModeFragment = new ArenaModeFragment();
                return arenaModeFragment;
            case 2: Fragment raceModeFragment = new RaceModeFragment();
                return raceModeFragment;
            default: Fragment survivalModeFragment = new SurvivalModeFragment();
                return survivalModeFragment;
        }
    }

    @Override
    public int getCount() {
        return 3;
    }


}
