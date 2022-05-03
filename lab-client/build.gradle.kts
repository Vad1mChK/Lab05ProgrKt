import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

plugins {
    kotlin("jvm")
    application
    id("org.jetbrains.dokka")
}

repositories {
    mavenCentral()
    flatDir {
        dirs("../lab-common")
    }
}

tasks.withType<KotlinCompile> {
    kotlinOptions.jvmTarget = "1.8"
}

application {
    mainClass.set("ClientKt")
}

dependencies {
    implementation(project(":lab-common"))
}

apply(plugin = "org.jetbrains.dokka")

tasks.withType<Jar> {
    manifest {
        attributes["Main-Class"] = "ru.vad1mchk.progr.lab05.client.Client"
    }
}