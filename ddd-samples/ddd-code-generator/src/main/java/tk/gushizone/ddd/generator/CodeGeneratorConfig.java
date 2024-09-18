package tk.gushizone.ddd.generator;

import cn.hutool.core.io.BomReader;
import cn.hutool.core.io.IoUtil;
import cn.hutool.core.io.resource.ResourceUtil;
import cn.hutool.core.lang.Assert;
import com.baomidou.mybatisplus.generator.config.DataSourceConfig;
import com.baomidou.mybatisplus.generator.config.GlobalConfig;
import com.baomidou.mybatisplus.generator.config.PackageConfig;
import com.baomidou.mybatisplus.generator.config.StrategyConfig;
import lombok.Data;
import org.yaml.snakeyaml.Yaml;
import org.yaml.snakeyaml.introspector.BeanAccess;

/**
 * @author gushizone
 * @since 2024/9/16
 **/
@Data
public class CodeGeneratorConfig {

    private GlobalConfig global;

    private DataSourceConfig dataSource;

    private PackageConfig packages;

    private StrategyConfig strategy;

    public static CodeGeneratorConfig loadByPath(String path) {
        BomReader reader = IoUtil.getBomReader(ResourceUtil.getStream(path));
        Assert.notNull(reader, "Reader must be not null !");
        final Yaml yaml = new Yaml();
        yaml.setBeanAccess(BeanAccess.FIELD);
        try {
            return yaml.loadAs(reader, CodeGeneratorConfig.class);
        } finally {
            IoUtil.close(reader);
        }
    }
}
