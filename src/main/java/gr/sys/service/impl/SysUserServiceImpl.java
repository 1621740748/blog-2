package gr.sys.service.impl;

import com.github.pagehelper.PageHelper;
import gr.blog.model.Article;
import gr.sys.mapper.SysUserMapper;
import gr.sys.model.SysUser;
import gr.sys.service.SysUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

/**
 *
 */
@Service("sysUserService")
public class SysUserServiceImpl implements SysUserService {

    @Autowired
    private SysUserMapper sysUserMapper;

    /**
     * 获取用户列表
     * @param pageNum
     * @param pageSize
     * @param filter
     * @return
     */
    @Override
    public List<SysUser> findUserList(int pageNum, int pageSize, Map<String, Object> filter) {
        PageHelper.startPage(pageNum, pageSize);
        List<SysUser> userList = sysUserMapper.getUserList(filter);
        return userList;
    }
}
