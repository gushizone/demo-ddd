package tk.gushizone.ddd.generator;

import cn.hutool.core.io.IoUtil;
import cn.hutool.core.lang.Dict;
import cn.hutool.setting.yaml.YamlUtil;
import tk.gushizone.ddd.generator.core.CodeGenerator;
import tk.gushizone.ddd.generator.core.CodeGeneratorConfig;
import tk.gushizone.ddd.generator.util.YamlHelper;

import java.io.IOException;
import java.io.StringWriter;
import java.util.Map;
import java.util.Set;

/**
 * 代码生成启动器
 * - 配置优先级: bootstrap.yml > code-generator.yml
 *
 * @author gushizone
 * @since 2024/9/18
 */
public class Bootstrap {

    public static void main(String[] args) {

        Dict bootMap = YamlUtil.loadByPath("bootstrap.yml");
        Dict defaultMap = YamlUtil.loadByPath("code-generator.yml");

        mergeMap(bootMap, defaultMap);

        try (StringWriter write = new StringWriter()) {
            YamlUtil.dump(defaultMap, write);
            CodeGeneratorConfig config = YamlHelper.load(write.toString(), CodeGeneratorConfig.class);
            CodeGenerator.execute(config);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * 将fromMap的数据合并到toMap中
     */
    @SuppressWarnings("unchecked")
    public static void mergeMap(Map<String, Object> fromMap, Map<String, Object> toMap) {
        Set<String> keys = fromMap.keySet();
        for (String key : keys) {
            Object toValue = toMap.get(key);
            Object fromValue = fromMap.get(key);
            if (!toMap.containsKey(key) || !(toValue instanceof Map && fromValue instanceof Map)) {
                // 源配置中没有这个key，则直接将这个key对应的配置赋给源配置即可。
                // toValue是Map,fromValue不是Map，则表示要将源配置中的配置改成简单类型，不再有多层级了
                // fromValue是Map,toValue不是Map，则表示源配置只是一个简单的类型，现在要改成多层级的配置。
                toMap.put(key, fromValue);
            } else {
                // 源配置中有这个key，且fromValue是一个单一的值，则直接替换即可(此时toValue可能是一个)
                mergeMap((Map<String, Object>) fromValue, (Map<String, Object>) toValue);
            }
        }
    }
}
