@file:Suppress("unused", "PrivatePropertyName")

package ru.minetopia.mtitem.base

import org.bukkit.inventory.ItemStack
import org.springframework.stereotype.Component
import ru.minetopia.mtitem.api.MtItemApi
import ru.minetopia.mtitem.api.MtItemApiHolder
import ru.minetopia.mtitem.api.exceptions.MtItemInvalidSyntaxException
import ru.minetopia.mtitem.api.exceptions.MtItemResolverNotFoundException
import ru.minetopia.mtitem.api.factory.MtItemFactory
import ru.minetopia.mtitem.api.resolver.ItemResolver
import java.util.regex.Pattern

private val PATTERN = Pattern.compile("([a-z]+)://(.*)")

@Component
class MtItemService : MtItemApi {
    private val resolvers = hashMapOf<String, ItemResolver>()

    init {
        MtItemApiHolder.set(this)
    }

    override fun findResolver(domain: String): ItemResolver = resolvers[domain]
        ?: throw MtItemResolverNotFoundException(domain)

    override fun findResolver(itemStack: ItemStack): ItemResolver? {
        return resolvers.values.firstOrNull { it.isResolverItem(itemStack) }
    }

    override fun findItem(id: String): ItemStack {
        return splitAndFind(id) { path -> resolve(path) }
    }

    override fun findItemFactory(id: String): MtItemFactory {
        return splitAndFind(id) { this.resolveFactory(it) }
    }

    override fun registerResolver(resolver: ItemResolver) {
        resolvers[resolver.domain] = resolver
    }

    override fun resolvers(): List<ItemResolver> {
        return resolvers.values.toList()
    }

    private inline fun <T> splitAndFind(id: String, block: ItemResolver.(String) -> T): T {
        val matcher = PATTERN.matcher(id)
        if (!matcher.matches()) throw MtItemInvalidSyntaxException(id)
        val domain = matcher.group(1) ?: throw MtItemInvalidSyntaxException(id)
        val path = matcher.group(2) ?: throw MtItemInvalidSyntaxException(id)
        val resolver = findResolver(domain)

        return block(resolver, path)
    }
}