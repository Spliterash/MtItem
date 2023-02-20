rootProject.name = "MtItem"

include(
    "api",
    "base",
    "builtIn:catalog",
    "builtIn:catalog:yaml",
    "builtIn:bin",
    "builtIn:itemsadder",
    "builtIn:hdb",
    "builtIn:minecraft",
    "commands",
)
pluginManagement {
    repositories {
        gradlePluginPortal()
        maven {
            url = uri("https://repo.spliterash.ru/group")
        }
    }
}