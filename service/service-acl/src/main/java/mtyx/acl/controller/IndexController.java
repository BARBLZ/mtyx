package mtyx.acl.controller;


import mtyx.result.Result;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/admin/acl/index")
@CrossOrigin     //跨域
public class IndexController {

    /**
     * 1、请求登陆的login
     * url: '/admin/acl/index/login',
     *     method: 'post',
     */
    @PostMapping("login")
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
    public Result logout() {
        return Result.ok(null);
    }

}
