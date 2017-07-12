package com.githang.heyitem.support;

import android.content.Intent;
import android.os.Parcelable;

/**
 * @author Geek_Soledad (msdx.android@qq.com)
 * @since 2017-07-12 0.1
 */
public class AutoExtra {
    public static <T extends Parcelable> void put(Intent intent, T data) {
        intent.putExtra(data.getClass().getSimpleName(), data);
    }

    public static <T extends Parcelable> T get(Intent intent, Class<T> cls) {
        return intent.getParcelableExtra(cls.getSimpleName());
    }
}
