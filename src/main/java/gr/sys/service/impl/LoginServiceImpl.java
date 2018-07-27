package gr.sys.service.impl;

import gr.sys.mapper.SysUserMapper;
import gr.sys.model.SysUser;
import gr.sys.service.LoginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service("loginService")
public class LoginServiceImpl implements LoginService {

    @Autowired
    private SysUserMapper sysUserMapper;

    @Override
    public SysUser getUserByName(String userName) {
        return sysUserMapper.selectByUserName(userName);
    }
}
