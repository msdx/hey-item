package com.githang.heyitem.staff.detail;

import android.os.Bundle;
import android.widget.TextView;

import com.githang.heyitem.R;
import com.githang.heyitem.staff.Staff;
import com.githang.heyitem.support.AutoExtra;
import com.githang.heyitem.support.BaseActivity;
import com.githang.heyitem.support.Formats;

public class StaffDetailActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setTitle(R.string.staff_detail);
        enableBackButton();
        setContentView(R.layout.activity_staff_detail);

        Staff staff = AutoExtra.get(getIntent(), Staff.class);

        TextView id = findViewById(R.id.id);
        TextView name = findViewById(R.id.name);
        TextView mobile = findViewById(R.id.mobile);
        TextView createTime = findViewById(R.id.create_time);
        id.setText(staff.id + "");
        name.setText(staff.name);
        mobile.setText(staff.mobile);
        createTime.setText(Formats.formatTime(staff.createTime));
    }
}
