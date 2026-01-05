plugins {
    kotlin("jvm") version "2.2.21"
}

group = "com.epam.automation"
version = "1.0-SNAPSHOT"

repositories {
    mavenCentral()
}

dependencies {
    implementation("com.microsoft.playwright:playwright:1.43.0")
    implementation("org.assertj:assertj-core:3.25.3")
    implementation("org.junit.jupiter:junit-jupiter:5.10.1")

    testImplementation(kotlin("test"))
    testImplementation("org.junit.jupiter:junit-jupiter:5.10.1")
    testImplementation("org.assertj:assertj-core:3.25.3")
}

sourceSets {
    val main by getting {
        kotlin.srcDirs("src/main/kotlin")
    }
    val test by getting {
        kotlin.srcDirs("src/test/kotlin")
    }
}

kotlin {
    jvmToolchain(24)
}

tasks.test {
    useJUnitPlatform()
}