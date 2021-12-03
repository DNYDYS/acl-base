package com.tx.base.handler;

import com.baomidou.mybatisplus.core.handlers.MetaObjectHandler;
import org.apache.ibatis.reflection.MetaObject;
import org.springframework.stereotype.Component;

import java.util.Date;

/**
 * 前端传值添加默认值
 * 给对象添加默认值
 * MetaObjectHandler接口是mybatisPlus为我们提供的的一个扩展接口，我们可以利用这个接口在我们插入或者更新数据的时候，
 * 为一些字段指定默认值。实现这个需求的方法不止一种，在sql层面也可以做到，在建表的时候也可以指定默认值。
 */
@Component
public class MyMetaObjectHandler implements MetaObjectHandler {
    @Override
    public void insertFill(MetaObject metaObject) {
        //属性名称，不是字段名称
        this.setFieldValByName("gmtCreate", new Date(), metaObject);
        this.setFieldValByName("gmtModified", new Date(), metaObject);
    }

    @Override
    public void updateFill(MetaObject metaObject) {
        this.setFieldValByName("gmtModified", new Date(), metaObject);
    }
}
