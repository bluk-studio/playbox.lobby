plugins {
    id("java")
    id("com.github.johnrengelman.shadow") version "7.1.2"
}

group = "org.playbox"
version = "1.0-SNAPSHOT"

repositories {
    mavenCentral()
    maven("https://jitpack.io")
    maven("https://repo.unnamed.team/repository/unnamed-public/")
}

dependencies {
    implementation("net.kyori:adventure-text-minimessage:4.12.0")
    implementation("com.sendgrid:java-http-client:4.5.1")
    implementation("org.reflections:reflections:0.10.2")
    implementation("team.unnamed:creative-api:0.4.1-SNAPSHOT")
    implementation("team.unnamed:creative-server:0.3.0-SNAPSHOT")
    implementation("com.github.Minestom:Minestom:aebf72de90")

    compileOnly("org.projectlombok:lombok:1.18.26")
    annotationProcessor("org.projectlombok:lombok:1.18.26")

    testCompileOnly("org.projectlombok:lombok:1.18.26")
    testAnnotationProcessor("org.projectlombok:lombok:1.18.26")
}

java {
    toolchain {
        languageVersion.set(JavaLanguageVersion.of(17))
    }
}

tasks {
    jar {
        manifest {
            attributes["Main-Class"] = "org.playbox.Server"
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