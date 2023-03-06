package ru.minetopia.mtitem.bin

import net.kyori.adventure.text.Component.text
import net.kyori.adventure.text.format.NamedTextColor
import org.bukkit.Material
import org.bukkit.inventory.ItemStack
import org.springframework.stereotype.Component
import ru.minetopia.mtitem.api.factory.MtItemFactory
import ru.minetopia.mtitem.api.resolver.ItemResolver
import java.util.*

@Component
class BinItemResolver : ItemResolver {
    override val domain = "bin"

    override fun resolve(text: String): ItemStack {
        return deserialize(text)
    }

    override fun resolveFactory(text: String): MtItemFactory {
        val stack = deserialize(text)

        return MtItemFactory { stack.clone() }
    }

    override fun items(): List<String> {
        return listOf()
    }

    private fun deserialize(text: String): ItemStack {
        val byteItemStack = Base64.getDecoder().decode(text)

        return try {
            ItemStack.deserializeBytes(byteItemStack)
        } catch (ex: Exception) {
            ItemStack(Material.STONE).apply {
                editMeta { it.displayName(text("Failed to deserialize item", NamedTextColor.RED)) }
            }
        }
    }
}