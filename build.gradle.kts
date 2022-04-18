import org.jetbrains.kotlin.gradle.tasks.KotlinCompile
import org.jetbrains.kotlin.ir.backend.js.compile

plugins {
    kotlin("jvm") version "1.5.10"
    application
    id("org.jetbrains.dokka") version "1.6.10"
}

repositories {
    mavenCentral()
    mavenLocal {
        metadataSources
    }
}

dependencies {
    implementation("$group", name, "$version")
}

tasks.test {
    useJUnitPlatform()
}

tasks.withType<KotlinCompile> {
    kotlinOptions.jvmTarget = "1.8"
}

application {
    mainClass.set("ClientKt")
}

buildscript {
    dependencies {
        classpath("org.jetbrains.dokka:dokka-gradle-plugin:1.6.10")
    }
}

dependencies {
    api(
        "$group", name, "$version"
    )
}

apply(plugin = "org.jetbrains.dokka")