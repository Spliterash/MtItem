package ru.minetopia.mtitem

import org.bukkit.Material
import org.bukkit.inventory.ItemStack
import org.springframework.stereotype.Component
import ru.minetopia.mtitem.api.exceptions.MtItemNotFoundException
import ru.minetopia.mtitem.api.resolver.ItemResolver

@Component
class MinecraftItemResolver : ItemResolver {
    override val domain = "minecraft"

    override fun resolve(text: String): ItemStack {
        val material = Material.matchMaterial(text) ?: throw MtItemNotFoundException()

        return ItemStack(material)
    }
}