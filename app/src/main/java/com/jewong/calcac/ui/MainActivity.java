package com.jewong.calcac.ui;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import com.jewong.calcac.R;
import com.jewong.calcac.common.BaseFragment;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    @SuppressWarnings("rawtypes")
    @Override
    public void onBackPressed() {
        Fragment navHostFragment = getSupportFragmentManager().findFragmentById(R.id.nav_host_fragment);
        if (navHostFragment != null) {
            BaseFragment fragment = (BaseFragment) navHostFragment.getChildFragmentManager().getFragments().get(0);
            if (!fragment.onBackPressConsume()) super.onBackPressed();
        }
    }

}