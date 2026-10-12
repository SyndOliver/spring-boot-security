package com.learning.spring_security_project.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

@Data
@TableName("shopee_permission")
public class ShopeePermission {
    @TableId(value = "permission_id", type = IdType.AUTO)
    private Long permissionId;
    @TableField(value = "permission_name")
    private String permissionName;
    @TableField(value = "permission_key")
    private String permissionKey;
}
