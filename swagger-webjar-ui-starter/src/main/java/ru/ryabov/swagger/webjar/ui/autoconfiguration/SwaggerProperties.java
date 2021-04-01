package ru.ryabov.swagger.webjar.ui.autoconfiguration;

import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

import java.util.ArrayList;
import java.util.List;

@Data
@ConfigurationProperties("swagger")
public class SwaggerProperties {
    private ResourceHandler specs = new ResourceHandler("/specs/**", "classpath:/META-INF/specs/", true);
    private SwaggerUI ui = new SwaggerUI();

    @Data
    @AllArgsConstructor
    public static class ResourceHandler {
        private String resourceHandler;
        private String resourceLocation;
        private boolean enabled;
    }

    @Data
    public static class SwaggerUI {
        private boolean enabled = true;
        private ResourceHandler indexHandler = new ResourceHandler("/webjars/**", "", true);
        private List<SwaggerAPI> apis = new ArrayList<>();
    }

    @Data
    public static class SwaggerAPI {
        private String url;
        private String name;
    }
}
