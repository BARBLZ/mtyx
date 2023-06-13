package mtyx.acl.service;

import com.baomidou.mybatisplus.extension.service.IService;
import mtyx.model.acl.Permission;

import java.util.List;

public interface PermissionService extends IService<Permission> {
    // 获取权限(菜单/功能)列表(按照树状图)
    List<Permission> getAllPermissionList();

    // 递归删除一个权限项及所属的子权限
    boolean removePermissionById(Long id);
}
