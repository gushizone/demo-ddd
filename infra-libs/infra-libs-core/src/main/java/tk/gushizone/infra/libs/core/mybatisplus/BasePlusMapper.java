package tk.gushizone.infra.libs.core.mybatisplus;

import com.baomidou.mybatisplus.core.enums.SqlMethod;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.TableInfo;
import com.baomidou.mybatisplus.core.metadata.TableInfoHelper;
import com.baomidou.mybatisplus.core.toolkit.CollectionUtils;
import com.baomidou.mybatisplus.core.toolkit.ReflectionKit;
import com.baomidou.mybatisplus.extension.conditions.query.LambdaQueryChainWrapper;
import com.baomidou.mybatisplus.extension.conditions.update.LambdaUpdateChainWrapper;
import com.baomidou.mybatisplus.extension.toolkit.ChainWrappers;
import com.baomidou.mybatisplus.extension.toolkit.SqlHelper;
import org.apache.ibatis.binding.MapperMethod;
import org.apache.ibatis.logging.LogFactory;

import java.util.Collection;

/**
 * BaseMapper 增强, 替代 IService 和 ServiceImpl
 * - currentModelClass 缓存 todo
 *
 * @author gushizone
 * @since 2022/8/24 10:36
 */
public interface BasePlusMapper<T> extends BaseMapper<T> {

    int DEFAULT_BATCH_SIZE = 1000;

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

    /**
     * 批量新增
     */
    default boolean saveBatch(Collection<T> list) {
        return saveBatch(list, DEFAULT_BATCH_SIZE);
    }

    /**
     * 批量新增
     */
    default boolean saveBatch(Collection<T> list, int batchSize) {
        Class<?> mapperClass = this.getClass().getInterfaces()[0];
        String sqlStatement = SqlHelper.getSqlStatement(mapperClass, SqlMethod.INSERT_ONE);
        return SqlHelper.executeBatch(this.currentModelClass(), LogFactory.getLog(mapperClass), list, batchSize, (sqlSession, entity) -> sqlSession.insert(sqlStatement, entity));
    }

    /**
     * 批量更新
     */
    default boolean updateBatchById(Collection<T> entityList, int batchSize) {
        Class<?> mapperClass = this.getClass().getInterfaces()[0];
        String sqlStatement = SqlHelper.getSqlStatement(mapperClass, SqlMethod.UPDATE_BY_ID);
        return SqlHelper.executeBatch(this.currentModelClass(), LogFactory.getLog(mapperClass), entityList, batchSize, (sqlSession, entity) -> {
            MapperMethod.ParamMap<T> param = new MapperMethod.ParamMap<>();
            param.put("et", entity);
            sqlSession.update(sqlStatement, param);
        });
    }

    /**
     * 批量删除
     */
    default boolean removeBatchByIds(Collection<?> list, int batchSize, boolean useFill) {
        Class<?> mapperClass = this.getClass().getInterfaces()[0];
        String sqlStatement = SqlHelper.getSqlStatement(mapperClass, SqlMethod.DELETE_BY_ID);
        TableInfo tableInfo = TableInfoHelper.getTableInfo(this.currentModelClass());
        return SqlHelper.executeBatch(this.currentModelClass(), LogFactory.getLog(mapperClass), list, batchSize, (sqlSession, e) -> {
            if (useFill && tableInfo.isWithLogicDelete()) {
                if (this.currentModelClass().isAssignableFrom(e.getClass())) {
                    sqlSession.update(sqlStatement, e);
                } else {
                    T instance = tableInfo.newInstance();
                    tableInfo.setPropertyValue(instance, tableInfo.getKeyProperty(), e);
                    sqlSession.update(sqlStatement, instance);
                }
            } else {
                sqlSession.update(sqlStatement, e);
            }
        });
    }

    /**
     * 批量删除
     */
    default boolean removeBatchByIds(Collection<?> list, boolean useFill) {
        return this.removeBatchByIds(list, 1000, useFill);
    }

    /**
     * 批量删除
     */
    default boolean removeByIds(Collection<?> list) {
        if (CollectionUtils.isEmpty(list)) {
            return false;
        } else {
            TableInfo tableInfo = TableInfoHelper.getTableInfo(this.currentModelClass());
            if (tableInfo.isWithLogicDelete() && tableInfo.isWithUpdateFill()) {
                return this.removeBatchByIds(list, true);
            } else {
                return SqlHelper.retBool(this.deleteBatchIds(list));
            }
        }
    }

    /**
     * todo 缓存
     */
    @SuppressWarnings("unchecked")
    default Class<T> currentModelClass() {
        return (Class<T>) ReflectionKit.getSuperClassGenericType(this.getClass(), BasePlusMapper.class, 0);
    }
}
