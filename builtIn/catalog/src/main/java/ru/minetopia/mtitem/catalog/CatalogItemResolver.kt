package ru.minetopia.mtitem.catalog

import org.bukkit.inventory.ItemStack
import org.springframework.stereotype.Component
import ru.minetopia.mtitem.api.exceptions.MtItemNotFoundException
import ru.minetopia.mtitem.api.resolver.ItemResolver
import ru.minetopia.mtitem.catalog.service.CatalogService

@Component
class CatalogItemResolver(
    private val catalogService: CatalogService,
) : ItemResolver {
    override val domain = "catalog"

    override fun resolve(text: String): ItemStack {
        val configuration = catalogService.find(text) ?: throw MtItemNotFoundException()
        val stack = configuration.item.item()

        if (configuration.name != null || configuration.description != null) {
            stack.editMeta { meta ->
                configuration.name?.let { name -> meta.displayName(name) }
                configuration.description?.let { description -> meta.lore(description) }
            }
        }

        return stack
    }
}