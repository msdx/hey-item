package com.githang.heyitem.staff.list;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.githang.heyitem.R;
import com.mcxtzhang.swipemenulib.SwipeMenuLayout;

/**
 * @author Geek_Soledad (msdx.android@qq.com)
 * @since 2017-07-11 0.1
 */
class StaffViewHolder extends RecyclerView.ViewHolder {
    final TextView name;
    final TextView mobile;

    StaffViewHolder(View itemView) {
        super(itemView);
        name = itemView.findViewById(R.id.name);
        mobile = itemView.findViewById(R.id.mobile);
        SwipeMenuLayout layout = itemView.findViewById(R.id.swipe_menu);
        layout.setIos(false);
    }

    public void setOnDeleteListener(View.OnClickListener listener) {
        itemView.findViewById(R.id.delete).setOnClickListener(listener);
    }
}
