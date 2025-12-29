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

// Ensure Java sources under src/main/kotlin are compiled and on the classpath
sourceSets {
    val main by getting {
        java.srcDirs("src/main/java", "src/main/kotlin")
        kotlin.srcDirs("src/main/kotlin")
    }
    val test by getting {
        java.srcDirs("src/test/java", "src/test/kotlin")
        kotlin.srcDirs("src/test/kotlin")
    }
}

kotlin {
    jvmToolchain(24)
}

tasks.test {
    useJUnitPlatform()
}