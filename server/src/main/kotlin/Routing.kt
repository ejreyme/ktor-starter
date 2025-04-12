package com.reymify

import io.ktor.server.application.*
import io.ktor.server.html.*
import io.ktor.server.http.content.*
import io.ktor.server.response.*
import io.ktor.server.routing.*
import kotlin.random.Random
import kotlinx.html.*

fun Application.configureRouting() {
    routing {
        staticResources("/", "/web")

        get("/") {
            call.respondHtml {
                index {
                    +"Hello World!"
                }
            }
        }
    }
}
