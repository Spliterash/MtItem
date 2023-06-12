package ru.minetopia.mtitem.api.resolver

import org.bukkit.inventory.ItemStack
import ru.minetopia.mtitem.api.exceptions.MtItemNotFoundException
import ru.minetopia.mtitem.api.factory.MtItemFactory

interface ItemResolver {
    val domain: String

    @Throws(MtItemNotFoundException::class)
    fun resolve(text: String): ItemStack

    @Throws(MtItemNotFoundException::class)
    fun resolveFactory(text: String): MtItemFactory {
        return MtItemFactory { resolve(text).clone() }
    }

    fun isResolverItem(itemStack: ItemStack): Boolean

    fun items(): Collection<String>
}