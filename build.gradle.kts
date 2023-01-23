import ru.spliterash.shadowkotlinrelocate.shadowjar.kotlinRelocate

plugins {
    id("java-library")
    kotlin("jvm") version "1.7.21"
    id("io.freefair.lombok") version "6.6"
    id("net.minecrell.plugin-yml.bukkit") version "0.5.2"
    id("com.github.johnrengelman.shadow") version "7.1.2"
    id("ru.spliterash.shadow-kotlin-relocate") version "1.0.0"
}


bukkit {
    name = "MtItem"
    main = "ru.minetopia.mtitem.app.MtItemPlugin"
    apiVersion = "1.13"
    authors = listOf("Spliterash")
    depend = listOf("SpringSpigot", "KotlinMc")
    softDepend = listOf("ItemsAdder", "HeadDatabase")
}

allprojects {
    apply(plugin = "java-library")
    apply(plugin = "kotlin")

    version = "1.0.0"

    repositories {
        mavenCentral()
        maven("https://repo.papermc.io/repository/maven-public/") // PaperAPI
        maven("https://repo.aikar.co/content/groups/aikar/") // ACF
        maven {
            url = uri("https://repo.spliterash.ru/group/")
        }
    }

    dependencies {
        compileOnly("io.papermc.paper:paper-api:1.19.2-R0.1-SNAPSHOT")
        compileOnly("ru.spliterash:spring-spigot:1.0.6")
        compileOnly("ru.spliterash:kotlin-mc:1.0.3")
    }

    tasks {
        compileJava {
            options.compilerArgs.add("-parameters");
        }
        compileKotlin {
            kotlinOptions.freeCompilerArgs = listOf("-Xcontext-receivers")
            kotlinOptions.javaParameters = true
        }
    }
}

val apiProject = project(":api")
configure(allprojects - apiProject) {
    dependencies {
        api(apiProject)
    }
}
subprojects.forEach { sub ->
    dependencies {
        api(sub)
    }
}

val startPath = "ru.minetopia.mtitem.shadow."
tasks.shadowJar {
    archiveVersion.set("")
    archiveClassifier.set("")
//    minimize()

    kotlinRelocate("co.aikar.commands", startPath + "acf")
    kotlinRelocate("co.aikar.locales", startPath + "locales")

    dependencies {
        allprojects.forEach {
            include(dependency(it))
        }

        include {
            it.moduleGroup in listOf("ru.spliterash")
        }
        include(dependency("co.aikar:acf-paper"))
    }
}

tasks {
    assemble { dependsOn(relocateKotlinMetadata) }
    jar { enabled = false; }
}