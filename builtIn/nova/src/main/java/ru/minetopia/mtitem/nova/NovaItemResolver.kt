package ru.minetopia.mtitem.nova

import org.bukkit.inventory.ItemStack
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass
import org.springframework.stereotype.Component
import ru.minetopia.mtitem.api.exceptions.MtItemNotFoundException
import ru.minetopia.mtitem.api.resolver.ItemResolver
import xyz.xenondevs.nova.api.Nova

@Component
@ConditionalOnClass(Nova::class)
class NovaItemResolver : ItemResolver {
    override val domain = "nova"

    override fun resolve(text: String): ItemStack {
        return Nova.getNova().itemRegistry.getOrNull(text)?.createItemStack()
            ?: throw MtItemNotFoundException(text, this)
    }


    @Suppress("DEPRECATION")
    override fun isResolverItem(itemStack: ItemStack): Boolean {
        return Nova.getNova().materialRegistry.getOrNull(itemStack) != null
    }

    override fun items(): Collection<String> = listOf()
}