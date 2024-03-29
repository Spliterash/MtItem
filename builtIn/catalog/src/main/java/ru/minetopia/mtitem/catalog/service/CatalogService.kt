package ru.minetopia.mtitem.catalog.service

import com.destroystokyo.paper.Namespaced
import org.bukkit.NamespacedKey
import org.bukkit.attribute.Attribute
import org.bukkit.inventory.ItemStack
import org.bukkit.persistence.PersistentDataType
import org.bukkit.plugin.java.JavaPlugin
import org.springframework.stereotype.Component
import ru.minetopia.mtitem.api.MtItemApi
import ru.minetopia.mtitem.base.common.mm
import ru.minetopia.mtitem.base.common.resetStartFormatting
import ru.minetopia.mtitem.catalog.configuration.CatalogItem
import ru.minetopia.mtitem.catalog.port.CatalogItemRepository
import ru.spliterash.kotlinmc.futureIO
import ru.spliterash.springspigot.reload.ReloadableBean
import java.util.*
import java.util.concurrent.CompletableFuture
import javax.annotation.PostConstruct

const val NBT_ID = "catalog_id"

@Component
class CatalogService(
    private val plugin: JavaPlugin,
    private val mtItemApi: MtItemApi,
    private val repository: CatalogItemRepository
) : ReloadableBean {
    private val items = hashMapOf<String, CatalogItem>()
    val ids: Collection<String> = Collections.unmodifiableCollection(items.keys)

    @PostConstruct
    fun init() {
        reloadBean().join()
    }

    override fun reloadBean(): CompletableFuture<Void?> = plugin.futureIO {
        items.clear()
        val config = repository.load()

        for ((id, conf) in config.catalog) {
            items[id] = CatalogItem(
                conf.id,
                null,
                conf.name?.mm()?.resetStartFormatting(),
                conf.description?.mm()?.resetStartFormatting()
            )
        }

        null
    }

    fun find(id: String): ItemStack? {
        val configuration = items[id] ?: return null

        var factory = configuration.itemFactory
        if (factory == null) {
            factory = mtItemApi.findItemFactory(configuration.id)
            configuration.itemFactory = factory
        }

        val stack = factory.item()

        stack.editMeta { meta ->
            configuration.name?.let { name -> meta.displayName(name) }
            configuration.description?.let { description -> meta.lore(description) }
            meta.persistentDataContainer.set(NamespacedKey(plugin, NBT_ID), PersistentDataType.STRING, id)
        }

        return stack
    }
}