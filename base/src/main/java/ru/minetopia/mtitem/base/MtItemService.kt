@file:Suppress("unused", "PrivatePropertyName")

package ru.minetopia.mtitem.base

import org.bukkit.inventory.ItemStack
import org.springframework.stereotype.Component
import ru.minetopia.mtitem.api.MtItemApi
import ru.minetopia.mtitem.api.MtItemApiHolder
import ru.minetopia.mtitem.api.exceptions.MtItemInvalidSyntaxException
import ru.minetopia.mtitem.api.exceptions.MtItemNotFoundException
import ru.minetopia.mtitem.api.resolver.ItemResolver
import java.util.regex.Pattern

private val PATTERN = Pattern.compile("([a-z]+)://(.*)")

@Component
class MtItemService(
    builtInResolvers: List<ItemResolver>
) : MtItemApi {
    private val resolvers = hashMapOf<String, ItemResolver>()

    init {
        builtInResolvers.forEach(::registerResolver)
        MtItemApiHolder.set(this)
    }

    override fun findResolver(domain: String): ItemResolver = resolvers[domain] ?: throw MtItemNotFoundException()


    override fun findItem(id: String): ItemStack {
        val matcher = PATTERN.matcher(id)
        if (!matcher.matches()) throw MtItemInvalidSyntaxException()
        val domain = matcher.group(1) ?: throw MtItemInvalidSyntaxException()
        val path = matcher.group(2) ?: throw MtItemInvalidSyntaxException()

        val resolver = findResolver(domain)

        return resolver.resolve(path)
    }

    override fun registerResolver(resolver: ItemResolver) {
        resolvers[resolver.domain] = resolver
    }
}