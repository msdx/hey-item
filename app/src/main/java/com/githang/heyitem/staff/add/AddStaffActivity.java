package com.githang.heyitem.staff.add;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TextInputLayout;
import android.text.Editable;
import android.text.TextUtils;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.EditText;

import com.githang.android.snippet.view.InputMethod;
import com.githang.heyitem.HIApplication;
import com.githang.heyitem.R;
import com.githang.heyitem.staff.Staff;
import com.githang.heyitem.support.BaseActivity;
import com.githang.heyitem.support.SimpleTextWatcher;
import com.litesuits.orm.db.assit.QueryBuilder;

/**
 * @author Geek_Soledad (msdx.android@qq.com)
 * @since 2017-07-11 0.1
 */
public class AddStaffActivity extends BaseActivity {
    private TextInputLayout mNameLayout;
    private EditText mNameEdit;
    private TextInputLayout mMobileLayout;
    private EditText mMobileEdit;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setTitle(R.string.add_staff);
        setContentView(R.layout.activity_staff_add);
        enableBackButton();

        mNameLayout = findViewById(R.id.name_layout);
        mNameEdit = findViewById(R.id.name_edit);
        mMobileLayout = findViewById(R.id.mobile_layout);
        mMobileEdit = findViewById(R.id.mobile_edit);
        mNameEdit.addTextChangedListener(new SimpleTextWatcher() {
            @Override
            public void afterTextChanged(Editable editable) {
                mNameLayout.setErrorEnabled(false);
            }
        });
        mMobileEdit.addTextChangedListener(new SimpleTextWatcher() {
            @Override
            public void afterTextChanged(Editable editable) {
                mMobileLayout.setErrorEnabled(false);
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.staff_add, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.save:
                saveStaff();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    private void saveStaff() {
        String name = mNameEdit.getText().toString().trim();
        if (TextUtils.isEmpty(name)) {
            mNameLayout.setError(getString(R.string.error_input_name));
            return;
        }

        String mobile = mMobileEdit.getText().toString().trim();
        if (TextUtils.isEmpty(mobile)) {
            mMobileLayout.setError(getString(R.string.error_input_mobile));
            return;
        }

        long count = HIApplication.liteOrm()
                .queryCount(new QueryBuilder(Staff.class).whereEquals(Staff.COL_NAME, name));
        if (count > 0) {
            mNameLayout.setError(getString(R.string.error_staff_exists));
            return;
        }

        InputMethod.hideSoftInput(this, mNameEdit.getWindowToken());

        Staff staff = new Staff(name, mobile, System.currentTimeMillis());
        HIApplication.liteOrm().save(staff);
        setResult(RESULT_OK);
        finish();
    }
}
