package mtyx.acl.service.serviceImpl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import mtyx.acl.mapper.RoleMapper;
import mtyx.acl.service.RoleService;
import mtyx.model.acl.Role;
import mtyx.vo.acl.RoleQueryVo;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

@Service
public class RoleServiceImpl extends ServiceImpl<RoleMapper, Role> implements RoleService{

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
}
