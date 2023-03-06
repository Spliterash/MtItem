package ru.minetopia.mtitem.hdb

import me.arcaniax.hdb.api.HeadDatabaseAPI
import org.bukkit.inventory.ItemStack
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass
import org.springframework.stereotype.Component
import ru.minetopia.mtitem.api.exceptions.MtItemNotFoundException
import ru.minetopia.mtitem.api.resolver.ItemResolver

@Component
@ConditionalOnClass(HeadDatabaseAPI::class)
class HeadDatabaseResolver : ItemResolver {
    private val api = HeadDatabaseAPI()
    override val domain = "hdb"

    override fun resolve(text: String): ItemStack = api.getItemHead(text) ?: throw MtItemNotFoundException()
    override fun items(): List<String> {
        return listOf()
    }
}