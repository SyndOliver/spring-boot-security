package com.learning.spring_security_project.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.learning.spring_security_project.entity.ShopeePermission;
import com.learning.spring_security_project.entity.ShopeeRole;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface RoleMapper extends BaseMapper<ShopeeRole> {
    @Select("SELECT m.* FROM shopee_permission m " +
            "JOIN shopee_role_permission rm ON m.permission_id = rm.permission_id " +
            "WHERE rm.role_id = #{roleId}")
    List<ShopeePermission> selectPermissionsByRoleId(Long roleId);
}
