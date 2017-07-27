package com.idtech.aidanlawfordwickham.asteroids;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

/**
 * Created by iD Student on 7/27/2017.
 */

public class SpaceshipOneFragment extends android.support.v4.app.Fragment{

    public SpaceshipOneFragment() {

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.spaceship_one_fragment, container, false);
        return rootView;
    }
}
