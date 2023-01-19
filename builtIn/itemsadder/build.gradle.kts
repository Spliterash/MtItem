repositories {
    maven("https://maven.pkg.github.com/LoneDev6/API-ItemsAdder") {
        name = "githubPackages"
        credentials(PasswordCredentials::class)
    }
}

dependencies {
    compileOnly("dev.lone:api-itemsadder:3.2.5")
}