package mtyx.acl.service.serviceImpl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import mtyx.acl.mapper.RoleMapper;
import mtyx.acl.service.AdminRoleService;
import mtyx.acl.service.RoleService;
import mtyx.model.acl.AdminRole;
import mtyx.model.acl.Role;
import mtyx.vo.acl.RoleQueryVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.*;
import java.util.stream.Collectors;

@Service
public class RoleServiceImpl extends ServiceImpl<RoleMapper, Role> implements RoleService{

    @Autowired
    private AdminRoleService adminRoleService;

    // 角色分页列表
    @Override
    public IPage<Role> selectRolePage(Page<Role> pageParam, RoleQueryVo roleQueryVo) {
        // 获取条件值：角色名称
        String roleName = roleQueryVo.getRoleName();
        // 创建条件构造器对象
        LambdaQueryWrapper<Role> wrapper = new LambdaQueryWrapper<Role>();
        // 如果条件不为空，则在条件构造器中封装条件
        if (!StringUtils.isEmpty(roleName)) {
            wrapper.like(Role::getRoleName, roleName);
        }
        // 调用mapper方法实现条件分页查询

        IPage<Role> rolePage = baseMapper.selectPage(pageParam, wrapper);
        return rolePage;
    }

    // 获取用户对应角色
    @Override
    public Map<String, Object> selectRolesByAdminId(Long adminId) {
        // 获取所有角色
        List<Role> allRolesList = baseMapper.selectList(null);
        // 获取指定用户id对应角色
        LambdaQueryWrapper<AdminRole> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(AdminRole::getAdminId, adminId);
        List<AdminRole> roleList = adminRoleService.list(wrapper);
        List<Long> roleIdList = roleList.stream().map(AdminRole::getRoleId).collect(Collectors.toList());
        List<Role> assignRoleList = new ArrayList<>();
        for (Role role : allRolesList) {
            Long roleId = role.getId();
            if (roleIdList.contains(roleId)) {
                assignRoleList.add(role);
            }
        }
        Map<String, Object> ret = new HashMap<>();
        ret.put("allRolesList", allRolesList);
        ret.put("assignRoles", assignRoleList);

        return ret;
    }

    // 为用户分配角色
    @Override
    public void saveAdminRole(Long adminId, Long[] roleIds) {
        // 删除该用户原来的角色
        LambdaQueryWrapper<AdminRole> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(AdminRole::getAdminId, adminId);
        adminRoleService.remove(wrapper);
        // 为该用户重新分配角色
        List<AdminRole> list = new LinkedList<>();
        for (Long roleId : roleIds) {
            AdminRole adminRole = new AdminRole();
            adminRole.setAdminId(adminId);
            adminRole.setRoleId(roleId);
            list.add(adminRole);
        }
        // 批量添加角色
        adminRoleService.saveBatch(list);
    }
}
