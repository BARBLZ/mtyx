package mtyx.acl.service.serviceImpl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import mtyx.acl.mapper.AdminMapper;
import mtyx.acl.service.AdminService;
import mtyx.model.acl.Admin;
import mtyx.vo.acl.AdminQueryVo;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

@Service
public class AdminServiceImpl extends ServiceImpl<AdminMapper, Admin> implements AdminService {
    @Override
    public IPage<Admin> selectAdminPage(Page<Admin> adminPage, AdminQueryVo adminQueryVo) {
        String name = adminQueryVo.getName();
        String userName = adminQueryVo.getUsername();
        // 创建条件构造器对象
        LambdaQueryWrapper<Admin> wrapper = new LambdaQueryWrapper<>();
        if (!StringUtils.isEmpty(name)) {
            wrapper.like(Admin::getName, name);
        }
        if (!StringUtils.isEmpty(userName)) {
            wrapper.eq(Admin::getUsername, userName);
        }
        IPage<Admin> adminPages = baseMapper.selectPage(adminPage, wrapper);
        return adminPages;
    }
}
