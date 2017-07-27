package com.idtech.aidanlawfordwickham.asteroids;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

/**
 * Created by iD Student on 7/27/2017.
 */

public class CharacterPagerAdapter extends FragmentStatePagerAdapter {

    public CharacterPagerAdapter(FragmentManager manager) {
        super(manager);
    }
    @Override
    public Fragment getItem(int i){
        switch(i) {
            case 1: Fragment spaceshipTwoFragment = new SpaceshipTwoFragment();
                return spaceshipTwoFragment;
            case 2: Fragment spaceshipThreeFragment = new SpaceshipThreeFragment();
                return spaceshipThreeFragment;
            case 3: Fragment spaceshipFourFragment = new SpaceshipFourFragment();
                return spaceshipFourFragment;
            default: Fragment spaceshipOneFragment = new SpaceshipOneFragment();
                return spaceshipOneFragment;
        }
    }
    @Override
    public int getCount() {
        return 4;
    }


}
