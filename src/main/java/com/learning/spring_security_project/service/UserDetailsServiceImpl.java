package com.learning.spring_security_project.service;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.learning.spring_security_project.mapper.UserMapper;
import com.learning.spring_security_project.entity.ShopeeRole;
import com.learning.spring_security_project.entity.ShopeeUser;
import com.learning.spring_security_project.mapper.RoleMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class UserDetailsServiceImpl implements UserDetailsService {

    @Autowired
    private UserMapper userMapper;

    private final RoleMapper roleMapper;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        // 1. Get User Info
        LambdaQueryWrapper<ShopeeUser> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(ShopeeUser::getLoginName, username);
        ShopeeUser shopeeUser = userMapper.selectOne(queryWrapper);
        if (shopeeUser == null) {
            throw new UsernameNotFoundException("Not found" + username);
        }
        //2. GetRoles
        List<ShopeeRole> roles = userMapper.selectRolesByUserId(shopeeUser.getUserId());
        roles.forEach(role ->
                role.setPermissions(roleMapper.selectPermissionsByRoleId(role.getRoleId())));
        shopeeUser.setShopeeRoles(roles);
        return shopeeUser;
    }
}
