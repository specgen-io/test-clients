plugins {
    kotlin("jvm") version "1.6.10"
    id("com.adarshr.test-logger") version "3.1.0"
    id("io.specgen.gradle")
}

group = "io.specgen"
version = "1.0-SNAPSHOT"

repositories {
    mavenCentral()
}

dependencies {
    implementation(kotlin("stdlib"))
    implementation("com.squareup.moshi:moshi:1.13.0")
    implementation("com.squareup.moshi:moshi-adapters:1.13.0")
    implementation("com.squareup.moshi:moshi-kotlin:1.13.0")
    implementation("com.squareup.okhttp3:okhttp:4.9.3")
    implementation("org.apache.logging.log4j:log4j-slf4j-impl:2.17.0")

    testImplementation(kotlin("test"))
    testImplementation("org.assertj:assertj-core:3.21.0")
}

specgen {
    clientKotlin {
        jsonlib.set("moshi")
        packageName.set("test_client")
        specFile.set(file("../spec.yaml"))
    }
}

tasks.test {
    useJUnitPlatform()
}