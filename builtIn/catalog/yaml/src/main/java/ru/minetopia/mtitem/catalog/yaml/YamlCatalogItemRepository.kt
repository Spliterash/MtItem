package ru.minetopia.mtitem.catalog.yaml

import com.fasterxml.jackson.core.type.TypeReference
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import org.bukkit.plugin.java.JavaPlugin
import org.springframework.stereotype.Component
import ru.minetopia.mtitem.catalog.conf.CatalogConf
import ru.minetopia.mtitem.catalog.conf.CatalogItemConf
import ru.minetopia.mtitem.catalog.port.CatalogItemRepository
import ru.spliterash.springspigot.json.services.YamlService
import java.io.File

@Component
class YamlCatalogItemRepository(
    plugin: JavaPlugin,
    private val yamlService: YamlService,
) : CatalogItemRepository {
    private val folder = File(plugin.dataFolder, "catalog")

    override suspend fun load(): CatalogConf = withContext(Dispatchers.IO) {
        if (!folder.isDirectory) {
            folder.mkdirs()

            val exampleItem = CatalogItemConf(
                "minecraft://stone",
                "<gray>Обычный <white>камень",
                listOf("<gray>Самый обычный <white>камень")
            )
            val map = mapOf(
                "example_catalog_item" to exampleItem
            )


            val exampleFile = File(folder, "example.yml")

            yamlService.save(exampleFile, map)

            return@withContext CatalogConf(map)
        }


        val map = hashMapOf<String, CatalogItemConf>()
        for (file in folder.listFiles()) {
            val loaded = yamlService.load(file, object : TypeReference<Map<String, CatalogItemConf>>() {})

            map.putAll(loaded)
        }

        CatalogConf(map)
    }
}