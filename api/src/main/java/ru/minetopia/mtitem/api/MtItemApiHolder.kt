package ru.minetopia.mtitem.api

object MtItemApiHolder {
    lateinit var api: MtItemApi
        private set

    fun set(api: MtItemApi) {
        if (this::api.isInitialized)
            throw IllegalArgumentException()

        MtItemApiHolder.api = api
    }
}