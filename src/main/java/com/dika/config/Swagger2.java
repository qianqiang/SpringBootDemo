package com.dika.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.ParameterBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.schema.ModelRef;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Parameter;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger.web.UiConfiguration;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.util.ArrayList;
import java.util.List;

@Configuration
@EnableSwagger2
public class Swagger2 {

	//是否开启swagger，正式环境一般是需要关闭的，可根据springboot的多环境配置进行设置
	@Value(value = "${swagger.enabled}")
	Boolean swaggerEnabled;

    @Bean
	public Docket createRestApi() {

    	//************* 开始 设置全局参数注释 一般设置htppheader参数说明*********************
    	List<Parameter> pars = new ArrayList<Parameter>();
		
		ParameterBuilder parBuilder = new ParameterBuilder();
		parBuilder.name("Cloud-Sender").description("00.1101<服务调用方编码 由Cloud中心维护人员根据编码规则在管理页面上进行注册后分配使用>").modelRef(new ModelRef("String")).parameterType("header").required(true);
		pars.add(parBuilder.build());
		
		parBuilder = new ParameterBuilder();
		parBuilder.name("Cloud-ServCode").description("00.1102.queryservice_synReq<服务编码 由Cloud中心自动生成，可通过内部服务查询获得各业务服务的编码，Cloud中心内部静态服务列表：00.1111.listServices（查询所有可用服务列表）、00.1111.myServices（查询我的可用服务列表）、00.1111.applyServices（申请服务使用）>").modelRef(new ModelRef("String")).parameterType("header").required(true);
		pars.add(parBuilder.build());
		
		parBuilder = new ParameterBuilder();
		parBuilder.name("Cloud-DynamicUri").description("/workorder/track/{custOrderCode}<调用具体业务服务，如需要使用动态uri时，在此参数中填写业务服务接口的动态uri.（可空）>").modelRef(new ModelRef("String")).parameterType("header").required(false);
		pars.add(parBuilder.build());
		
		parBuilder = new ParameterBuilder();
		parBuilder.name("Cloud-MsgId").description("40288d8e5bcc4cfe015bcf8286ec1e08<消息ID（消息唯一标识）>").modelRef(new ModelRef("String")).parameterType("header").required(true);
		pars.add(parBuilder.build());
		
		parBuilder = new ParameterBuilder();
		parBuilder.name("Cloud-Time").description("2017-11-14 13:00:15.410<请求发送时间 时间均采用yyyy-MM-dd HH24:mm:ss.SSS 格式>").modelRef(new ModelRef("String")).parameterType("header").required(true);
		pars.add(parBuilder.build());
		
		parBuilder = new ParameterBuilder();
		parBuilder.name("Cloud-NodeIP").description("127.0.0.1<服务调用方主机节点IP.（可空）>").modelRef(new ModelRef("String")).parameterType("header").required(false);
		pars.add(parBuilder.build());
    	//************* 结束 设置全局参数注释 一般设置htppheader参数说明*********************

	    return new Docket(DocumentationType.SWAGGER_2)
                .apiInfo(apiInfo())
				.enable(swaggerEnabled)
                .select()
                .apis(RequestHandlerSelectors.basePackage("com.dika")) //设置需要发布的API包路径
                .paths(PathSelectors.any())
//                .paths(PathSelectors.none())//如果是线上环境，添加路径过滤，设置为全部都不符合
                .build()
                .globalOperationParameters(pars);
	  }

	  @Bean
	  UiConfiguration uiConfig() {
	    return new UiConfiguration(
	        null,// url
	        "none",       // docExpansion          => none | list
	        "alpha",      // apiSorter             => alpha
	        "schema",     // defaultModelRendering => schema
	        UiConfiguration.Constants.DEFAULT_SUBMIT_METHODS,
	        false,        // enableJsonEditor      => true | false
	        true,         // showRequestHeaders    => true | false
	        60000L);      // requestTimeout => in milliseconds, defaults to null (uses jquery xh timeout)
	  }
	
	  //api初始化
	   @SuppressWarnings("deprecation")
	   ApiInfo apiInfo() {
		   ApiInfoBuilder apiInfoBuilder = new ApiInfoBuilder();
		   apiInfoBuilder.title("Demo title");
		   apiInfoBuilder.description("Demo description");
		   apiInfoBuilder.termsOfServiceUrl("no terms of service");
		//   apiInfoBuilder.contact("ztesoft oss");
		   apiInfoBuilder.license("dikakeji");
		   apiInfoBuilder.licenseUrl("https://github.com/springfox/springfox/blob/master/LICENSE");
		   apiInfoBuilder.version("2.0");
		 return  apiInfoBuilder.build();
		   
		}
	    /*************Swagger2 结束****************/

}
