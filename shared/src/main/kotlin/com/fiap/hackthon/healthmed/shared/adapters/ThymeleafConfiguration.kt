package com.fiap.hackthon.healthmed.shared.adapters

import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer
import org.thymeleaf.spring6.SpringTemplateEngine
import org.thymeleaf.templateresolver.ClassLoaderTemplateResolver

@Configuration
class ThymeleafConfiguration : WebMvcConfigurer {
    @Bean
    fun templateResolver(): ClassLoaderTemplateResolver {
        val resolver = ClassLoaderTemplateResolver()

        resolver.prefix = "templates/" // Location of thymeleaf template
        resolver.isCacheable = false // Turning of cache to facilitate template changes
        resolver.suffix = ".html" // Template file extension
        resolver.setTemplateMode("HTML") // Template Type
        resolver.characterEncoding = "UTF-8"

        return resolver
    }

    @Bean
    fun templateEngine(): SpringTemplateEngine {
        val engine = SpringTemplateEngine()
        engine.setTemplateResolver(templateResolver())

        return engine
    }
}
