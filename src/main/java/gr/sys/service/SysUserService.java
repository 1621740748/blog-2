package gr.sys.service;

import gr.blog.model.Article;
import gr.sys.model.SysUser;

import java.util.List;
import java.util.Map;

public interface SysUserService {

    /**
     * 获取用户列表
     * @param pageNum
     * @param pageSize
     * @param filter
     * @return
     */
    List<SysUser> findUserList(int pageNum, int pageSize, Map<String,Object> filter);
}
