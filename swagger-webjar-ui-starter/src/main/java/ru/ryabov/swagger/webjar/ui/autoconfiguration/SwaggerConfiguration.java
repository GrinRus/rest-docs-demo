package ru.ryabov.swagger.webjar.ui.autoconfiguration;

import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.apache.commons.io.IOUtils;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.Resource;
import org.springframework.util.AntPathMatcher;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.resource.ResourceTransformer;
import org.springframework.web.servlet.resource.ResourceTransformerChain;
import org.springframework.web.servlet.resource.TransformedResource;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.stream.Collectors;

@Configuration
@EnableConfigurationProperties(SwaggerProperties.class)
@ConditionalOnProperty(prefix = "swagger", value = "enabled", matchIfMissing = true)
public class SwaggerConfiguration {

    @Bean
    public WebMvcConfigurer swaggerWebConfigurer(SwaggerProperties properties) {
        return new SwaggerWebConfigurer(properties);
    }

    @AllArgsConstructor
    public static class SwaggerWebConfigurer implements WebMvcConfigurer {

        private SwaggerProperties properties;

        @Override
        public void addResourceHandlers(ResourceHandlerRegistry registry) {
            SwaggerProperties.SwaggerUI ui = properties.getUi();
            if (ui.isEnabled()) {
                registry.addResourceHandler(ui.getIndexHandler().getResourceHandler())
                        .addResourceLocations("classpath:/META-INF/resources/webjars/")
                        .resourceChain(false)
                        .addTransformer(new SwaggerIndexTransformer(properties));
            }
            SwaggerProperties.ResourceHandler api = properties.getSpecs();
            if (api.isEnabled()) {
                registry.addResourceHandler(api.getResourceHandler())
                        .addResourceLocations(api.getResourceLocation());
            }
        }
    }

    @RequiredArgsConstructor
    public static class SwaggerIndexTransformer implements ResourceTransformer {

        private static final String DEFAULT_URL_ATTRIBUTE = "url: \"https://petstore.swagger.io/v2/swagger.json\"";

        private final SwaggerProperties properties;

        @Override
        public Resource transform(HttpServletRequest request, Resource resource, ResourceTransformerChain transformerChain) throws IOException {
            final AntPathMatcher antPathMatcher = new AntPathMatcher();
            boolean isIndexFound = antPathMatcher.match("**/swagger-ui/**/index.html", resource.getURL().toString());
            if (isIndexFound) {
                String html = IOUtils.toString(resource.getInputStream(), StandardCharsets.UTF_8.displayName());
                html = html.replace(DEFAULT_URL_ATTRIBUTE, buildURLsAttribute());
                return new TransformedResource(resource, html.getBytes());
            }
            return resource;
        }

        private String buildURLsAttribute() {
            String urls = properties.getUi().getApis().stream()
                    .map(api -> "{ url: \"" + api.getUrl() + "\", name: \"" + api.getName() + "\" }")
                    .collect(Collectors.joining(","));
            return "urls: [" + urls + "]";
        }
    }
}
