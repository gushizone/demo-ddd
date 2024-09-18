package tk.gushizone.ddd.generator;

import cn.hutool.json.JSONUtil;
import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.generator.FastAutoGenerator;
import com.baomidou.mybatisplus.generator.config.*;
import com.baomidou.mybatisplus.generator.config.builder.CustomFile;
import com.baomidou.mybatisplus.generator.config.po.TableInfo;
import com.baomidou.mybatisplus.generator.engine.FreemarkerTemplateEngine;
import org.apache.ibatis.annotations.Mapper;
import tk.gushizone.infra.libs.base.constant.Columns;
import tk.gushizone.infra.libs.base.entity.RevisionEntity;
import tk.gushizone.infra.libs.core.mybatisplus.RevisionModel;
import tk.gushizone.infra.libs.core.rest.RevisionRsp;

import java.util.*;
import java.util.stream.Collectors;

/**
 * @author gushizone
 * @since 2024/9/17
 */
public class CodeGenerator {

    public static void main(String[] args) {

        CodeGeneratorConfig config = CodeGeneratorConfig.loadByPath("code-generator.yml");

        GlobalConfig global = config.getGlobal();
        DataSourceConfig dataSource = config.getDataSource();
        PackageConfig packages = config.getPackages();
        StrategyConfig strategy = config.getStrategy();

        // 模版的自定义配置
        Map<String, Object> customMap = new HashMap<>();

        FastAutoGenerator.create(dataSource.getUrl(), dataSource.getUsername(), dataSource.getPassword())
                .globalConfig(builder -> builder
                        .author(global.getAuthor()) // 设置作者
                        .outputDir(global.getOutputDir()) // 指定输出目录
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
                        .pathInfo(pathInfoMap(global.getOutputDir())) // 设置文件生成路径
                )
                .strategyConfig(builder -> builder
                        .addInclude(new ArrayList<>(strategy.getInclude())) // 设置需要生成的表名
                        .entityBuilder()
                        .superClass(strategy.entity().getSuperClass())
                        .naming(strategy.entity().getNaming())
                        .disableSerialVersionUID()
                        .enableLombok()
                        .enableChainModel()
                        .addSuperEntityColumns(Columns.CREATED_BY, Columns.CREATED_AT, Columns.UPDATED_BY, Columns.UPDATED_AT, Columns.DELETED_AT)
                        .mapperBuilder()
                        .mapperAnnotation(Mapper.class)
                        .superClass(strategy.mapper().getSuperClass())
                )
                .injectionConfig(builder -> builder.beforeOutputFile((tableInfo, objectMap) -> {

                                    objectMap.put("domain", initDomainMap(tableInfo, objectMap));
                                    objectMap.put("adapter", initAdapterMap(tableInfo, objectMap));

                                })
                                .customFile(customFiles(global.getOutputDir()))
                )
                .templateEngine(new FreemarkerTemplateEngine()) // 使用Freemarker引擎模板，默认的是Velocity引擎模板
                .execute();
    }

    /**
     * 模板变量配置 - domain
     */
    private static Object initDomainMap(TableInfo tableInfo, Map<String, Object> objectMap) {

        Map<String, Object> resultMap = new HashMap<>();

        Set<String> importPackages = tableInfo.getImportPackages().stream()
                .filter(e -> !TableName.class.getName().equals(e))
                .filter(e -> !RevisionModel.class.getName().equals(e))
                .collect(Collectors.toSet());
        importPackages.add(RevisionEntity.class.getName());
        resultMap.put("importPackages", new ArrayList<>(importPackages));

        resultMap.put("package", JSONUtil.parseObj(objectMap).getJSONObject("package").get("Parent")
                + ".domain.model.entity");
        resultMap.put("entity", tableInfo.getEntityName() + "Entity");
        resultMap.put("superEntityClass", RevisionEntity.class.getSimpleName());
        return resultMap;
    }

    /**
     * 模板变量配置 - adapter
     */
    private static Object initAdapterMap(TableInfo tableInfo, Map<String, Object> objectMap) {

        Map<String, Object> resultMap = new HashMap<>();

        Set<String> importPackages = tableInfo.getImportPackages().stream()
                .filter(e -> !TableName.class.getName().equals(e))
                .filter(e -> !RevisionModel.class.getName().equals(e))
                .collect(Collectors.toSet());
        importPackages.add(RevisionRsp.class.getName());
        resultMap.put("importPackages", new ArrayList<>(importPackages));

        resultMap.put("package", JSONUtil.parseObj(objectMap).getJSONObject("package").get("Parent")
                + ".adapter.in.web.dto.rsp");
        resultMap.put("entity", tableInfo.getEntityName() + "Rsp");
        resultMap.put("superEntityClass", RevisionRsp.class.getSimpleName());

        resultMap.put("springdoc", true);
        return resultMap;
    }

    /**
     * 自定义配置模板文件
     */
    private static List<CustomFile> customFiles(String outputDir) {
        List<CustomFile> customFiles = new ArrayList<>();

        customFiles.add(new CustomFile.Builder()
                .fileName("Entity.java")
                .templatePath("/templates/domain/entity.java.ftl")
                .packageName("domain.model.entity")
                .filePath(outputDir)
                .enableFileOverride()
                .build());

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

        resultMap.put(OutputFile.entity, outputDir + "/infrastructure/repository/db/po");
        resultMap.put(OutputFile.mapper, outputDir + "/infrastructure/repository/db/mapper");
        resultMap.put(OutputFile.xml, outputDir + "/resources/mapper");

        return resultMap;
    }


}
