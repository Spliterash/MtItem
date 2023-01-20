plugins {
    `maven-publish`
}
java {
    withSourcesJar()
}
publishing {
    publications {
        create<MavenPublication>("maven") {
            groupId = "ru.minetopia"
            artifactId = rootProject.name
            from(components["java"])
        }
    }

    repositories {
        mavenLocal()
        maven {
            name = "nexus"
            url = uri("https://repo.spliterash.ru/" + rootProject.name)
            credentials {
                username = findProperty("SPLITERASH_NEXUS_USR")?.toString()
                password = findProperty("SPLITERASH_NEXUS_PSW")?.toString()
            }
        }
    }
}