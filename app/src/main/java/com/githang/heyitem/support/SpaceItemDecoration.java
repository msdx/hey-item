package com.githang.heyitem.support;

import android.graphics.Rect;
import android.support.v7.widget.RecyclerView;
import android.view.View;

/**
 * @author Geek_Soledad (msdx.android@qq.com)
 * @since 2017-07-11 0.1
 */
public class SpaceItemDecoration extends RecyclerView.ItemDecoration {
    private int space;

    public SpaceItemDecoration(int space) {
        this.space = space;
    }

    @Override
    public void getItemOffsets(Rect outRect, View view, RecyclerView parent, RecyclerView.State state) {
        if (parent.getChildAdapterPosition(view) != 0) {
            outRect.set(0, space, 0, 0);
        }
    }
}
