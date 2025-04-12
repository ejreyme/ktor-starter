package com.reymify

import com.reymify.pages.hello
import com.reymify.pages.helloLanding
import io.ktor.server.application.*
import io.ktor.server.html.*
import io.ktor.server.http.content.*
import io.ktor.server.routing.*

fun Application.configureRouting() {
    routing {
        staticResources("/", "/web")

        get("/") {
            call.respondHtml {
                index {
                    hello()
                }
            }
        }

        get("/home") {
            call.respondHtml {
                index {
                    helloLanding()
                }
            }
        }
    }
}
