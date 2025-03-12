package ru.netology.SpringBootdemo.Config;

import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Bean;
import ru.netology.SpringBootdemo.SystemProfile.DevProfile;
import ru.netology.SpringBootdemo.SystemProfile.ProductionProfile;
import ru.netology.SpringBootdemo.SystemProfile.SystemProfile;

public class JavaConfig {
    @ConditionalOnProperty(name = "netology.profile.dev", havingValue = "true")
    @Bean
    public SystemProfile devProfile() {
        return new DevProfile();
    }

    @ConditionalOnProperty(name = "netology.profile.dev", havingValue = "false", matchIfMissing = true)
    @Bean
    public SystemProfile prodProfile() {
        return new ProductionProfile();
    }
}
