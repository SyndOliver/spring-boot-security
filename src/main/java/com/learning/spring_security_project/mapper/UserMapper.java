package com.learning.spring_security_project.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.learning.spring_security_project.entity.ShopeeRole;
import com.learning.spring_security_project.entity.ShopeeUser;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface UserMapper extends BaseMapper<ShopeeUser> {

    @Select("SELECT r.* FROM shopee_role r " +
            "JOIN shopee_user_role ur ON r.role_id = ur.role_id " +
            "WHERE ur.user_id = #{userId}")
    @Results({
            @Result(property = "roleId", column = "role_id"),
            @Result(property = "permissions", column = "role_id",
                    many = @Many(select = "com.springsecurity.mapper.PermissionMapper.selectByUserId"))
    })
    List<ShopeeRole> selectRolesByUserId(Long userId);
}
