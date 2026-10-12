package com.learning.spring_security_project.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.learning.spring_security_project.entity.ShopeePermission;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface PermissionMapper extends BaseMapper<ShopeePermission> {
    @Select("SELECT DISTINCT m.* FROM shopee_permission m " +
            "JOIN shopee_role_permission rm ON m.permission_id = rm.permission_id " +
            "JOIN shopee_user_role ur ON rm.role_id = ur.role_id " +
            "WHERE ur.user_id = #{userId}")
    List<ShopeePermission> selectByUserId(Long userId);
}
