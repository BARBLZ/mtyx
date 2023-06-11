package mtyx.acl.controller;


import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import mtyx.result.Result;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/admin/acl/index")
@Api("登录接口")
@CrossOrigin     //解决跨域问题
// 跨域问题：前后端访问的协议（http）或ip地址或端口号不一致导致
public class IndexController {

    /**
     * 1、请求登陆的login
     * url: '/admin/acl/index/login',
     *     method: 'post',
     */
    @PostMapping("login")
    @ApiOperation("登录")
    public Result login() {
        Map<String,Object> map = new HashMap<>();
        map.put("token","admin-token");
        return Result.ok(map);
    }

    /**
     * 2、返回用户信息
     * url: '/admin/acl/index/info',
     *     method: 'get',
     */
    @GetMapping("info")
    @ApiOperation("获取信息")
    public Result info() {
        Map<String, String> map = new HashMap<>();
        map.put("name", "admin");
        map.put("avatar", "https://wpimg.wallstcn.com/f778738c-e4f8-4870-b634-56703b4acafe.gif");
        return Result.ok(map);
    }

    /**
     * 3、退出登录
     * url: '/admin/acl/index/logout',
     *     method: 'post'
     */
    @PostMapping("logout")
    @ApiOperation("退出")  // 用来在Swagger获取的文档中标注名称
    public Result logout() {
        return Result.ok(null);
    }

}
