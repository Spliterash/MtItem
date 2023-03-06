package ru.minetopia.mtitem.commands.init

import co.aikar.commands.BaseCommand
import co.aikar.commands.PaperCommandManager
import org.springframework.beans.factory.DisposableBean
import org.springframework.beans.factory.InitializingBean
import org.springframework.stereotype.Component

@Component
open class BaseCommandInitializer(
    private val commandManager: PaperCommandManager,
    private val commands: List<BaseCommand>
) : InitializingBean, DisposableBean {
    override fun afterPropertiesSet() {
        for (command in commands) {
            commandManager.registerCommand(command)
        }
    }

    override fun destroy() {
        for (command in commands) {
            commandManager.unregisterCommand(command)
        }
    }
}