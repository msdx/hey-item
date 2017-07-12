package com.githang.heyitem.staff.list;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
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
    private ItemMenuListener mItemMenuListener;

    StaffAdapter(@NonNull List<Staff> list, LayoutInflater inflater) {
        mList = list;
        mLayoutInflater = inflater;
    }

    @Override
    public StaffViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        final StaffViewHolder holder = new StaffViewHolder(mLayoutInflater.inflate(R.layout.item_staff, parent, false));
        holder.setItemClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (mItemMenuListener != null) {
                    int position = holder.getAdapterPosition();
                    mItemMenuListener.onItemClick(position, holder);
                }
            }
        });
        holder.setDeleteListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (mItemMenuListener != null) {
                    int position = holder.getAdapterPosition();
                    mItemMenuListener.onDelete(position);
                }
            }
        });
        holder.setEditListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (mItemMenuListener != null) {
                    int position = holder.getAdapterPosition();
                    mItemMenuListener.onEdit(position);
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
    }

    @Override
    public int getItemCount() {
        return mList.size();
    }

    public void setItemMenuListener(ItemMenuListener itemMenuListener) {
        mItemMenuListener = itemMenuListener;
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

    public interface ItemMenuListener {
        void onItemClick(int position, StaffViewHolder holder);

        void onEdit(int position);

        void onDelete(int position);
    }
}
