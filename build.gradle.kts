plugins {
    id("java")
    id("com.github.johnrengelman.shadow") version "7.1.2"
}

group = "org.playbox.lobby"
version = "1.0-SNAPSHOT"

repositories {
    mavenCentral()
    maven("https://jitpack.io")
    maven("https://repo.unnamed.team/repository/unnamed-public/")
}

dependencies {
    implementation("org.reflections:reflections:0.10.2")
    implementation("team.unnamed:creative-api:0.4.1-SNAPSHOT")
    implementation("team.unnamed:creative-server:0.3.0-SNAPSHOT")
    implementation("com.github.Minestom:Minestom:-SNAPSHOT") {
        isChanging = true
    }
}

java {
    toolchain {
        languageVersion.set(JavaLanguageVersion.of(17))
    }
}

tasks {
    jar {
        manifest {
            attributes["Main-Class"] = "org.playbox.lobby.Server"
        }
    }
    build {
        dependsOn(shadowJar)
    }
    test {
        useJUnitPlatform()
    }
    shadowJar {
        archiveName = "server.jar"
        
        mergeServiceFiles()
        archiveClassifier.set("") // Prevent the -all suffix
    }
}