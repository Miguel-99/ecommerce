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
    implementation("io.javalin:javalin:5.2.0")
    implementation("org.slf4j:slf4j-simple:2.0.5")
    implementation("com.fasterxml.jackson.core:jackson-databind:2.14.0")
    implementation("org.junit.jupiter:junit-jupiter-engine:5.9.0")
    testRuntimeOnly("org.junit.jupiter:junit-jupiter-engine:5.9.0")
    testImplementation("org.junit.jupiter:junit-jupiter-api:5.9.0")
    testImplementation("io.rest-assured:rest-assured:5.3.0")
    testImplementation("org.mockito:mockito-core:4.8.0")
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
