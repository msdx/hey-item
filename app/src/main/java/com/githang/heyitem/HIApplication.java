package com.githang.heyitem;

import android.app.Application;
import android.support.annotation.NonNull;

import com.litesuits.orm.LiteOrm;

/**
 * @author 黄浩杭 (huanghaohang@parkingwang.com)
 * @since 2017-07-11 0.1
 */
public class HIApplication extends Application {

    private static LiteOrm LITE_ORM;

    @Override
    public void onCreate() {
        super.onCreate();
        LITE_ORM = LiteOrm.newSingleInstance(this, "hey_item.db");
        LITE_ORM.setDebugged(BuildConfig.DEBUG);
    }

    @NonNull
    public static LiteOrm liteOrm() {
        return LITE_ORM;
    }
}
