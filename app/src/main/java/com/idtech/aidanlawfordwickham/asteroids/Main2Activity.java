package com.idtech.aidanlawfordwickham.asteroids;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.view.ViewParent;

public class Main2Activity extends FragmentActivity {

    ViewPager viewPager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);

        ObjectPaint.createPaint();
        ObjectBitmap.initializeBitmaps(getResources());

        CustomPagerAdapter pagerAdapter = new CustomPagerAdapter(getSupportFragmentManager());
        viewPager = (ViewPager) findViewById(R.id.pager);
        viewPager.setAdapter(pagerAdapter);
    }

    public void initiateSurvival(View view) {
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }
}
