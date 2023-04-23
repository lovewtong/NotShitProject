package com.Zlibrary.config.knife4j;

import com.github.xiaoymin.knife4j.spring.extension.OpenApiExtensionResolver;
import com.Zlibrary.config.swagger.ResponseStatus;
import io.swagger.annotations.ApiOperation;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import springfox.documentation.builders.*;
import springfox.documentation.oas.annotations.EnableOpenApi;
import springfox.documentation.schema.ScalarType;
import springfox.documentation.service.*;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @className: OpenApiConfig
 * @description: TODO 类描述
 * @author: sy
 * @date: 2022-04-14
 **/
@Configuration
@ConditionalOnProperty(name = "knife4j.enable", havingValue = "true")
public class OpenApiConfig {


    private List<Response> getGlobalResponse() {
        return ResponseStatus.HTTP_STATUS_ALL.stream().map(
                        a -> new ResponseBuilder().code(a.getResponseCode()).description(a.getDescription()).build())
                .collect(Collectors.toList());
    }

    private List<RequestParameter> getGlobalRequestParameters() {
        List<RequestParameter> parameters = new ArrayList<>();
        parameters.add(new RequestParameterBuilder()
                .name("id")
                .description("id test")
                .required(false) //required 是否可为空
                .in(ParameterType.QUERY)
                .query(q -> q.model(m -> m.scalarModel(ScalarType.INTEGER)))
                .build()
        );
        parameters.add(new RequestParameterBuilder()
                .name("title")
                .description("title test")
                .required(false)
                .in(ParameterType.QUERY)
                .query(q -> q.model(m -> m.scalarModel(ScalarType.STRING)))
                .build()
        );
        parameters.add(new RequestParameterBuilder()
                .name("author")
                .description("author test")
                .required(false)
                .in(ParameterType.QUERY)
                .query(q -> q.model(m -> m.scalarModel(ScalarType.STRING)))
                .build()
        );
        parameters.add(new RequestParameterBuilder()
                .name("essay")
                .description("essay test")
                .required(false)
                .in(ParameterType.QUERY)
                .query(q -> q.model(m -> m.scalarModel(ScalarType.STRING)))
                .build()
        );

        return parameters;
    }


    /**
     * @return api info
     */
    private ApiInfo apiInfo() {
        return new ApiInfoBuilder()
                .title("随机阅读")
                .description("随机阅读API文档")
                .contact(new Contact("sy", "https://github.com/wusy568", "wtwsy99@gmail.com"))
                .termsOfServiceUrl("http://pddbook.club/")
                .version("1.0")
                .build();
    }

}
