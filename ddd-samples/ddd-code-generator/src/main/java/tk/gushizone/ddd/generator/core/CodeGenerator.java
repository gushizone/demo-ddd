package tk.gushizone.ddd.generator.core;

import cn.hutool.core.io.FileUtil;
import cn.hutool.json.JSONUtil;
import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.generator.FastAutoGenerator;
import com.baomidou.mybatisplus.generator.config.*;
import com.baomidou.mybatisplus.generator.config.builder.CustomFile;
import com.baomidou.mybatisplus.generator.config.po.TableInfo;
import com.baomidou.mybatisplus.generator.engine.FreemarkerTemplateEngine;
import org.apache.ibatis.annotations.Mapper;
import tk.gushizone.ddd.generator.util.YamlHelper;
import tk.gushizone.infra.libs.base.constant.DbColumns;
import tk.gushizone.infra.libs.base.entity.DomainEntity;
import tk.gushizone.infra.libs.core.rest.DomainRsp;

import java.util.*;
import java.util.stream.Collectors;

/**
 * @author gushizone
 * @since 2024/9/17
 */
public class CodeGenerator {

    public static void main(String[] args) {

        CodeGeneratorConfig config = YamlHelper.loadByPath("code-generator.yml", CodeGeneratorConfig.class);
        execute(config);
    }

    public static void execute(CodeGeneratorConfig config) {

        GlobalConfig global = config.getGlobal();
        DataSourceConfig dataSource = config.getDataSource();
        PackageConfig packages = config.getPackages();
        StrategyConfig strategy = config.getStrategy();

        // 路径兼容, 如用户相对目录等
        String outputDir = FileUtil.normalize(global.getOutputDir());

        FastAutoGenerator.create(dataSource.getUrl(), dataSource.getUsername(), dataSource.getPassword())
                .globalConfig(builder -> builder
                        .author(global.getAuthor()) // 设置作者
                        .outputDir(outputDir) // 指定输出目录
                        .dateType(global.getDateType()) // 日期类型
                )
                .packageConfig(builder -> builder.parent(packages.getParent()) // 设置父包名
                        .moduleName(packages.getModuleName()) // 设置父包模块名
                        .entity(packages.getEntity())
                        .service(packages.getService())
                        .serviceImpl(packages.getServiceImpl())
                        .mapper(packages.getMapper())
                        .xml(packages.getXml())
                        .controller(packages.getController())
                        .pathInfo(pathInfoMap(outputDir)) // 设置文件生成路径
                )
                .strategyConfig(builder -> builder
                        .addInclude(new ArrayList<>(strategy.getInclude())) // 设置需要生成的表名
                        .entityBuilder()
                        .superClass(strategy.entity().getSuperClass())
                        .naming(strategy.entity().getNaming())
                        .disableSerialVersionUID()
                        .enableLombok()
                        .enableChainModel()
                        .addSuperEntityColumns(DbColumns.CREATED_BY, DbColumns.CREATED_AT, DbColumns.UPDATED_BY, DbColumns.UPDATED_AT, DbColumns.DELETED_AT)
                        .mapperBuilder()
                        .mapperAnnotation(Mapper.class)
                        .superClass(strategy.mapper().getSuperClass())
                        .controllerBuilder()
                        .enableRestStyle()
                        .enableHyphenStyle()
                        .enableFileOverride()
                        .serviceBuilder()
                        .disable()
                )
                .injectionConfig(builder -> builder.beforeOutputFile((tableInfo, objectMap) -> {

                                    objectMap.put("adapter", initAdapterMap(tableInfo, objectMap));

                                })
                                .customFile(customFiles(outputDir))
                )
                .templateEngine(new FreemarkerTemplateEngine()) // 使用Freemarker引擎模板，默认的是Velocity引擎模板
                .execute();
    }

    /**
     * 模板变量配置 - adapter
     */
    private static Object initAdapterMap(TableInfo tableInfo, Map<String, Object> objectMap) {

        Map<String, Object> resultMap = new HashMap<>();

        Set<String> importPackages = tableInfo.getImportPackages().stream()
                .filter(e -> !TableName.class.getName().equals(e))
                .filter(e -> !DomainEntity.class.getName().equals(e))
                .collect(Collectors.toSet());
        importPackages.add(DomainRsp.class.getName());
        resultMap.put("importPackages", new ArrayList<>(importPackages));

        resultMap.put("package", JSONUtil.parseObj(objectMap).getJSONObject("package").get("Parent")
                + ".adapter.in.web.dto.rsp");
        resultMap.put("entity", tableInfo.getEntityName() + "Rsp");
        resultMap.put("superEntityClass", DomainRsp.class.getSimpleName());

        resultMap.put("springdoc", true);
        return resultMap;
    }

    /**
     * 自定义配置模板文件
     */
    private static List<CustomFile> customFiles(String outputDir) {
        List<CustomFile> customFiles = new ArrayList<>();

        customFiles.add(new CustomFile.Builder()
                .fileName("Rsp.java")
                .templatePath("/templates/adapter/rsp.java.ftl")
                .packageName("adapter.in.web.dto.rsp")
                .filePath(outputDir)
                .enableFileOverride()
                .build());

        return customFiles;
    }

    /**
     * 设置文件的输出位置
     */
    private static Map<OutputFile, String> pathInfoMap(String outputDir) {
        Map<OutputFile, String> resultMap = new HashMap<>();

        resultMap.put(OutputFile.controller, outputDir + "/adapter/in/web");
        resultMap.put(OutputFile.entity, outputDir + "/domain/model/entity");
        resultMap.put(OutputFile.mapper, outputDir + "/infrastructure/repository/db/mapper");
        resultMap.put(OutputFile.xml, outputDir + "/resources/mapper");

        return resultMap;
    }

}
