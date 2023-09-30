val pluginName: String by project

plugins {
    `java-library`
    id("io.papermc.paperweight.userdev") version "1.5.5" // Test
}

repositories {
    mavenCentral()

    maven("https://papermc.io/repo/repository/maven-public/")
    maven("https://ci.frostcast.net/plugin/repository/everything")

    maven("https://jitpack.io")
}

dependencies {
    paperweight.paperDevBundle("1.20.1-R0.1-SNAPSHOT")

    compileOnly("io.papermc.paper:paper-api:1.20.1-R0.1-SNAPSHOT")
    compileOnly("com.github.TownyAdvanced:towny:0.99.5.0")
    compileOnly("me.confuser:BarAPI:3.5")
}

tasks {
    assemble {
        dependsOn(reobfJar)
    }
    processResources {
        filesMatching("plugin.yml") {
            expand(mapOf(
                "name" to pluginName,
                "version" to version,
            ))
        }
    }
}

java {
    toolchain.languageVersion.set(JavaLanguageVersion.of(17))
}
