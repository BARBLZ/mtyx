package mtyx.acl.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import mtyx.model.acl.Role;
import mtyx.vo.acl.RoleQueryVo;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service
public interface RoleService extends IService<Role> {
    IPage<Role> selectRolePage(Page<Role> pageParam, RoleQueryVo roleQueryVo);

    Map<String, Object> selectRolesByAdminId(Long adminId);

    void saveAdminRole(Long adminId, Long[] roleId);
}
