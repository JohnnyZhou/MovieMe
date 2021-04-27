package com.johnnyzhou.movieme.ui.common;

import androidx.fragment.app.Fragment;

import com.johnnyzhou.movieme.di.component.AppComponent;

public abstract class BaseFragment extends Fragment {
    public AppComponent getAppComponent() {
        return ((BaseActivity) getActivity()).getApplicationComponent();
    }
}
