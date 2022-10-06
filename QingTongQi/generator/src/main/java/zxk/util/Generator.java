package zxk.util;

import com.baomidou.mybatisplus.generator.AutoGenerator;
import com.baomidou.mybatisplus.generator.config.DataSourceConfig;
import com.baomidou.mybatisplus.generator.config.GlobalConfig;
import com.baomidou.mybatisplus.generator.config.PackageConfig;
import com.baomidou.mybatisplus.generator.config.StrategyConfig;
import com.baomidou.mybatisplus.generator.config.rules.NamingStrategy;
import com.baomidou.mybatisplus.generator.engine.FreemarkerTemplateEngine;
import zxk.common.BaseEntity;

import java.util.HashMap;
import java.util.Map;

public class Generator {
    public static void main(String[] args) {
        // 获取项目路径
        String parent = System.getProperty("user.dir");
        // 定义entity路径
        String entityPath = parent + "/entity/src/main/java/zxk/entity";
        // 定义其他项目名称
        String otherPath = "/admin";
        // 定义生成的表名
        String tableName = "ums_role_resource";
        // 创建生成器对象
        AutoGenerator mpg = new AutoGenerator();
        // 定义全局配置
        GlobalConfig gc = new GlobalConfig();
        gc.setOpen(false);
        // 配置所有者
        gc.setAuthor("zxk");
        // 去掉service前面的I
        gc.setServiceName("%sService");
        // 配置父项目路径
        gc.setOutputDir(parent);
        mpg.setGlobalConfig(gc);
        // 配置数据源
        DataSourceConfig dsc = new DataSourceConfig();
        dsc.setDriverName("com.mysql.jdbc.Driver");
        dsc.setUrl("jdbc:mysql://124.222.157.65:3306/db1?useUnicode=true&characterEncoding=utf8");
        dsc.setUsername("root");
        dsc.setPassword("123456");
        mpg.setDataSource(dsc);
        // 包配置
        PackageConfig pc = new PackageConfig();
        pc.setParent("zxk");
        // 自定义生成路径
        Map<String,String> pathInfo = new HashMap<>();
        pathInfo.put("entity_path", entityPath);
        pathInfo.put("mapper_path", parent + otherPath + "/src/main/java/zxk/mapper");
        pathInfo.put("xml_path", parent + otherPath + "/src/main/resources/zxk/mapper");
        pathInfo.put("service_path", parent + otherPath + "/src/main/java/zxk/service");
        pathInfo.put("service_impl_path", parent + otherPath + "/src/main/java/zxk/service/impl");
        pathInfo.put("controller_path", parent + otherPath + "/src/main/java/zxk/controller");
        pc.setPathInfo(pathInfo);
        mpg.setPackageInfo(pc);
        // 策略配置
        StrategyConfig sc = new StrategyConfig();
        // 把表名的下划线转成驼峰命名法
        sc.setNaming(NamingStrategy.underline_to_camel);
        // 把字段名下划线转驼峰
        sc.setColumnNaming(NamingStrategy.underline_to_camel);
        // 设置entity的父类
        sc.setSuperEntityClass(BaseEntity.class);
        // 设置不生成字段
        sc.setSuperEntityColumns("id");
        // 设置entity 都加 @Data注解
        sc.setEntityLombokModel(true);
        // 设置 所有controller都加@RestController注解
        sc.setRestControllerStyle(true);
        // 设置Controller类中 父目录
        // sc.setControllerMappingHyphenStyle(true);
        // 设置 生成的表名
        sc.setInclude(tableName);
        mpg.setStrategy(sc);
        // 设置模板
        mpg.setTemplateEngine(new FreemarkerTemplateEngine());
        // 生成代码
        mpg.execute();
    }
}
