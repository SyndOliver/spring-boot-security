package com.learning.spring_security_project.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.util.List;

@Data
@TableName("shopee_role")
public class ShopeeRole {
    @TableId(value = "role_id", type = IdType.AUTO)
    private Long roleId;

    @TableField("role_name") // Ánh xạ tới cột 'role_name'
    private String roleName;

    @TableField("role_key") // Ánh xạ tới cột 'role_key'
    private String roleKey;

    @TableField(exist = false)
    private List<ShopeePermission> permissions;
}
