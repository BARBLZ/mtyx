package mtyx.acl.service.serviceImpl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import mtyx.acl.mapper.PermissionMapper;
import mtyx.acl.service.PermissionService;
import mtyx.acl.utils.PermissionHelper;
import mtyx.model.acl.Permission;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.LinkedList;
import java.util.List;

@Service
public class PermissionServiceImpl extends ServiceImpl<PermissionMapper, Permission> implements PermissionService {

    @Autowired
    private  PermissionService permissionService;

    // 获取权限(菜单/功能)列表(按照树状图)
    @Override
    public List<Permission> getAllPermissionList() {
        // 获取所有权限项
        List<Permission> allPermissionList = baseMapper.selectList(null);

        // 存放最终结果（树形列表集合）
        List<Permission> root = new LinkedList<>();

        // 获取第一层的list pid=0
        for (Permission permission : allPermissionList) {
            if (permission.getPid() == 0) {
                permission.setLevel(1);
                root.add(PermissionHelper.getAllChildren(permission, allPermissionList));
            }
        }

        return root;
    }


    // 递归删除一个权限项及所属的子权限
    @Override
    public boolean removePermissionById(Long id) {
        List<Long> removeIdList = new LinkedList<>();
        removeIdList.add(id);
        getRemoveIdList(id, removeIdList);
        return permissionService.removeByIds(removeIdList);
    }

    // 递归获取权限id的所有子权限
    private void getRemoveIdList(Long id, List<Long> removeIdList) {
        LambdaQueryWrapper<Permission> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(Permission::getPid, id);
        List<Permission> permissionList = permissionService.list(wrapper);
        for (Permission permission : permissionList) {
            removeIdList.add(permission.getId());
            // 递归所有子权限
            getRemoveIdList(permission.getId(), removeIdList);
        }
    }

}
