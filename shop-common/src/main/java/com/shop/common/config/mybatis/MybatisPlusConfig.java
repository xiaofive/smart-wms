package com.shop.common.config.mybatis;

import com.baomidou.mybatisplus.extension.plugins.MybatisPlusInterceptor;
import com.baomidou.mybatisplus.extension.plugins.inner.BlockAttackInnerInterceptor;
import com.baomidou.mybatisplus.extension.plugins.inner.OptimisticLockerInnerInterceptor;
import com.baomidou.mybatisplus.extension.plugins.inner.PaginationInnerInterceptor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * Mybatis-Plus配置
 * 版本：3.4.0
 * <p>
 * Author: wang Y
 * Date: 2021-05-22
 */
@Configuration
public class MybatisPlusConfig {

    /**
     * 自定义 SqlInjector
     * 里面包含自定义的全局方法:
     * 1.SelectByRefId
     * 2.ListAllAvailable
     *
     * @Param:
     * @return:
     * @Date: 2021-05-23
     */
    @Bean
    public ShopLogicSqlInjector gsCustomSqlInjector() {
        return new ShopLogicSqlInjector();
    }

    /**
     * 插件：按需参考官方文档添加：https://mp.baomidou.com/guide/
     * 一：乐观锁配置 OptimisticLockerInnerInterceptor
     * 使用时，在实体类的字段上加上 @Version 注解
     * <p>
     * 说明：
     * <p>
     * 1.支持的数据类型只有:int,Integer,long,Long,Date,Timestamp,LocalDateTime
     * 整数类型下 newVersion = oldVersion + 1
     * newVersion 会回写到 entity 中
     * 仅支持 updateById(id) 与 update(entity, wrapper) 方法
     * 在 update(entity, wrapper) 方法下, wrapper 不能复用!!!
     * <p>
     * 2.以User对象为例：
     * 需要先获取旧的User：
     * User old = userMapper.selectById(userReq.getId());
     * old.setRefId(UUID.randomUUID().toString());
     * old.setName(userReq.getName());
     * old.setAge(userReq.getAge());
     * old.setEmail(userReq.getEmail());
     * 然后再执行update操作，乐观锁字段才能维护成功
     * userMapper.updateById(old);
     * <p>
     * 3.乐观锁实现方式：
     * 取出记录时，获取当前version
     * 更新时，带上这个version
     * 执行更新时， set version = newVersion where version = oldVersion
     * 如果version不对，就更新失败
     * <p>
     * 二：分页配置 PaginationInnerInterceptor
     * 新的分页插件,一缓和二缓遵循mybatis的规则,需要设置 MybatisConfiguration#useDeprecatedExecutor = false 避免缓存出现问题
     * <p>
     * 三：防止全表更新与删除插件 BlockAttackInnerInterceptor
     * TODO：Mybatis-Plus3.4的漏洞：与逻辑删除字段共用的时候拦截失效的问题
     *
     * @Param:
     * @return:
     * @Date: 2021-05-23
     */
    @Bean
    public MybatisPlusInterceptor mybatisPlusInterceptor() {
        MybatisPlusInterceptor interceptor = new MybatisPlusInterceptor();
        interceptor.addInnerInterceptor(new PaginationInnerInterceptor()); //分页
        interceptor.addInnerInterceptor(new OptimisticLockerInnerInterceptor()); //启用乐观锁
        interceptor.addInnerInterceptor(new BlockAttackInnerInterceptor()); // 添加防止全表更新与删除插件
        return interceptor;
    }

//    /**
//     * 自3.3.0开始,默认使用雪花算法+UUID(不含中划线)  //TODO 待ID方案完善
//     *
//     * @Param:
//     * @return:
//     * @Date: 2021-05-23
//     */
//    @Bean
//    public IdentifierGenerator idGenerator() {
//        return new ShopRefIdGenerator();
//    }

}
