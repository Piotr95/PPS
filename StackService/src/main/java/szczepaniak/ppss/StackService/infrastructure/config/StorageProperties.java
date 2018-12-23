package szczepaniak.ppss.StackService.infrastructure.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Configuration;


@SuppressWarnings("ConfigurationProperties")
@Configuration
@ConfigurationProperties("storage")
@Data
public class StorageProperties {
    private String location = "upload-dir";

}
