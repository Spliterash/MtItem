package ru.minetopia.mtitem.base.init

import org.springframework.beans.factory.InitializingBean
import org.springframework.stereotype.Component
import ru.minetopia.mtitem.api.resolver.ItemResolver
import ru.minetopia.mtitem.base.MtItemService

@Component
class MtItemServiceInitializer(
    private val service: MtItemService,
    private val builtIn: Collection<ItemResolver>
) : InitializingBean {
    override fun afterPropertiesSet() {
        for (itemResolver in builtIn) {
            service.registerResolver(itemResolver)
        }
    }
}