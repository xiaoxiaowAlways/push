package com.yps.shangqi.push.gen;

import org.mybatis.generator.api.MyBatisGenerator;
import org.mybatis.generator.config.Configuration;
import org.mybatis.generator.config.xml.ConfigurationParser;
import org.mybatis.generator.internal.DefaultShellCallback;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class MybatisGenTool {

  public static void main(String[] args) throws Exception {
    List<String> warnings = new ArrayList<String>();
    //是否覆盖
    boolean overwrite = true;
    String genCfg = "/gen/mbgConfiguration.xml";
    File configFile = new File(MybatisGenTool.class.getResource(genCfg).getFile());
    ConfigurationParser cp = new ConfigurationParser(warnings);
    Configuration config = cp.parseConfiguration(configFile);
    DefaultShellCallback callback = new DefaultShellCallback(overwrite);
    MyBatisGenerator myBatisGenerator = new MyBatisGenerator(config, callback, warnings);
    myBatisGenerator.generate(null);
  }

}