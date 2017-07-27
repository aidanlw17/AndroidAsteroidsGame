package com.idtech.aidanlawfordwickham.asteroids;

import android.app.Activity;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.view.ViewPager;

public class CharacterBuilderActivity extends FragmentActivity {

    ViewPager viewPager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_character_builder);

        CharacterPagerAdapter pagerAdapter = new CharacterPagerAdapter(getSupportFragmentManager());
        viewPager = (ViewPager) findViewById(R.id.characterPager);
        viewPager.setAdapter(pagerAdapter);
    }
}
