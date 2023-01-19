package ru.minetopia.mtitem.itemsadder

import dev.lone.itemsadder.api.CustomStack
import org.bukkit.inventory.ItemStack
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass
import org.springframework.stereotype.Component
import ru.minetopia.mtitem.api.exceptions.MtItemNotFoundException
import ru.minetopia.mtitem.api.resolver.ItemResolver

@Component
@ConditionalOnClass(CustomStack::class)
class ItemsAdderItemResolver : ItemResolver {
    override val domain: String
        get() = "itemsadder"

    override fun resolve(text: String): ItemStack {
        return CustomStack.getInstance(text)?.itemStack ?: throw MtItemNotFoundException()
    }
}