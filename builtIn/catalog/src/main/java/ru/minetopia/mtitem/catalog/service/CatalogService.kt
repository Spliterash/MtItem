package ru.minetopia.mtitem.catalog.service

import kotlinx.coroutines.runBlocking
import net.kyori.adventure.text.minimessage.MiniMessage
import org.apache.logging.log4j.LogManager
import org.springframework.stereotype.Component
import ru.minetopia.mtitem.api.MtItemApi
import ru.minetopia.mtitem.api.exceptions.MtItemNotFoundException
import ru.minetopia.mtitem.api.factory.MtItemFactory
import ru.minetopia.mtitem.base.common.Reloadable
import ru.minetopia.mtitem.catalog.configuration.CatalogItem
import ru.minetopia.mtitem.catalog.port.CatalogItemRepository
import javax.annotation.PostConstruct

private val log = LogManager.getLogger(CatalogService::class.java)

@Component
class CatalogService(
    private val mtItemApi: MtItemApi,
    private val repository: CatalogItemRepository
) : Reloadable {
    private val items = hashMapOf<String, CatalogItem>()

    @PostConstruct
    fun init() = runBlocking {
        reload()
    }

    override suspend fun reload() {
        items.clear()
        val config = repository.load()

        for ((id, conf) in config.catalog) {
            val factory: MtItemFactory = try {
                mtItemApi.findItemFactory(conf.id)
            } catch (ex: MtItemNotFoundException) {
                log.warn("Failed find " + conf.id + " for catalog item " + id)
                continue
            }

            CatalogItem(
                factory,
                conf.name?.toComponent(),
                conf.description?.toComponent()
            )
        }
    }

    private fun String.toComponent(): net.kyori.adventure.text.Component = MiniMessage.miniMessage().deserialize(this)
    private fun List<String>.toComponent() = this.map { it.toComponent() }

    fun find(id: String): CatalogItem? = items[id]
}