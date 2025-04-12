package com.reymify


import kotlinx.html.*

fun HTML.index(block: FlowContent.() -> Unit = {}) {
    head {
        title("HTMX Example")
        meta { charset = "UTF-8" }
        meta { httpEquiv = "Content-Type"; content = "text/html; charset=UTF-8" }
        meta { name = "viewport"; content = "width=device-width, initial-scale=1.0" }
        meta { content = "Ktor Dev" ; name = "author"}
        meta { content = "Ktor Starter" ; name = "description"}
        script(src = "/web.js") {}
        link(rel = "stylesheet", href = "/output.css")
    }
    body {
        block()
    }
}
