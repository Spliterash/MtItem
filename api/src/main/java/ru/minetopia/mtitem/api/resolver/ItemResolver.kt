package ru.minetopia.mtitem.api.resolver

import org.bukkit.inventory.ItemStack
import ru.minetopia.mtitem.api.exceptions.MtItemNotFoundException

interface ItemResolver {
    val domain: String

    @Throws(MtItemNotFoundException::class)
    fun resolve(text: String): ItemStack
}