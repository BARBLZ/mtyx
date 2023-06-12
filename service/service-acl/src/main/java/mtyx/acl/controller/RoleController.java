package mtyx.acl.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import mtyx.acl.service.RoleService;
import mtyx.model.acl.Role;
import mtyx.result.Result;
import mtyx.vo.acl.RoleQueryVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@Api(tags = "角色管理接口")
@RequestMapping("/admin/acl/role")
@CrossOrigin
public class RoleController {
    /**
     * 获取角色分页列表(带搜索)
     * url: `${api_name}/${page}/${limit}`,
     *       method: 'get',
     */
    @Autowired
    private RoleService roleService;

    @ApiOperation("获取角色分页列表")
    @GetMapping("{page}/{limit}")
    public Result pageList (@PathVariable Long page,
                         @PathVariable Long limit,
                         RoleQueryVo roleQueryVo) {
        Page<Role> pageParam = new Page<>(page, limit);
        IPage<Role> pageModle = roleService.selectRolePage(pageParam, roleQueryVo);

        return Result.ok(pageModle);
    }

    /**
     * 根据id获取角色
     */
    @ApiOperation("根据id获取角色")
    @GetMapping("/get/{id}")
    public Result getById(@PathVariable Long id) {
        Role role = roleService.getById(id);
        return Result.ok(role);
    }

    /**
     * 新增角色
     */
    @ApiOperation("新增角色")
    @PostMapping("save")
    public Result save(@RequestBody Role role) {
        boolean is_success = roleService.save(role);
        return is_success ? Result.ok(null) : Result.fail(null);
    }

    /**
     * 修改角色
     */
    @ApiOperation("修改角色")
    @PutMapping("update")
    public Result update(@RequestBody Role role) {
        boolean is_success = roleService.updateById(role);
        return is_success ? Result.ok(null) : Result.fail(null);
    }
    /**
     * 删除角色
     */
    @ApiOperation("删除角色")
    @DeleteMapping("/remove/{id}")
    public Result remove(@PathVariable Long id) {
        boolean is_success = roleService.removeById(id);
        return is_success ? Result.ok(null) : Result.fail(null);

    }
     /**
     * 批量删除角色
     */
     @ApiOperation("批量删除角色")
     @DeleteMapping("batchRemove")
     public Result batchRemove(@RequestBody List<Long> roleList) {
         boolean is_success = roleService.removeByIds(roleList);
         return is_success ? Result.ok(null) : Result.fail(null);

     }

}
