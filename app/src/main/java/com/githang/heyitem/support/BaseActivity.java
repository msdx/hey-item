package com.githang.heyitem.support;

import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;

/**
 * @author 黄浩杭 (huanghaohang@parkingwang.com)
 * @since 2017-07-12
 */
public class BaseActivity extends AppCompatActivity {

    protected void enableBackButton() {
        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.setDisplayHomeAsUpEnabled(true);
            actionBar.setHomeButtonEnabled(true);
        }
    }

    @Override
    public boolean onSupportNavigateUp() {
        if (!super.onSupportNavigateUp()) {
            onBackPressed();
        }
        return true;
    }
}
