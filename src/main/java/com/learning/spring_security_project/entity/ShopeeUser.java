package com.learning.spring_security_project.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Data
@TableName("shopee_user")
public class ShopeeUser implements UserDetails {

    @TableId(value = "user_id", type = IdType.AUTO)
    private Long userId;

    @TableField(value = "login_name")
    private String loginName;

    @TableField(value = "password")
    private String password;

    @TableField(value = "status")
    private String status;

    @TableField(value = "del_flag")
    private String delFlag;

    @TableField(exist = false)
    private List<ShopeeRole> shopeeRoles;


    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        Set<GrantedAuthority> authorities = new HashSet<>();
        // add roles
        authorities.addAll(shopeeRoles.stream()
                .map(role -> new SimpleGrantedAuthority("ROLE_" + role.getRoleKey()))
                .collect(Collectors.toList()));
        // add permissions
        authorities.addAll(shopeeRoles.stream()
                        .flatMap(role -> role.getPermissions().stream())
                .map(permissionKey -> new SimpleGrantedAuthority(permissionKey.getPermissionKey()))
                .collect(Collectors.toList()));
        return authorities;
    }

    @Override
    public String getPassword() {
        return this.password;
    }

    @Override
    public String getUsername() {
        return this.loginName;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return "0".equals(this.status);
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return "0".equals(this.delFlag);
    }
}
