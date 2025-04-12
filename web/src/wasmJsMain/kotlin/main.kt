package com.reymify

import kotlin.js.*
import kotlinx.browser.*

@JsModule("htmx.org")
external object htmx

fun main() {
    document.body?.apply {
        addEventListener("htmx:beforeSwap") {
        }
        addEventListener("htmx:afterSwap") {
        }
    }
}
