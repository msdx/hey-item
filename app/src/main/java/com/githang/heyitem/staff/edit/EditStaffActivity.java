package com.githang.heyitem.staff.edit;

import android.os.Bundle;
import android.support.design.widget.TextInputLayout;
import android.text.TextUtils;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.EditText;
import android.widget.TextView;

import com.githang.android.snippet.view.InputMethod;
import com.githang.heyitem.HIApplication;
import com.githang.heyitem.R;
import com.githang.heyitem.staff.Staff;
import com.githang.heyitem.support.AutoExtra;
import com.githang.heyitem.support.BaseActivity;
import com.githang.heyitem.support.Formats;

public class EditStaffActivity extends BaseActivity {
    private TextInputLayout mMobileLayout;
    private EditText mMobileEdit;
    private Staff mStaff;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setTitle(R.string.edit_staff);
        enableBackButton();
        setContentView(R.layout.activity_staff_edit);

        mStaff = AutoExtra.get(getIntent(), Staff.class);

        TextView id = findViewById(R.id.id);
        TextView name = findViewById(R.id.name);
        TextView createTime = findViewById(R.id.create_time);
        id.setText(mStaff.id + "");
        name.setText(mStaff.name);
        createTime.setText(Formats.formatTime(mStaff.createTime));
        mMobileLayout = findViewById(R.id.mobile_layout);
        mMobileEdit = findViewById(R.id.mobile_edit);
        mMobileEdit.setText(mStaff.mobile);
        mMobileEdit.setSelection(mMobileEdit.getText().toString().length());
        mMobileEdit.requestFocus();
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
        String mobile = mMobileEdit.getText().toString().trim();
        if (TextUtils.isEmpty(mobile)) {
            mMobileLayout.setError(getString(R.string.error_input_mobile));
            return;
        }

        InputMethod.hideSoftInput(this, mMobileLayout.getWindowToken());

        mStaff.mobile = mobile;
        HIApplication.liteOrm().save(mStaff);
        setResult(RESULT_OK);
        finish();
    }
}
