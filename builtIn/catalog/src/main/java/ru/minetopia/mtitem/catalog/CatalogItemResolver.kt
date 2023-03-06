package ru.minetopia.mtitem.catalog

import org.bukkit.inventory.ItemStack
import org.springframework.stereotype.Component
import ru.minetopia.mtitem.api.resolver.ItemResolver
import ru.minetopia.mtitem.catalog.service.CatalogService

@Component
class CatalogItemResolver(
    private val catalogService: CatalogService,
) : ItemResolver {
    override val domain = "catalog"

    override fun resolve(text: String): ItemStack {
        return catalogService.find(text)
    }

    override fun items(): Collection<String> {
        return catalogService.ids
    }
}