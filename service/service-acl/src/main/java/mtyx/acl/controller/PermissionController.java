package mtyx.acl.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import mtyx.acl.service.PermissionService;
import mtyx.model.acl.Permission;
import mtyx.result.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Api(tags = "权限管理接口")
@RestController
@CrossOrigin
@RequestMapping("/admin/acl/permission")
public class PermissionController {

    @Autowired
    private PermissionService permissionService;

    // 获取权限(菜单/功能)列表(按照树状图)
    @ApiOperation("获取权限列表")
    @GetMapping
    public Result getPermissionList() {
        List<Permission> list = permissionService.getAllPermissionList();
        return Result.ok(list);
    }

    // 递归删除一个权限项及所属的子权限
    @ApiOperation("删除权限项")
    @DeleteMapping("remove/{id}")
    public Result removePermission(@PathVariable Long id) {
        boolean is_success = permissionService.removePermissionById(id);
        return is_success ? Result.ok(null) : Result.fail(null);
    }

    // 保存一个权限项
    @ApiOperation("保存权限项")
    @PostMapping("save")
    public Result addPermission(@RequestBody Permission permission) {
        boolean is_success = permissionService.save(permission);
        return is_success ? Result.ok(null) : Result.fail(null);
    }

    // 更新一个权限项
    @ApiOperation("更新权限项")
    @PutMapping("update")
    public Result updatePermission(@RequestBody Permission permission) {
        boolean is_success = permissionService.updateById(permission);
        return is_success ? Result.ok(null) : Result.fail(null);
    }
}
