package ru.minetopia.mtitem.app

import ru.spliterash.springspigot.init.SpringSpigotPlugin

class MtItemPlugin :SpringSpigotPlugin() {
    override fun getAppClass()  = MtItemApplication::class.java
}