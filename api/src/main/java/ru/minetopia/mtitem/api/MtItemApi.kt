package ru.minetopia.mtitem.api

import org.bukkit.inventory.ItemStack
import ru.minetopia.mtitem.api.exceptions.MtItemNotFoundException
import ru.minetopia.mtitem.api.factory.MtItemFactory
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

    /**
     * Найти фабрику для создания данного предмета
     *
     * Сделано из-за того, что некоторые плагины, такие как HDB, не могут отдать предмет сразу, поэтому если плагин хочет
     * "запечь" конфигурацию, то ему следует использовать эту фабрику
     */
    @Throws(MtItemNotFoundException::class)
    fun findItemFactory(id: String): MtItemFactory

    fun registerResolver(resolver: ItemResolver)

    fun resolvers(): List<ItemResolver>
    fun findResolver(itemStack: ItemStack): ItemResolver?
}