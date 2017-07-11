package com.githang.heyitem;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;

import com.githang.heyitem.staff.list.StaffListFragment;

import java.util.List;

/**
 * 主界面
 * @author Geek_Soledad (msdx.android@qq.com)
 * @since 2017-07-11 0.1
 */
public class MainActivity extends AppCompatActivity {

    private Fragment mStaffFragment;

    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {
        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            switch (item.getItemId()) {
                case R.id.borrow:
                    setTitle(R.string.borrow);
                    return true;
                case R.id.items:
                    setTitle(R.string.items);
                    return true;
                case R.id.records:
                    setTitle(R.string.records);
                    return true;
                case R.id.staff:
                    if (mStaffFragment == null) {
                        mStaffFragment = new StaffListFragment();
                    }
                    showFragment(mStaffFragment);
                    setTitle(R.string.staff);
                    return true;
            }
            return false;
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        BottomNavigationView navigationView = findViewById(R.id.navigation_view);
        navigationView.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);
        navigationView.setSelectedItemId(R.id.borrow);
    }

    private void showFragment(Fragment fragment) {
        FragmentManager fm = getSupportFragmentManager();
        List<Fragment> fragments = fm.getFragments();
        FragmentTransaction ft = fm.beginTransaction();
        if (!fragment.isAdded()) {
            ft.add(R.id.fragment_container, fragment, fragment.getTag());
        }
        if (fragments != null) {
            for (Fragment f : fragments) {
                if (f == fragment) {
                    ft.show(f);
                } else {
                    ft.hide(f);
                }
            }
        }
        ft.commitAllowingStateLoss();
    }
}
