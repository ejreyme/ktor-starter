val kotlinVersion: String by project
val kotlinxHtmlVersion: String by project
val logbackVersion: String by project
val kotlinCssVersion: String by project

plugins {
    kotlin("jvm") version "2.2.20"
    id("io.ktor.plugin") version "3.3.0"
    kotlin("plugin.serialization").version("2.2.10")
    id("org.jlleitschuh.gradle.ktlint") version "13.1.0"
}

group = "com.sealedstack"
version = "0.0.1"

application {
    mainClass = "io.ktor.server.netty.EngineMain"

    val isDevelopment: Boolean = project.ext.has("development")
    applicationDefaultJvmArgs = listOf("-Dio.ktor.development=$isDevelopment")
}

kotlin {
    jvmToolchain(24)
}

tasks.register<GradleBuild>("cleanBuildPublish") {
    tasks = listOf("clean", "build", "buildFatJar", "buildImage", "publishImageToLocalRegistry")
}

ktor {
    docker {
        localImageName.set("ktor-starter")
        imageTag.set("latest")
    }
}

dependencies {
    // core
    implementation("io.ktor:ktor-server-core:$kotlinVersion")
    implementation("io.ktor:ktor-server-netty:$kotlinVersion")
    implementation("ch.qos.logback:logback-classic:$logbackVersion")
    // html/css dsl
    implementation("io.ktor:ktor-server-html-builder:$kotlinVersion")
    implementation("org.jetbrains.kotlinx:kotlinx-html:$kotlinxHtmlVersion")
    implementation("org.jetbrains.kotlin-wrappers:kotlin-css-jvm:$kotlinCssVersion")
    // test
    testImplementation("io.ktor:ktor-server-test-host:$kotlinVersion")
    testImplementation("org.jetbrains.kotlin:kotlin-test-junit:$kotlinVersion")
}
