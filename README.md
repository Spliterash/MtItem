# MtItems

Скачать можно [отсюда](https://jenkins.spliterash.ru/job/public/job/MtItem/)

Мне надоело в каждом плагине создавать ItemStack'и вытаскивая их из других плагинов, поэтому я написал для себя
вспомогательный плагин, вытаскивающий айтемстаки

Для запуска необходимы [SpringSpigot](https://github.com/Spliterash/SpringSpigot),
и [KotlinMc](https://github.com/Spliterash/KotlinMc)

Получить itemstack можно так
```kotlin
MtItemApiHolder.api.findItem("minecraft://stone")

MtItemApiHolder.api.findItem("itemsadder://namespace:id")

MtItemApiHolder.api.findItem("hdb://100")
```

Так же есть апишка для добавления или получения

```kotlin
repositories {
    maven("https://repo.spliterash.ru/group")
}
dependencies {
    compileOnly("ru.minetopia:MtItem:1.0.0")
}
```