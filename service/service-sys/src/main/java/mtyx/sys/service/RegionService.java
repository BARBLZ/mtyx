package mtyx.sys.service;

import mtyx.model.sys.Region;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * <p>
 * 地区表 服务类
 * </p>
 *
 * @author atzzc
 * @since 2023-06-13
 */
public interface RegionService extends IService<Region> {

    // 根据区域关键字查询区域列表信息
    List<Region> getRegionByKeyWord(String keyword);
}
