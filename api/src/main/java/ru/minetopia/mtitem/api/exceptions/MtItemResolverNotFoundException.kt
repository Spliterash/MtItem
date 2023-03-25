@file:Suppress("MemberVisibilityCanBePrivate", "CanBeParameter")

package ru.minetopia.mtitem.api.exceptions

class MtItemResolverNotFoundException(val id: String) : MtItemException("MtItem resolver $id not found")