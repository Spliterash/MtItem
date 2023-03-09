package ru.minetopia.mtitem.api.exceptions

@Suppress("MemberVisibilityCanBePrivate", "CanBeParameter")
class MtItemInvalidSyntaxException(
    val line: String
) : MtItemException("Failed parse mtitem, invalid syntax: '${line}'") {
}