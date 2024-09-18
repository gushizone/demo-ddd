package tk.gushizone.ddd.generator.util;

import cn.hutool.core.io.BomReader;
import cn.hutool.core.io.IoUtil;
import cn.hutool.core.io.resource.ResourceUtil;
import cn.hutool.core.lang.Assert;
import org.yaml.snakeyaml.Yaml;
import org.yaml.snakeyaml.introspector.BeanAccess;

/**
 * @author gushizone
 * @since 2024/9/18
 */
public class YamlHelper {

    public static <T> T loadByPath(String path, Class<T> clazz) {
        BomReader reader = IoUtil.getBomReader(ResourceUtil.getStream(path));
        Assert.notNull(reader, "Reader must be not null !");
        final Yaml yaml = new Yaml();
        yaml.setBeanAccess(BeanAccess.FIELD);
        try {
            return yaml.loadAs(reader, clazz);
        } finally {
            IoUtil.close(reader);
        }
    }

    public static <T> T load(String yml, Class<T> clazz) {
        final Yaml yaml = new Yaml();
        yaml.setBeanAccess(BeanAccess.FIELD);
        return yaml.loadAs(yml, clazz);
    }

}
