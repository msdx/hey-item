package com.githang.heyitem.staff;

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
public class Staff {
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
}
