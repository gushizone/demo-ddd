package tk.gushizone.ddd.generator.core;

import com.baomidou.mybatisplus.generator.config.DataSourceConfig;
import com.baomidou.mybatisplus.generator.config.GlobalConfig;
import com.baomidou.mybatisplus.generator.config.PackageConfig;
import com.baomidou.mybatisplus.generator.config.StrategyConfig;
import lombok.Data;

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
}
