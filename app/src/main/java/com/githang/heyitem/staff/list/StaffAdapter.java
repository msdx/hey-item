package com.githang.heyitem.staff.list;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.githang.heyitem.R;
import com.githang.heyitem.staff.Staff;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Geek_Soledad (msdx.android@qq.com)
 * @since 2017-07-11 0.1
 */
class StaffAdapter extends RecyclerView.Adapter<StaffViewHolder> {
    @NonNull
    private List<Staff> mList;
    private LayoutInflater mLayoutInflater;
    private OnDeleteListener mOnDeleteListener;

    StaffAdapter(@NonNull List<Staff> list, LayoutInflater inflater) {
        mList = list;
        mLayoutInflater = inflater;
    }

    @Override
    public StaffViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        final StaffViewHolder holder = new StaffViewHolder(mLayoutInflater.inflate(R.layout.item_staff, parent, false));
        holder.setOnDeleteListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (mOnDeleteListener != null) {
                    int position = holder.getAdapterPosition();
                    mOnDeleteListener.onDelete(position);
                }
            }
        });
        return holder;
    }

    @Override
    public void onBindViewHolder(StaffViewHolder holder, int position) {
        Staff staff = mList.get(position);
        holder.name.setText(staff.name);
        holder.mobile.setText(staff.mobile);
        if (TextUtils.isEmpty(staff.mobile)) {
            holder.mobile.setVisibility(View.GONE);
        } else {
            holder.mobile.setVisibility(View.VISIBLE);
        }
    }

    @Override
    public int getItemCount() {
        return mList.size();
    }

    public void setOnDeleteListener(OnDeleteListener onDeleteListener) {
        mOnDeleteListener = onDeleteListener;
    }

    void update(List<Staff> list) {
        if (list == null) {
            mList = new ArrayList<>();
        } else {
            mList = list;
        }

        notifyDataSetChanged();
    }

    Staff getItem(int position) {
        return mList.get(position);
    }

    void remove(int position) {
        mList.remove(position);
        notifyItemRemoved(position);
    }

    public interface OnDeleteListener {
        void onDelete(int position);
    }
}
