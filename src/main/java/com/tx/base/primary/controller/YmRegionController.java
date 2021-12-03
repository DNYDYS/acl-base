package com.tx.base.primary.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.tx.base.primary.entity.YmRegion;
import com.tx.base.primary.service.YmRegionService;
import com.tx.base.utils.R;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @description: --
 * @author：Bing
 * @date：2021/12/3 10:44
 * @version：1.0
 */
@Api(tags = "地区接口")
@RestController
@RequestMapping("/region")
public class YmRegionController {
    @Autowired
    private YmRegionService regionService;

    @ApiOperation("地区列表")
    @PostMapping("/list")
    public R list() {
        List<YmRegion> list = regionService.list(new QueryWrapper<>());
        return R.ok().data("regionList", list);
    }
}
