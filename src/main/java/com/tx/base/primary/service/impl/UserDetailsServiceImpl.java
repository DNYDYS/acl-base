package com.tx.base.primary.service.impl;

import com.tx.base.primary.entity.AclLog;
import com.tx.base.primary.entity.SecurityUser;
import com.tx.base.primary.entity.User;
import com.tx.base.primary.service.AclLogService;
import com.tx.base.primary.service.PermissionService;
import com.tx.base.primary.service.UserService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("userDetailsService")
public class UserDetailsServiceImpl implements UserDetailsService {

    @Autowired
    private UserService userService;

    @Autowired
    private PermissionService permissionService;

    @Autowired
    private AclLogService aclLogService;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        //根据用户名查询数据
        User user = userService.selectByUsername(username);
        //判断
        if(user == null) {
            throw new UsernameNotFoundException("用户不存在");
        }
        AclLog aclLog = new AclLog();
        aclLog.setTitle(username);
        aclLog.setType("1");
        aclLog.setRequestUrl("/admin/login");
        aclLog.setRequestType("POST");
        aclLog.setRemoteIp("127.0.0.1");
        com.tx.base.primary.entity.User curUser = new com.tx.base.primary.entity.User();
        //复制对象
        BeanUtils.copyProperties(user,curUser);
        aclLogService.addLog(aclLog);
        //根据用户查询用户权限列表
        List<String> permissionValueList = permissionService.selectPermissionValueByUserId(user.getId());
        SecurityUser securityUser = new SecurityUser();
        securityUser.setCurrentUserInfo(curUser);
        securityUser.setPermissionValueList(permissionValueList);
        return securityUser;
    }
}
