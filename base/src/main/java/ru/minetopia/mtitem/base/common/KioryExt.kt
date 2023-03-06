package ru.minetopia.mtitem.base.common

import net.kyori.adventure.text.Component
import net.kyori.adventure.text.format.TextDecoration
import net.kyori.adventure.text.minimessage.MiniMessage
import net.kyori.adventure.text.serializer.plain.PlainTextComponentSerializer
import org.bukkit.entity.Player

fun String.mm() = MiniMessage.miniMessage().deserialize(this)

fun Iterable<String>.mm() = this.map(String::mm)


fun Component.plain() = PlainTextComponentSerializer.plainText().serialize(this)

fun Iterable<Component>.plain() = this.map(Component::plain)

fun Component.resetStartFormatting(): Component = Component.text()
    .style {
        it.decoration(TextDecoration.ITALIC, false)
        it.decoration(TextDecoration.BOLD, false)
        it.decoration(TextDecoration.OBFUSCATED, false)
        it.decoration(TextDecoration.STRIKETHROUGH, false)
    }
    .append(this)
    .build()

fun Iterable<Component>.resetStartFormatting(): List<Component> = this.map(Component::resetStartFormatting)

fun Component.send(player: Player) {
    player.sendMessage(this)
}