package com.neu.azuresql.config;

import com.github.xiaoymin.knife4j.spring.extension.OpenApiExtensionResolver;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;

@Configuration
public class Knife4jConfig implements WebMvcConfigurer {

    // 从application.yml获取当前系统的版本，application.yml是从Maven的pom文件中获取版本号的
    private final String VERSION;

    // 引入Knife4j提供的扩展类
    private final OpenApiExtensionResolver openApiExtensionResolver;

    // 这里报错不用管，只要在SpringBoot配置文件中配置了knife4j.enable=true，OpenApiExtensionResolver就可以自动注入
    @Autowired
    public Knife4jConfig(OpenApiExtensionResolver openApiExtensionResolver, @Value("1.0") String version) {     // 被@Value注入的参数，不会受到@Autowired自动装配的影响
        this.openApiExtensionResolver = openApiExtensionResolver;
        VERSION = version;
    }

    @Bean
    public Docket swaggerDefaultDocket() {
        String groupName = "people";
        return new Docket(DocumentationType.SWAGGER_2)  // 使用Swagger2的注解，而非OpenAPI3
                .apiInfo(apiInfo())
                /*
                    Docket名称，默认值为default。在配置了多个Docket的情况下才有用.
                    每个Docket都可以设置自己的注解类型，是使用Swagger2还是OpenAPI3。
                    每个Docket都包含了多个Controller；
                    如果是多模块开发，可以给每个模块都配置一个Docket；
                    不同的Docket扫描不同的Controller，这时就必须使用RequestHandlerSelectors.basePackage()来指定扫描的Controller了
                 */
                .groupName(groupName)
                .select()
                // 扫描使用@Api注解的Controller
                // 也可以使用withMethodAnnotation(ApiOperation.class)或basePackage(Controller所在包名)来配置扫描
                .apis(RequestHandlerSelectors.withClassAnnotation(Api.class))
                // 指定路径处理PathSelectors.any()代表所有的路径
                .paths(PathSelectors.any())
                .build()
                .extensions(openApiExtensionResolver.buildExtensions(groupName));
    }

    private ApiInfo apiInfo() {
        return new ApiInfoBuilder()
                .title("people")
                .description("people")
                // .termsOfServiceUrl("http://127.0.0.1:8080/swagger-ui.html")  // 自定义设置Swagger UI访问地址
                .contact(new Contact("None","https://www.sorry.none.com", "none@none.none"))   // 作者信息
                .version(VERSION)
                .build();
    }

    // 下面的配置必须有，否则导入Sa-Token并配置拦截器后，访问knife4j接口文档主页时会报错404
    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("doc.html").addResourceLocations("classpath:/META-INF/resources/");
        registry.addResourceHandler("/webjars/**").addResourceLocations("classpath:/META-INF/resources/webjars/");
    }
}
