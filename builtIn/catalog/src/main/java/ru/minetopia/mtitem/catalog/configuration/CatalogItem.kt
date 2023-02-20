package ru.minetopia.mtitem.catalog.configuration

import net.kyori.adventure.text.Component
import ru.minetopia.mtitem.api.factory.MtItemFactory

class CatalogItem(
    val item: MtItemFactory,
    val name: Component?,
    val description: List<Component>?
)