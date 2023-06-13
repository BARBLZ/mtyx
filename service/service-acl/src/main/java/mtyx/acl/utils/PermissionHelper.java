package mtyx.acl.utils;

import mtyx.model.acl.Permission;

import java.util.LinkedList;
import java.util.List;

public class PermissionHelper {

    public static Permission getAllChildren(Permission permission, List<Permission> allPermissionList) {
        permission.setChildren(new LinkedList<>());

        for (Permission it : allPermissionList) {
            if (it.getPid().longValue() == permission.getId().longValue()) {
                // it为permission的子权限结点
                Integer level = permission.getLevel() + 1;
                it.setLevel(level);
                if (permission.getChildren() == null) {
                    permission.setChildren(new LinkedList<>());
                }
                permission.getChildren().add(getAllChildren(it, allPermissionList));
            }
        }
        return permission;
    }
}
