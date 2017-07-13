package com.githang.heyitem.staff.list;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.ActivityOptionsCompat;
import android.support.v4.app.Fragment;
import android.support.v4.util.Pair;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.TypedValue;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.githang.heyitem.HIApplication;
import com.githang.heyitem.R;
import com.githang.heyitem.staff.Staff;
import com.githang.heyitem.staff.add.AddStaffActivity;
import com.githang.heyitem.staff.detail.StaffDetailActivity;
import com.githang.heyitem.staff.edit.EditStaffActivity;
import com.githang.heyitem.support.AutoExtra;
import com.githang.heyitem.support.SpaceItemDecoration;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Geek_Soledad (msdx.android@qq.com)
 * @since 2017-07-11 0.1
 */
public class StaffListFragment extends Fragment {
    private static final int REQUEST_ADD_STAFF = 1000;
    private static final int REQUEST_EDIT_STAFF = 1001;

    private RecyclerView mRecyclerView;
    private StaffAdapter mAdapter;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_staff_list, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        final FloatingActionButton fab = view.findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                addStaff();
            }
        });

        mRecyclerView = view.findViewById(R.id.recycler_view);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false));
        int space = (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 16, getResources().getDisplayMetrics());
        mRecyclerView.addItemDecoration(new SpaceItemDecoration(space));
        mRecyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                if (dy > 0) {
                    fab.hide();
                } else {
                    fab.show();
                }
            }
        });

        mAdapter = new StaffAdapter(new ArrayList<Staff>(), LayoutInflater.from(getContext()));
        mAdapter.setItemMenuListener(new StaffAdapter.ItemMenuListener() {
            @Override
            public void onItemClick(int position, StaffViewHolder holder) {
                showStaffDetail(position, holder);
            }

            @Override
            public void onEdit(int position) {
                editStaff(position);
            }

            @Override
            public void onDelete(int position) {
                deleteStaff(position);
            }
        });
        mRecyclerView.setAdapter(mAdapter);
        reloadStaffList();
    }

    private void addStaff() {
        Intent intent = new Intent(getActivity(), AddStaffActivity.class);
        startActivityForResult(intent, REQUEST_ADD_STAFF);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == REQUEST_ADD_STAFF || requestCode == REQUEST_EDIT_STAFF) {
            if (resultCode == Activity.RESULT_OK) {
                Snackbar.make(getView(), R.string.msg_save_successful, Snackbar.LENGTH_SHORT).show();
                reloadStaffList();
            }
        } else {
            super.onActivityResult(requestCode, resultCode, data);
        }
    }

    private void reloadStaffList() {
        List<Staff> list = HIApplication.liteOrm().query(Staff.class);
        mAdapter.update(list);
    }

    private void showStaffDetail(int position, final StaffViewHolder holder) {
        Staff staff = mAdapter.getItem(position);
        Intent intent = new Intent(getActivity(), StaffDetailActivity.class);
        AutoExtra.put(intent, staff);
        Pair name = Pair.create(holder.name, getString(R.string.share_name));
        Pair mobile = Pair.create(holder.mobile, getString(R.string.share_mobile));
        ActivityOptionsCompat options = ActivityOptionsCompat.makeSceneTransitionAnimation(
                getActivity(), name, mobile);
        ActivityCompat.startActivity(getActivity(), intent, options.toBundle());
    }

    private void editStaff(int position) {
        Staff staff = mAdapter.getItem(position);
        Intent intent = new Intent(getActivity(), EditStaffActivity.class);
        AutoExtra.put(intent, staff);
        startActivityForResult(intent, REQUEST_EDIT_STAFF);
    }

    private void deleteStaff(int position) {
        HIApplication.liteOrm().delete(mAdapter.getItem(position));
        mAdapter.remove(position);
        Snackbar.make(getView(), R.string.msg_delete_successful, Snackbar.LENGTH_SHORT).show();
    }
}
