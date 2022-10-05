import com.github.jengelman.gradle.plugins.shadow.tasks.ShadowJar

plugins {
    id("java")
    id("com.github.johnrengelman.shadow") version "7.1.2"
}

group = "com.example"
version = "1.0-SNAPSHOT"

repositories {
    mavenCentral()
}

dependencies {
    testImplementation("org.junit.jupiter:junit-jupiter-api:5.8.1")
    testRuntimeOnly("org.junit.jupiter:junit-jupiter-engine:5.8.1")
    implementation("io.javalin:javalin:4.6.4")
    implementation("org.slf4j:slf4j-simple:2.0.1")
    implementation("com.fasterxml.jackson.core:jackson-databind:2.13.3")
}

tasks.getByName<Test>("test") {
    useJUnitPlatform()
}

tasks.getByName<ShadowJar>("shadowJar") {
    archiveFileName.set("ecommerce.jar")
    manifest {
        attributes(mapOf(
            "Main-Class" to "com.example.ecommerce.http.Main"
        ))
    }
}
