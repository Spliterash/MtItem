package ru.minetopia.mtitem.api.factory

import org.bukkit.inventory.ItemStack

fun interface MtItemFactory {
    fun item(): ItemStack
}