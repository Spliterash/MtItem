package ru.minetopia.mtitem.commands.init

import co.aikar.commands.PaperCommandManager
import org.springframework.beans.factory.InitializingBean
import org.springframework.stereotype.Component
import ru.minetopia.mtitem.api.MtItemApi

@Component
class AcfContextInitializer(
    private val paperCommandManager: PaperCommandManager,
    private val mtItemApi: MtItemApi,
) : InitializingBean {
    override fun afterPropertiesSet() {
        paperCommandManager.commandCompletions.registerAsyncCompletion("item") {
            mtItemApi.resolvers()
                .flatMap { resolver ->
                    resolver
                        .items()
                        .map { itemId -> "${resolver.domain}://${itemId}" }
                }
        }
    }
}