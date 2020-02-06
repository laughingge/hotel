package com.example.hotel.config;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurationSupport;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

import java.util.List;

@Component
public class ViewConfig extends WebMvcConfigurerAdapter {/**
 * 添加静态资源文件，外部可以直接访问地址
 *
 * @param registry
 */
@Override
public void addResourceHandlers(ResourceHandlerRegistry registry) {
    registry.addResourceHandler("/static/**").addResourceLocations("classpath:/static/");
    super.addResourceHandlers(registry);
}
    @Override
    public void configureMessageConverters(List<HttpMessageConverter<?>> converters) {
        ObjectMapper objectMapper = new ObjectMapper();

        //null字段不返回
        objectMapper.setSerializationInclusion(JsonInclude.Include.NON_NULL);

        // mybatis 使用懒加载后，返回JSON报错
        // 而且这个配置不能放在“null字段不返回”配置前面，否则"null字段不返回"配置会失效
        // @see <a href="https://blog.csdn.net/justinytsoft/article/details/53575236"></a>
        objectMapper.configure(SerializationFeature.FAIL_ON_EMPTY_BEANS, false);
        MappingJackson2HttpMessageConverter jackson2HttpMessageConverter = new MappingJackson2HttpMessageConverter();
        jackson2HttpMessageConverter.setObjectMapper(objectMapper);
        converters.add(jackson2HttpMessageConverter);
    }


}
