package ru.minetopia.mtitem.commands

import co.aikar.commands.BaseCommand
import co.aikar.commands.annotation.*
import org.bukkit.command.CommandSender
import org.bukkit.entity.Player
import org.bukkit.plugin.java.JavaPlugin
import org.springframework.stereotype.Component
import ru.minetopia.mtitem.api.MtItemApi
import ru.spliterash.springspigot.reload.ReloadService

@Component
@CommandAlias("mtitem")
class MtItemCommand(
    private val reloadService: ReloadService,
    private val plugin: JavaPlugin,
    private val mtItemApi: MtItemApi,
) : BaseCommand() {
    @Subcommand("give")
    @CommandPermission("mtitem.give")
    @CommandCompletion("@item @players")
    fun give(sender: CommandSender, item: String, @Optional @Flags("other,defaultself") player: Player) {
        val stack = mtItemApi.findItem(item)

        player.inventory.addItem(stack)

        sender.sendMessage("Done.")
    }

    @Subcommand("reload")
    @CommandPermission("mtitem.admin")
    fun reload(sender: CommandSender) {
        reloadService.reload().whenComplete { _, _ ->
            sender.sendMessage("Plugin reloaded")
        }
    }
}