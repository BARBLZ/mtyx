package mtyx.sys.controller;


import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import mtyx.model.sys.Region;
import mtyx.result.Result;
import mtyx.sys.service.RegionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * <p>
 * 地区表 前端控制器
 * </p>
 *
 * @author atzzc
 * @since 2023-06-13
 */
@RestController
@RequestMapping("admin/sys/region")
@Api(tags = "区域接口")
@CrossOrigin
public class RegionController {

    @Autowired
    private RegionService regionService;

    // 根据区域关键字查询区域列表信息
    @ApiOperation("根据区域关键字查询区域列表信息")
    @GetMapping("findRegionByKeyWord/{keyword}")
    public Result findRegionByKeyWord(@PathVariable String keyword) {
        List<Region> list = regionService.getRegionByKeyWord(keyword);
        return Result.ok(list);
    }
}

