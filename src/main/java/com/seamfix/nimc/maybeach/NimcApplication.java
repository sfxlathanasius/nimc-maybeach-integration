package com.seamfix.nimc.maybeach;

import com.github.ulisesbocchio.jar.resources.JarResourceLoader;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.core.env.StandardEnvironment;
import org.springframework.jms.annotation.EnableJms;
import org.springframework.scheduling.annotation.EnableAsync;

@EnableJms
@EnableAsync
@SpringBootApplication
@SuppressWarnings("PMD.UseUtilityClass")
public class NimcApplication {

    public static void main(String[] args) {
        StandardEnvironment environment = new StandardEnvironment();
        new SpringApplicationBuilder()
                .sources(NimcApplication.class)
                .environment(environment)
                .resourceLoader(new JarResourceLoader(environment, "resources.extract.dir"))
                .build()
                .run(args);
    }
}
