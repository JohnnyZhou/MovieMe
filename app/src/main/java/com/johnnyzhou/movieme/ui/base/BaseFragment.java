package com.johnnyzhou.movieme.ui.base;

import android.support.v4.app.Fragment;

import com.johnnyzhou.movieme.di.component.AppComponent;

public abstract class BaseFragment extends Fragment {
    public AppComponent getAppComponent() {
        return ((BaseActivity) getActivity()).getApplicationComponent();
    }
}
