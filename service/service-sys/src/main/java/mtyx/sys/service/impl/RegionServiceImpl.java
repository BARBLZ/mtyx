package mtyx.sys.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import mtyx.model.sys.Region;
import mtyx.sys.mapper.RegionMapper;
import mtyx.sys.service.RegionService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 * 地区表 服务实现类
 * </p>
 *
 * @author atzzc
 * @since 2023-06-13
 */
@Service
public class RegionServiceImpl extends ServiceImpl<RegionMapper, Region> implements RegionService {

    // 根据区域关键字查询区域列表信息
    @Override
    public List<Region> getRegionByKeyWord(String keyword) {
        LambdaQueryWrapper<Region> wrapper = new LambdaQueryWrapper<>();
        wrapper.like(Region::getName, keyword);
        List<Region> regionList = baseMapper.selectList(wrapper);
        return regionList;
    }
}
