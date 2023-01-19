package ru.minetopia.mtitem.api

import org.bukkit.inventory.ItemStack
import ru.minetopia.mtitem.api.exceptions.MtItemNotFoundException
import ru.minetopia.mtitem.api.resolver.ItemResolver

interface MtItemApi {
    @Throws(MtItemNotFoundException::class)
    fun findResolver(domain: String): ItemResolver

    /**
     * Найти предмет
     *
     * Пример
     * itemsadder://minetopia_ui:empty
     */
    @Throws(MtItemNotFoundException::class)
    fun findItem(id: String): ItemStack

    fun registerResolver(resolver: ItemResolver)
}