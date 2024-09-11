package tk.gushizone.infra.libs.base.util;

import cn.hutool.json.JSONUtil;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.google.common.collect.Sets;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.math.NumberUtils;

import java.math.BigDecimal;
import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.function.BinaryOperator;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.stream.Collectors;

/**
 * 模型工具类
 * - 主要基于 stream 实现
 * - 避免 NullPointerException 等常用异常
 * - 取 null
 *
 * @author gushizone@gmail.com
 * @since 2021-01-02 15:07
 */
@Slf4j
public class ModelUtils {

    /**
     * stream map
     */
    public static <T, R> List<R> map(Collection<T> collection, Function<T, R> function) {
        if (CollectionUtils.isEmpty(collection)) {
            return Lists.newArrayList();
        }
        return collection.stream()
                .filter(e -> e != null && function.apply(e) != null)
                .map(function)
                .collect(Collectors.toList());
    }

    /**
     * stream map，toSet
     */
    public static <T, R> Set<R> mapToSet(Collection<T> collection, Function<T, R> function) {
        if (CollectionUtils.isEmpty(collection)) {
            return Sets.newHashSet();
        }
        return collection.stream()
                .filter(e -> e != null && function.apply(e) != null)
                .map(function)
                .collect(Collectors.toSet());
    }

    /**
     * stream flatMap
     */
    public static <T, R> List<R> flatMap(Collection<T> collection, Function<T, List<R>> function) {
        if (CollectionUtils.isEmpty(collection)) {
            return Lists.newArrayList();
        }
        return collection.stream()
                .filter(e -> CollectionUtils.isNotEmpty(function.apply(e)))
                .flatMap(e -> function.apply(e).stream())
                .collect(Collectors.toList());
    }

    /**
     * stream toMap
     * - 避免 Duplicate key
     */
    public static <T, K> Map<K, T> toMap(Collection<T> collection, Function<T, K> keyMapper) {
        if (CollectionUtils.isEmpty(collection)) {
            return Maps.newHashMap();
        }
        return collection.stream()
                .filter(e -> e != null && keyMapper.apply(e) != null)
                .collect(Collectors.toMap(keyMapper, Function.identity(), merge()));
    }

    /**
     * stream toMap
     * 避免 null 和 Duplicate key
     */
    public static <T, K, V> Map<K, V> toMap(Collection<T> collection,
                                            Function<T, K> keyMapper,
                                            Function<T, V> valueMapper) {
        if (CollectionUtils.isEmpty(collection)) {
            return Maps.newHashMap();
        }
        return collection.stream()
                .filter(e -> e != null && keyMapper.apply(e) != null)
                .collect(Collectors.toMap(keyMapper, valueMapper, merge()));
    }

    /**
     * stream group
     */
    public static <T, K> Map<K, List<T>> groupBy(Collection<T> collection, Function<T, K> keyMapper) {
        if (CollectionUtils.isEmpty(collection)) {
            return Maps.newHashMap();
        }
        return collection.stream()
                .filter(e -> e != null && keyMapper.apply(e) != null)
                .collect(Collectors.groupingBy(keyMapper, Collectors.toList()));
    }

    /**
     * stream group
     */
    public static <T, K, V> Map<K, List<V>> groupBy(Collection<T> collection,
                                                    Function<T, K> keyMapper,
                                                    Function<T, V> valueMapper) {
        if (CollectionUtils.isEmpty(collection)) {
            return Maps.newHashMap();
        }
        return collection.stream()
                .filter(e -> e != null && keyMapper.apply(e) != null)
                .collect(Collectors.groupingBy(keyMapper, Collectors.mapping(valueMapper, Collectors.toList())));
    }

    /**
     * stream filter
     */
    public static <T> List<T> filter(Collection<T> collection, Predicate<T> predicate) {
        if (CollectionUtils.isEmpty(collection)) {
            return Lists.newArrayList();
        }
        return collection.stream()
                .filter(predicate)
                .collect(Collectors.toList());
    }

    /**
     * 计数 - 根据 key 去重 去null
     */
    public static <T, R> Long count(Collection<T> collection, Function<T, R> keyMapper) {
        if (CollectionUtils.isEmpty(collection)) {
            return NumberUtils.LONG_ZERO;
        }
        return collection.stream()
                .filter(e -> e != null && keyMapper.apply(e) != null)
                .map(keyMapper)
                .distinct()
                .count();
    }

    /**
     * 求和 - BigDecimal
     */
    public static <T> BigDecimal sum(Collection<T> collection, Function<T, BigDecimal> function) {
        if (CollectionUtils.isEmpty(collection)) {
            return BigDecimal.ZERO;
        }
        return collection.stream()
                .filter(e -> e != null && function.apply(e) != null)
                .map(function)
                .reduce(BigDecimal.ZERO, BigDecimal::add);
    }

    /**
     * 去重
     */
    public static <T, K> List<T> distinct(Collection<T> collection, Function<T, K> keyMapper) {
        if (CollectionUtils.isEmpty(collection)) {
            return Lists.newArrayList();
        }
        return collection.stream()
                .filter(e -> e != null && keyMapper.apply(e) != null)
                .filter(distinctByKey(keyMapper))
                .collect(Collectors.toList());

    }

    /**
     * 去重断言
     */
    public static <T, K> Predicate<T> distinctByKey(Function<T, K> keyExtractor) {
        Map<K, Boolean> map = Maps.newConcurrentMap();
        return t -> map.putIfAbsent(keyExtractor.apply(t), Boolean.TRUE) == null;
    }

    /**
     * key 冲突时的策略: 替换并打印日志
     */
    public static <U> BinaryOperator<U> merge() {
        return (a, b) -> {
            try {
                if (log.isDebugEnabled()) {
                    log.debug("The key of 'a' and 'b' are duplicated, use 'b'. a:{}, b:{}", JSONUtil.toJsonStr(a), JSONUtil.toJsonStr(b));
                }
            } catch (Exception e) {
                log.warn(e.getMessage(), e);
            }
            return b;
        };
    }
}
