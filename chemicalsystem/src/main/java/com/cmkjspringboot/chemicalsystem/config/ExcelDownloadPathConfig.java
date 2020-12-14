package com.cmkjspringboot.chemicalsystem.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

@Configuration
public class ExcelDownloadPathConfig extends WebMvcConfigurerAdapter {

    // 引入全局文件下载路劲变量
    @Value("${cbs-excelsPath}")
    private String mExcelsPath;

    // 配置允许通过外部url访问指定的服务器路径
    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        if(mExcelsPath.equals("") || mExcelsPath.equals("${cbs-excelsPath}")){
            String excelsPath = ExcelDownloadPathConfig.class.getClassLoader().getResource("").getPath();
            System.out.print("1.上传配置类excelsPath=="+excelsPath+"\n");
            if(excelsPath.indexOf(".jar")>0){
                excelsPath = excelsPath.substring(0, excelsPath.indexOf(".jar"));
            }else if(excelsPath.indexOf("classes")>0){
                excelsPath = "file:"+excelsPath.substring(0, excelsPath.indexOf("classes"));
            }
            excelsPath = excelsPath.substring(0, excelsPath.lastIndexOf("/"))+"/chemicalfiles/";
            mExcelsPath = excelsPath;
        }
        System.out.print("excelsPath============="+mExcelsPath+"\n");
        //LoggerFactory.getLogger(WebAppConfig.class).info("imagesPath============="+mImagesPath+"\n");
        registry.addResourceHandler("/chemicalfiles/**").addResourceLocations(mExcelsPath);
        // TODO Auto-generated method stub
        System.out.print("2.上传配置类mExcelsPath=="+mExcelsPath+"\n");
        super.addResourceHandlers(registry);
    }
}
