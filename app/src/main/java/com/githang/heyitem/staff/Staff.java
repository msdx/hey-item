package com.githang.heyitem.staff;

import android.os.Parcel;
import android.os.Parcelable;

import com.litesuits.orm.db.annotation.Column;
import com.litesuits.orm.db.annotation.NotNull;
import com.litesuits.orm.db.annotation.PrimaryKey;
import com.litesuits.orm.db.annotation.Table;
import com.litesuits.orm.db.enums.AssignType;

/**
 * 人员对象
 *
 * @author Geek_Soledad (msdx.android@qq.com)
 * @since 2017-07-11 0.1
 */
@Table("t_staff")
public class Staff implements Parcelable {
    public static final String COL_NAME = "name";

    @PrimaryKey(AssignType.AUTO_INCREMENT)
    @Column("_id")
    public long id;
    @Column("name")
    @NotNull
    public String name;
    @Column("mobile")
    public String mobile;
    @Column("create_time")
    public long createTime;

    public Staff() {
    }

    public Staff(String name, String mobile, long createTime) {
        this.name = name;
        this.mobile = mobile;
        this.createTime = createTime;
    }

    @Override
    public int describeContents() { return 0; }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeLong(this.id);
        dest.writeString(this.name);
        dest.writeString(this.mobile);
        dest.writeLong(this.createTime);
    }

    protected Staff(Parcel in) {
        this.id = in.readLong();
        this.name = in.readString();
        this.mobile = in.readString();
        this.createTime = in.readLong();
    }

    public static final Parcelable.Creator<Staff> CREATOR = new Parcelable.Creator<Staff>() {
        @Override
        public Staff createFromParcel(Parcel source) {return new Staff(source);}

        @Override
        public Staff[] newArray(int size) {return new Staff[size];}
    };
}
