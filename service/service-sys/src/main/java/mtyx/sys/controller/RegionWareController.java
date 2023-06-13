package mtyx.sys.controller;


import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import mtyx.model.sys.RegionWare;
import mtyx.result.Result;
import mtyx.sys.service.RegionWareService;
import mtyx.vo.sys.RegionWareQueryVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * <p>
 * 城市仓库关联表 前端控制器
 * </p>
 *
 * @author atzzc
 * @since 2023-06-13
 */
@RestController
@RequestMapping("/admin/sys/regionWare")
@Api(tags = "开通区域接口")
@CrossOrigin
public class RegionWareController {

    @Autowired
    private RegionWareService regionWareService;

    // 开通区域列表
    @ApiOperation("开通区域列表")
    @GetMapping("{page}/{limit}")
    public Result getPageList(@PathVariable Long page,
                              @PathVariable Long limit,
                              RegionWareQueryVo regionWareQueryVo) {
        IPage<RegionWare> pageParm = new Page<>(page, limit);
        IPage<RegionWare> pageModel = regionWareService.getRegionWareByKeyword(pageParm, regionWareQueryVo);
        return Result.ok(pageModel);

    }

    // 添加开通区域
    @ApiOperation("添加开通区域")
    @PostMapping("save")
    public Result addRegionWare(@RequestBody RegionWare regionWare) {
        regionWareService.saveRegionWare(regionWare);
        return Result.ok(null);
    }

    //删除开通区域
    @ApiOperation("删除开通区域")
    @DeleteMapping("remove/{id}")
    public Result remove(@PathVariable Long id) {
        regionWareService.removeById(id);
        return Result.ok(null);
    }

    // 取消开通区域
    @ApiOperation(value = "取消开通区域")
    @PostMapping("updateStatus/{id}/{status}")
    public Result updateStatus(@PathVariable Long id, @PathVariable Integer status) {
        regionWareService.updateStatus(id, status);
        return Result.ok(null);
    }

}

