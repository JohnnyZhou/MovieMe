package com.johnnyzhou.movieme.ui.person.detail;

import android.os.Bundle;

import com.johnnyzhou.movieme.R;
import com.johnnyzhou.movieme.ui.common.BaseActivity;

public class PersonDetailActivity extends BaseActivity {
    private static final String TAG_PERSON_DETAIL = "person_detail";
    public static final String EXTRA_PERSON_ID = "com.johnnyzhou.movieme.person_id";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_person);
        initialiseInjections();
        setupFragment();
    }

    private void initialiseInjections() {
//        DaggerActivityComponent.builder()
//                .appComponent(getApplicationComponent())
//                .activityModule(new ActivityModule(this))
//                .build()
//                .inject(this);
    }

    private void setupFragment() {
        if (getSupportFragmentManager().findFragmentByTag(TAG_PERSON_DETAIL) != null) return;

        String personId = getIntent().getStringExtra(EXTRA_PERSON_ID);
        getSupportFragmentManager().beginTransaction()
                .add(R.id.detail_person_container, PersonDetailFragment.newInstance(personId), TAG_PERSON_DETAIL)
                .commit();
    }
}