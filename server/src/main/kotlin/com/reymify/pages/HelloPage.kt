package com.reymify.pages

import kotlinx.html.*


fun FlowContent.hello() {
    h1(classes = "text-3xl font-bold underline text-blue") { +"Hello World!" }
}

fun FlowContent.helloLanding() {
    div(classes = "min-h-screen bg-gradient-to-b from-blue-50 to-white flex flex-col") {
        // Header
        header(classes = "container mx-auto py-6 px-4") {
            div(classes = "flex justify-between items-center") {
                div(classes = "flex items-center") {
                    img(classes = "h-10 w-auto", src = "/images/logo.svg", alt = "Logo")
                    span(classes = "ml-3 text-xl font-semibold text-gray-800") { +"Our Platform" }
                }
                nav(classes = "hidden md:flex space-x-8") {
                    a(href = "#", classes = "text-gray-600 hover:text-blue-600") { +"Home" }
                    a(href = "#features", classes = "text-gray-600 hover:text-blue-600") { +"Features" }
                    a(href = "#about", classes = "text-gray-600 hover:text-blue-600") { +"About" }
                    a(href = "#contact", classes = "text-gray-600 hover:text-blue-600") { +"Contact" }
                }
                button(classes = "md:hidden") {
                    type = ButtonType.button
                    attributes["aria-label"] = "Toggle menu"
                    span(classes = "block h-0.5 w-6 bg-gray-800 mb-1") {}
                    span(classes = "block h-0.5 w-6 bg-gray-800 mb-1") {}
                    span(classes = "block h-0.5 w-6 bg-gray-800") {}
                }
            }
        }

        // Hero section
        main(classes = "flex-grow") {
            section(classes = "container mx-auto px-4 py-16 md:py-24 flex flex-col items-center text-center") {
                div(classes = "max-w-3xl") {
                    // Using the original hello function
                    hello()

                    p(classes = "mt-6 text-xl text-gray-600") {
                        +"Welcome to our platform! We're excited to have you here. Discover how we can help you achieve your goals."
                    }

                    div(classes = "mt-8 flex flex-col sm:flex-row gap-4 justify-center") {
                        a(href = "#get-started", classes = "px-6 py-3 bg-blue-600 text-white rounded-lg hover:bg-blue-700 transition-colors shadow-md") {
                            +"Get Started"
                        }
                        button(classes = "px-6 py-3 border border-gray-300 text-gray-700 rounded-lg hover:bg-gray-50 transition-colors") {
                            attributes["hx-get"] = "/learn-more"
                            attributes["hx-target"] = "#info-section"
                            attributes["hx-swap"] = "innerHTML"
                            +"Learn More"
                        }
                    }
                }
            }

            // Features section
            section(classes = "container mx-auto px-4 py-16 bg-white") {
                id = "features"
                h2(classes = "text-2xl md:text-3xl font-bold text-center mb-12") {
                    +"Key Features"
                }

                div(classes = "grid grid-cols-1 md:grid-cols-3 gap-8") {
                    featureCard(
                        icon = "🚀",
                        title = "Lightning Fast",
                        description = "Our platform delivers exceptional performance to keep you productive."
                    )
                    featureCard(
                        icon = "🔒",
                        title = "Secure by Design",
                        description = "Your data is protected with industry-leading security protocols."
                    )
                    featureCard(
                        icon = "🔄",
                        title = "Seamless Integration",
                        description = "Easily connect with your favorite tools and services."
                    )
                }
            }

            // Info section that can be updated by HTMX
            div(classes = "container mx-auto px-4 py-8") {
                id = "info-section"
                // Content will be loaded via HTMX
            }
        }

        // Footer
        footer(classes = "bg-gray-800 text-white py-12") {
            div(classes = "container mx-auto px-4") {
                div(classes = "grid grid-cols-1 md:grid-cols-4 gap-8") {
                    div {
                        h3(classes = "text-lg font-semibold mb-4") { +"Our Platform" }
                        p(classes = "text-gray-400") {
                            +"Empowering users with innovative solutions since 2023."
                        }
                    }
                    div {
                        h3(classes = "text-lg font-semibold mb-4") { +"Quick Links" }
                        ul(classes = "space-y-2") {
                            li { a(href = "#", classes = "text-gray-400 hover:text-white") { +"Home" } }
                            li { a(href = "#features", classes = "text-gray-400 hover:text-white") { +"Features" } }
                            li { a(href = "#about", classes = "text-gray-400 hover:text-white") { +"About Us" } }
                            li { a(href = "#contact", classes = "text-gray-400 hover:text-white") { +"Contact" } }
                        }
                    }
                    div {
                        h3(classes = "text-lg font-semibold mb-4") { +"Contact" }
                        address(classes = "text-gray-400 not-italic") {
                            p { +"123 Tech Street" }
                            p { +"Innovation City, IC 10101" }
                            p { +"info@ourplatform.com" }
                            p { +"+1 (555) 123-4567" }
                        }
                    }
                    div {
                        h3(classes = "text-lg font-semibold mb-4") { +"Follow Us" }
                        div(classes = "flex space-x-4") {
                            a(href = "#", classes = "text-gray-400 hover:text-white") { +"Twitter" }
                            a(href = "#", classes = "text-gray-400 hover:text-white") { +"LinkedIn" }
                            a(href = "#", classes = "text-gray-400 hover:text-white") { +"GitHub" }
                        }
                    }
                }
                div(classes = "mt-8 pt-8 border-t border-gray-700 text-gray-400 text-center") {
                    +"© ${java.time.Year.now().value} Our Platform. All rights reserved."
                }
            }
        }
    }
}

/**
 * Helper function to create feature cards
 */
private fun FlowContent.featureCard(icon: String, title: String, description: String) {
    div(classes = "bg-gray-50 p-6 rounded-lg shadow-sm hover:shadow-md transition-shadow") {
        div(classes = "text-3xl mb-4") { +icon }
        h3(classes = "text-xl font-semibold mb-2") { +title }
        p(classes = "text-gray-600") { +description }
    }
}
