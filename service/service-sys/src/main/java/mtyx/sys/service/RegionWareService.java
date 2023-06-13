package mtyx.sys.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;
import mtyx.model.sys.RegionWare;
import mtyx.vo.sys.RegionWareQueryVo;

/**
 * <p>
 * 城市仓库关联表 服务类
 * </p>
 *
 * @author atzzc
 * @since 2023-06-13
 */
public interface RegionWareService extends IService<RegionWare> {

    // 开通区域列表
    IPage<RegionWare> getRegionWareByKeyword(IPage<RegionWare> pageParm, RegionWareQueryVo regionWareQueryVo);

    // 添加开通区域
    void saveRegionWare(RegionWare regionWare);
}
