plugins {
    kotlin("jvm")
    id("java")
}

group = "ru.vad1mchk"
version = "0.0.0"

repositories {
    mavenCentral()
}

dependencies {
    implementation("com.fasterxml.jackson.core", "jackson-core", "2.13.2")
    implementation("com.fasterxml.jackson.core", "jackson-annotations", "2.13.2")
    implementation("com.fasterxml.jackson.core", "jackson-databind", "2.13.2")
    implementation("com.fasterxml.jackson.dataformat", "jackson-dataformat-csv", "2.13.2")
    implementation("com.fasterxml.jackson.datatype", "jackson-datatype-jsr310", "2.13.2")
    testImplementation("org.junit.jupiter:junit-jupiter-api:5.8.1")
    testRuntimeOnly("org.junit.jupiter:junit-jupiter-engine:5.8.1")
}

tasks.getByName<Test>("test") {
    useJUnitPlatform()
}

tasks.withType<org.gradle.jvm.tasks.Jar> {
    duplicatesStrategy = DuplicatesStrategy.INCLUDE
}