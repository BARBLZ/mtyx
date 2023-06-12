package mtyx.acl.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import mtyx.acl.service.AdminService;
import mtyx.acl.service.RoleService;
import mtyx.model.acl.Admin;
import mtyx.result.Result;
import mtyx.vo.acl.AdminQueryVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@Api(tags = "用户接口")
@RestController
@RequestMapping("/admin/acl/user")
@CrossOrigin
public class AdminController {
    @Autowired
    private AdminService adminService;

    @Autowired
    private RoleService roleService;

    // 用户列表
    @ApiOperation("用户列表")
    @GetMapping("{page}/{limit}")
    public Result getPageList(@PathVariable Long page,
                              @PathVariable Long limit,
                              AdminQueryVo adminQueryVo) {
        Page<Admin> adminPage = new Page<>(page, limit);
        IPage<Admin> pageModle = adminService.selectAdminPage(adminPage, adminQueryVo);
        return Result.ok(pageModle);
    }
    // 用户添加
    @ApiOperation("用户添加")
    @PostMapping("save")
    public Result save(@RequestBody Admin admin) {
        boolean is_success = adminService.save(admin);
        return is_success ? Result.ok(null) : Result.fail(null);

    }
    // 用户修改（id查询和修改）
    @ApiOperation("用户修改")
    @PutMapping("update")
    public Result update(@RequestBody Admin admin) {
        boolean is_success = adminService.updateById(admin);
        return is_success ? Result.ok(null) : Result.fail(null);
    }
    // 根据id删除
    @ApiOperation("根据id删除")
    @DeleteMapping("remove/{id}")
    public Result removeById(@PathVariable Long id) {
        boolean is_success = adminService.removeById(id);
        return is_success ? Result.ok(null) : Result.fail(null);
    }
    // 批量删除
    @ApiOperation("批量删除")
    @DeleteMapping("batchRemove")
    public Result removeUsers(@RequestBody List<Long> idList) {
        boolean is_success = adminService.removeByIds(idList);
        return is_success ? Result.ok(null) : Result.fail(null);
    }

    // 获取某个用户的所有角色
    @ApiOperation("获取用户角色")
    @GetMapping("toAssign/{adminId}")
    public Result getRoles(@PathVariable Long adminId) {
        Map<String, Object> map = roleService.selectRolesByAdminId(adminId);
        return Result.ok(map);
    }

    // 为用户分配角色
    @ApiOperation("为用户分配角色")
    @PostMapping("doAssign")
    public Result doAssign(@RequestParam Long adminId,
                           @RequestParam Long[] roleId) {
        roleService.saveAdminRole(adminId, roleId);
        return Result.ok(null);
    }

}
