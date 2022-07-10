plugins {
    kotlin("jvm") version "1.6.10"
    id("com.adarshr.test-logger") version "3.1.0"
    id("io.specgen.kotlin.gradle")
}

group = "io.specgen"
version = "1.0-SNAPSHOT"

repositories {
    mavenCentral()
}

dependencies {
    implementation(kotlin("stdlib"))
    implementation("com.squareup.okhttp3:okhttp:4.9.3")
    implementation("com.fasterxml.jackson.module:jackson-module-kotlin:2.13.3")
    implementation("com.fasterxml.jackson.datatype:jackson-datatype-jsr310:2.13.3")
    implementation("org.apache.logging.log4j:log4j-slf4j-impl:2.17.2")

    testImplementation(kotlin("test"))
    testImplementation("org.assertj:assertj-core:3.23.1")
}

specgen {
    clientKotlin {
        jsonlib.set("jackson")
        packageName.set("test_client")
        specFile.set(file("../spec.yaml"))
    }
}

tasks.test {
    useJUnitPlatform()
}