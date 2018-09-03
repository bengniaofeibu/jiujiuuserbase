package com.jiujiu.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.ParameterBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.schema.ModelRef;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.service.Parameter;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.util.ArrayList;
import java.util.List;

@Configuration
@EnableSwagger2
public class Swagger2 {

    @Bean
    public Docket createRestApi() {

        ParameterBuilder apiHeadPar = new ParameterBuilder();
        List<Parameter> pars = new ArrayList<Parameter>();

        addParameters("version","接口版本号","string",true,"1.0",apiHeadPar,pars);
        addParameters("certType","认证方式","string",true,"0",apiHeadPar,pars);
        addParameters("certification","签名","string",true,"72D08658938A4A66E9271B134D43A718",apiHeadPar,pars);
        addParameters("timestamp","时间戳","string",true,"1535511949000",apiHeadPar,pars);
        addParameters("plat","平台","string",true,"ehome",apiHeadPar,pars);
        addParameters("isTest","测试","int",true,"99",apiHeadPar,pars);
        addParameters("appVersion","app版本号","string",true,"V4.0.0",apiHeadPar,pars);
        addParameters("osInformation","操作系统版本信息",
                "string",false,"iPhone 7 Plus (CDMA)-ios 11.2.1",apiHeadPar,pars);
        addParameters("channel","渠道","string",false,"ehome",apiHeadPar,pars);
        addParameters("longitude","经度","string",false,"121.380418",apiHeadPar,pars);
        addParameters("latitude","纬度","string",false,"31.187735",apiHeadPar,pars);
        addParameters("deviceToken","终端的唯一码","string",false,"0",apiHeadPar,pars);
        addParameters("userId","用户id","string",false,"a3da0759972141a785655d685f6f8942",apiHeadPar,pars);

        return new Docket(DocumentationType.SWAGGER_2)
                .apiInfo(apiInfo())
                .select()
                //为当前包路径
                .apis(RequestHandlerSelectors.basePackage("com.jiujiu.controller"))
                .paths(PathSelectors.any())
                .build()
                .globalOperationParameters(pars);
    }
    //构建 api文档的详细信息函数,注意这里的注解引用的是哪个
    private ApiInfo apiInfo() {
        return new ApiInfoBuilder()
                //页面标题
                .title("Swagger2 构建RESTful API")
                //创建人
                .contact(new Contact("THZ", "http://www.baidu.com", ""))
                //版本号
                .version("1.0")
                //描述
                .description("API 描述")
                .build();
    }

    private void addParameters(String name,String desc,String type,Boolean flag,
                               String defaultValue,ParameterBuilder apiHeadPar,List<Parameter> pars){
        apiHeadPar.name(name).description(desc)
                .modelRef(new ModelRef(type)).parameterType("header")
                .required(flag).defaultValue(defaultValue).build();
        pars.add(apiHeadPar.build());
    }

}
