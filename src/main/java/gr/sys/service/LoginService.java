package gr.sys.service;

import gr.sys.model.SysUser;

public interface LoginService {

    /**
     * 根据用户名获取用户信息 由于登录等功能
     */
    SysUser getUserByName(String userName);
}
