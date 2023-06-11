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

@RestController
@Api(tags = "用户管理接口")
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

}
