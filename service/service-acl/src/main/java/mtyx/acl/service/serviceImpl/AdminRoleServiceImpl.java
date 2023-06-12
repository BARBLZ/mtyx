package mtyx.acl.service.serviceImpl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import mtyx.acl.mapper.AdminRoleMapper;
import mtyx.acl.service.AdminRoleService;
import mtyx.model.acl.AdminRole;
import org.springframework.stereotype.Service;

@Service
public class AdminRoleServiceImpl extends ServiceImpl<AdminRoleMapper, AdminRole> implements AdminRoleService {
}
