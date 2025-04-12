plugins {
    kotlin("jvm") version "2.1.20" apply false
    kotlin("multiplatform") version "2.1.20" apply false
    id("io.ktor.plugin") version "3.1.2" apply false
}

subprojects {
    repositories {
        mavenCentral()
    }

    group = "com.reymify"
    version = "0.0.1"
}
