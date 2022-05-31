import org.jetbrains.dokka.gradle.DokkaTask
import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

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

buildscript {
    dependencies {
        classpath("org.jetbrains.dokka:dokka-gradle-plugin:1.6.10")
    }
}

tasks.dokkaHtmlMultiModule.configure {
    outputDirectory.set(buildDir.resolve("documentation"))
    includes.from("README.md", "module.md")
}

tasks.withType<DokkaTask>().configureEach {

}

subprojects {
    plugins.apply("org.jetbrains.dokka")
}


dependencies {
    api(
        "$group", name, "$version"
    )
}

apply(plugin = "org.jetbrains.dokka")