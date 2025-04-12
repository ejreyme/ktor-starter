val kotlin_version: String by project
val kotlinx_browser_version: String by project
val kotlinx_html_version: String by project
val ktor_version: String by project
val logback_version: String by project

plugins {
    kotlin("multiplatform") version "2.1.20"
}

kotlin {
    @OptIn(org.jetbrains.kotlin.gradle.ExperimentalWasmDsl::class)
    wasmJs {
        browser {
            commonWebpackConfig {
                outputFileName = "web.js"
            }
        }
        binaries.executable()
    }

    tasks.register("tailwindcssMinify", Exec::class) {
        commandLine("./tailwindcss_minify.sh")
    }

    tasks.register("tailwindcssWatcher", Exec::class) {
        commandLine("./tailwindcss_watcher.sh")
    }

    sourceSets {
        wasmJsMain.dependencies {
            implementation(npm("htmx.org", "2.0.3"))
            implementation("org.jetbrains.kotlinx:kotlinx-browser:$kotlinx_browser_version")
        }
    }
}
