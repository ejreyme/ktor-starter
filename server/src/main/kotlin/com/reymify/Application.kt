package com.reymify

import com.reymify.components.*
import io.ktor.http.*
import io.ktor.server.application.*
import io.ktor.server.html.*
import io.ktor.server.http.content.*
import io.ktor.server.routing.*
import kotlinx.html.*

fun main(args: Array<String>) = io.ktor.server.netty.EngineMain.main(args)

fun Application.module() {
    routing {
        staticResources("/", "/web")

        get("/") {
            call.render { hello() }
        }
    }
}

suspend fun ApplicationCall.render(status: HttpStatusCode = HttpStatusCode.OK, component: FlowContent.() -> Unit) {
    respondHtmlTemplate(LayoutTemplate(), status) {
        content { component() }
    }
}

class LayoutTemplate : Template<HTML> {
    val content = Placeholder<FlowContent>()
    override fun HTML.apply() {
        head {
            title("HTMX Example")
            meta { charset = "UTF-8" }
            meta { httpEquiv = "Content-Type"; content = "text/html; charset=UTF-8" }
            meta { name = "viewport"; content = "width=device-width, initial-scale=1.0" }
            meta { content = "Ktor Dev" ; name = "author"}
            meta { content = "Ktor Starter" ; name = "description"}
            script(src = "/web.js") {}
            link(rel = "stylesheet", href = "/output.css")
            link(rel = "stylesheet", href= "https://rsms.me/inter/inter.css")
        }
        body {
            insert(content)
        }
    }
}