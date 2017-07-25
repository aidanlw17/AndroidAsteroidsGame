package com.idtech.aidanlawfordwickham.asteroids;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

/**
 * Created by iD Student on 7/25/2017.
 */

public class SurvivalModeFragment extends android.support.v4.app.Fragment {

    public SurvivalModeFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.survival_mode_fragment, container, false);
        return rootView;
    }

}
