# MtItems

Скачать можно [отсюда](https://jenkins.spliterash.ru/job/public/job/MtItem/)

Мне надоело в каждом плагине создавать ItemStack'и вытаскивая их из других плагинов, поэтому я написал для себя
вспомогательный плагин, вытаскивающий айтемстаки

Так же есть апишка для добавления или получения

```kotlin
repositories {
    maven("https://repo.spliterash.ru/group")
}
dependencies {
    compileOnly("ru.minetopia:MtItem:1.0.0")
}
```