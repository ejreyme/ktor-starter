package com.sealedstack

import io.ktor.http.*
import io.ktor.server.application.*
import io.ktor.server.html.*
import io.ktor.server.http.content.*
import io.ktor.server.response.*
import io.ktor.server.routing.*
import kotlinx.css.CssBuilder
import kotlinx.css.opacity
import kotlinx.css.properties.Timing
import kotlinx.css.properties.Transition
import kotlinx.css.properties.ms
import kotlinx.html.*

fun main(args: Array<String>) =
    io.ktor.server.netty.EngineMain
        .main(args)

fun Application.module() {
    routing {
        staticResources("/", "static")

        get("/styles.css") {
            call.respondCss {
                // htmx
                rule(".htmx-indicator") {
                    opacity = 0.0
                    Transition(property = "opacity", duration = 200.ms, timing = Timing.easeIn)
                }
                rule(".htmx-request .htmx-indicator") {
                    opacity = 1.0
                }
                rule(".htmx-request.htmx-indicator") {
                    opacity = 1.0
                }
            }
        }

        get("/") {
            call.renderPage { indexPage() }
        }

        get("/hello") {
            if (call.serveHtml()) {
                call.renderComponent { helloWorld() }
            } else {
                call.respondText("Hello World!")
            }
        }
    }
}

suspend fun ApplicationCall.renderPage(
    status: HttpStatusCode = HttpStatusCode.OK,
    component: FlowContent.() -> Unit,
) {
    respondHtmlTemplate(LayoutTemplate(), status) {
        content { component() }
    }
}

suspend fun ApplicationCall.renderComponent(component: FlowContent.() -> Unit) {
    respondHtml(status = HttpStatusCode.OK) { body { component() } }
}

suspend inline fun ApplicationCall.respondCss(builder: CssBuilder.() -> Unit) {
    this.respondText(CssBuilder().apply(builder).toString(), ContentType.Text.CSS)
}

fun ApplicationCall.serveHtml(): Boolean {
    val accept = request.headers["Accept"] ?: "text/html"
    val htmx = request.headers["HX-Request"] ?: "false"
    return htmx == "true" || accept == "text/html"
}

class LayoutTemplate : Template<HTML> {
    val content = Placeholder<FlowContent>()

    override fun HTML.apply() {
        head {
            lang = "en"
            attributes["data-bs-theme"] = "dark"
            title("HTMX Example")
            meta { charset = "UTF-8" }
            meta {
                httpEquiv = "Content-Type"
                content = "text/html; charset=UTF-8"
            }
            meta {
                name = "viewport"
                content = "width=device-width, initial-scale=1.0"
            }
            meta {
                content = "Ktor Dev"
                name = "author"
            }
            meta {
                content = "Ktor Starter"
                name = "description"
            }
            loadLinks()
        }
        body {
            insert(content)
            loadScripts()
        }
    }
}

fun HEAD.loadLinks() {
    link(rel = "stylesheet", href = "css/bootstrap.min.css", type = "text/css")
    link(rel = "stylesheet", href = "css/all.min.css", type = "text/css")
    link(rel = "stylesheet", href = "/styles.css", type = "text/css")
    script(src = "js/htmx.min.js") {}
}

fun FlowContent.loadScripts() {
    script(src = "js/bootstrap.bundle.min.js") {}
    script(src = "js/Chart.min.js") {}
}

fun FlowContent.indexPage() {
    div(classes = "container") {
        attributes["hx-get"] = "/hello"
        attributes["hx-target"] = "#response"
        attributes["hx-swap"] = "innerHTML"
        attributes["hx-trigger"] = "load"
        id = "response"
    }
}

fun FlowContent.helloWorld() {
    h1 { +"Hello Ktor Starter" }
}
