package ru.minetopia.mtitem.commands.init

import co.aikar.commands.PaperCommandManager
import org.bukkit.plugin.java.JavaPlugin
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration

@Configuration
open class ACFConfiguration {
    @Bean
    open fun paperCommandManager(plugin: JavaPlugin): PaperCommandManager {
        return PaperCommandManager(plugin)
    }
}