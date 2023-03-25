@file:Suppress("CanBeParameter", "MemberVisibilityCanBePrivate")

package ru.minetopia.mtitem.api.exceptions

import ru.minetopia.mtitem.api.resolver.ItemResolver

class MtItemNotFoundException(val id: String, val itemResolver: ItemResolver) : MtItemException(
    "MtItem not found by id $id in resolver ${itemResolver.domain}"
)
