package mtyx.sys.controller;


import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import mtyx.model.sys.Ware;
import mtyx.result.Result;
import mtyx.sys.service.WareService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * <p>
 * 仓库表 前端控制器
 * </p>
 *
 * @author atzzc
 * @since 2023-06-13
 */
@RestController
@RequestMapping("/admin/sys/ware")
@Api(tags = "仓库接口")
@CrossOrigin
public class WareController {

    @Autowired
    private WareService wareService;

    @ApiOperation("仓库展示")
    @GetMapping("findAllList")
    public Result list() {
        List<Ware> list = wareService.list();
        return Result.ok(list);

    }
}

