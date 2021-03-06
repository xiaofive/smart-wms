package com.shop.product.example.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.LambdaUpdateWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.shop.common.util.BeanConvertUtils;
import com.shop.product.example.bean.entity.User;
import com.shop.product.example.bean.req.UserReq;
import com.shop.product.example.mapper.UserMapper;
import com.shop.product.example.service.UserService;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements UserService {

    @Autowired
    private UserMapper userMapper;

    @Override
    public IPage<User> testPage(Page<User> page, String name) {
        QueryWrapper<User> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq(StringUtils.isNotBlank(name), "name", name);
        // 不进行 count sql 优化，解决 MP 无法自动优化 SQL 问题，这时候你需要自己查询 count 部分
        // page.setOptimizeCountSql(false);
        // 当 total 为小于 0 或者设置 setSearchCount(false) 分页插件不会进行 count 查询
        // 要点!! 分页返回的对象与传入的对象是同一个   Page对象实现了IPage接口
        Page<User> userPage = userMapper.selectPage(page, queryWrapper);
        return userPage;
    }

    @Override
    public User testSelectByRefId(String refId) {
        User user = userMapper.selectByRefId(refId);
        return user;
    }

    @Override
    public void testUpdate(UserReq userReq) {
        User old = userMapper.selectById(userReq.getId());
        old.setRefId(UUID.randomUUID().toString());
        old.setName(userReq.getName());
        old.setAge(userReq.getAge());
        old.setEmail(userReq.getEmail());
        userMapper.updateById(old);
    }

    @Override
    public void testDelete(Long id) {
        userMapper.deleteById(id);
    }

    @Override
    public void testInsert(UserReq userReq) {
        User user = BeanConvertUtils.map(userReq, User.class);
        userMapper.insert(user);
    }

    @Override
    public void deleteAllTable() {
        //测试全表删除
        userMapper.delete(null);
    }

    @Override
    public void updateAllTable() {
        //测试全表更新
        LambdaUpdateWrapper<User> updateWrapper = new LambdaUpdateWrapper<User>().set(User::getName, "005");
        userMapper.update(null, updateWrapper);
    }

}
