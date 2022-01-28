plugins {
    kotlin("jvm") version "1.6.10"
    id("io.specgen.gradle")
}

group = "io.specgen"
version = "1.0-SNAPSHOT"

repositories {
    mavenCentral()
}

dependencies {
    implementation(kotlin("stdlib"))
    implementation("com.squareup.okhttp3:okhttp:4.9.3")
    implementation("com.fasterxml.jackson.module:jackson-module-kotlin:2.13.1")
    implementation("com.fasterxml.jackson.datatype:jackson-datatype-jsr310:2.13.1")
    implementation("org.apache.logging.log4j:log4j-slf4j-impl:2.17.0")

    testImplementation(kotlin("test"))
    testImplementation("org.assertj:assertj-core:3.21.0")
}

specgen {
    clientKotlin {
        packageName.set("test_client")
        specFile.set(file("../spec.yaml"))
    }
}

tasks.test {
    useJUnitPlatform()
}