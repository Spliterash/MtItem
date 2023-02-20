package ru.minetopia.mtitem.catalog.port

import ru.minetopia.mtitem.catalog.conf.CatalogConf

interface CatalogItemRepository {
    suspend fun load(): CatalogConf
}