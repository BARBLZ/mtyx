package mtyx.sys.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import mtyx.exception.GlobalExceptionHandler;
import mtyx.exception.MtyxException;
import mtyx.model.sys.RegionWare;
import mtyx.result.ResultCodeEnum;
import mtyx.sys.mapper.RegionWareMapper;
import mtyx.sys.service.RegionWareService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import mtyx.vo.sys.RegionWareQueryVo;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

/**
 * <p>
 * 城市仓库关联表 服务实现类
 * </p>
 *
 * @author atzzc
 * @since 2023-06-13
 */
@Service
public class RegionWareServiceImpl extends ServiceImpl<RegionWareMapper, RegionWare> implements RegionWareService {

    // 开通区域列表
    @Override
    public IPage<RegionWare> getRegionWareByKeyword(IPage<RegionWare> pageParm, RegionWareQueryVo regionWareQueryVo) {
        String keyword = regionWareQueryVo.getKeyword();
        LambdaQueryWrapper<RegionWare> wrapper = new LambdaQueryWrapper<>();
        if (!StringUtils.isEmpty(keyword)) {
            wrapper.like(RegionWare::getRegionName, keyword).or().like(RegionWare::getWareName, keyword);
        }
        IPage<RegionWare> regionWareIPage = baseMapper.selectPage(pageParm, wrapper);
        return regionWareIPage;
    }

    @Override
    public void saveRegionWare(RegionWare regionWare) {
        // 判断该区域是否已经开通
        LambdaQueryWrapper<RegionWare> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(RegionWare::getRegionId, regionWare.getRegionId());
        Integer count = baseMapper.selectCount(wrapper);
        if (count > 0) {
            // 已经开通
            throw new MtyxException(ResultCodeEnum.REGION_OPEN);
        }
        baseMapper.insert(regionWare);
    }

    // 取消开通区域
    @Override
    public void updateStatus(Long id, Integer status) {
        RegionWare regionWare = baseMapper.selectById(id);
        regionWare.setStatus(status);
        baseMapper.updateById(regionWare);
    }
}
