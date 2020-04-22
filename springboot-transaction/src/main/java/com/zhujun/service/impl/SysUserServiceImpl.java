package com.zhujun.service.impl;

import com.zhujun.entity.SysUser;
import com.zhujun.dao.SysUserDao;
import com.zhujun.service.SysUserService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;

/**
 * (SysUser)表服务实现类
 *
 * @author makejava
 * @since 2020-02-24 20:57:42
 */
@Service("sysUserService")
public class SysUserServiceImpl implements SysUserService {
    @Resource
    private SysUserDao sysUserDao;

    /**
     * 通过ID查询单条数据
     *
     * @param no 主键
     * @return 实例对象
     */
    @Override
    @Transactional
    public SysUser queryById(Integer no) {
        return this.sysUserDao.queryById(no);
    }

    /**
     * 查询多条数据
     *
     * @param offset 查询起始位置
     * @param limit 查询条数
     * @return 对象列表
     */
    @Override
    public List<SysUser> queryAllByLimit(int offset, int limit) {
        return this.sysUserDao.queryAllByLimit(offset, limit);
    }

    /**
     * 新增数据
     *
     * @param sysUser 实例对象
     * @return 实例对象
     */
    @Override
    public SysUser insert(SysUser sysUser) {
        this.sysUserDao.insert(sysUser);
        System.out.println("rebase test");
        return sysUser;
    }

    /**
     * 修改数据
     *
     * @param sysUser 实例对象
     * @return 实例对象
     */
    @Override
    public SysUser update(SysUser sysUser) {
        this.sysUserDao.update(sysUser);
        return this.queryById(sysUser.getNo());
    }

    /**
     * 通过主键删除数据
     *
     * @param no 主键
     * @return 是否成功
     */
    @Override
    public boolean deleteById(Integer no) {
        return this.sysUserDao.deleteById(no) > 0;
    }



}