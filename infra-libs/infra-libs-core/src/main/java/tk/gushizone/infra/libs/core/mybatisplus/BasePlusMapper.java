package tk.gushizone.infra.libs.core.mybatisplus;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.extension.conditions.query.LambdaQueryChainWrapper;
import com.baomidou.mybatisplus.extension.conditions.update.LambdaUpdateChainWrapper;
import com.baomidou.mybatisplus.extension.toolkit.ChainWrappers;

/**
 * BaseMapper 增强
 *
 * @author gushizone
 * @since 2022/8/24 10:36
 */
public interface BasePlusMapper<T> extends BaseMapper<T> {

    /**
     * lambdaQuery
     */
    default LambdaQueryChainWrapper<T> lambdaQuery() {
        return ChainWrappers.lambdaQueryChain(this);
    }

    /**
     * lambdaUpdate
     */
    default LambdaUpdateChainWrapper<T> lambdaUpdate() {
        return ChainWrappers.lambdaUpdateChain(this);
    }
}
