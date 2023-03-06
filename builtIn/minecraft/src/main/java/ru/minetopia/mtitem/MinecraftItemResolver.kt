package ru.minetopia.mtitem

import org.bukkit.Material
import org.bukkit.inventory.ItemStack
import org.springframework.stereotype.Component
import ru.minetopia.mtitem.api.exceptions.MtItemNotFoundException
import ru.minetopia.mtitem.api.factory.MtItemFactory
import ru.minetopia.mtitem.api.resolver.ItemResolver

@Component
class MinecraftItemResolver : ItemResolver {
    override val domain = "minecraft"

    override fun resolve(text: String): ItemStack {
        val material = Material.matchMaterial(text) ?: throw MtItemNotFoundException()

        return ItemStack(material)
    }

    override fun resolveFactory(text: String): MtItemFactory {
        val stack = resolve(text)

        return MtItemFactory { stack.clone() }
    }

    override fun items(): List<String> {
        return Material.values().map { it.name.lowercase() }
    }
}