package com.reymify.components

import kotlinx.html.*

fun FlowContent.hello() {
    h1(classes = "text-3xl font-bold underline text-blue") { +"Hello World!" }
}

fun FlowContent.navigation() {
    nav(classes = "bg-gray-800") {
        div(classes = "mx-auto max-w-7xl px-2 sm:px-6 lg:px-8") {
            div(classes = "relative flex h-16 items-center justify-between") {
                div(classes = "absolute inset-y-0 left-0 flex items-center sm:hidden") {
                    mobileMenuButton()
                } // end
                div(classes = "flex flex-1 items-center justify-center sm:items-stretch sm:justify-start") {
                    div(classes = "flex shrink-0 items-center") {
                        img(src = "https://tailwindcss.com/plus-assets/img/logos/mark.svg?color=indigo&shade=500",
                            classes = "h-8 w-auto") {
                            attributes["alt"] = "Your Company"
                        }
                    }
                    div(classes = "hidden sm:ml-6 sm:block") {
                        div(classes = "flex space-x-4") {
                            // Current: "bg-gray-900 text-white", Default: "text-gray-300 hover:bg-gray-700 hover:text-white"
                            navLinks()
                        }
                    }
                } // large menu items
                div(classes = "absolute inset-y-0 right-0 flex items-center pr-2 sm:static sm:inset-auto sm:ml-6 sm:pr-0") {
                    notificationButton()
                    profileDropdown()
                }
            } // menu container
        }
        mobileMenu()
    }
}
fun FlowContent.mobileMenuButton() {
    // Mobile menu button
    button(type = ButtonType.button) {
        classes = setOf(
            "relative", "inline-flex", "items-center", "justify-center", "rounded-md",
            "p-2", "text-gray-400", "hover:bg-gray-700", "hover:text-white",
            "focus:ring-2", "focus:ring-white", "focus:outline-hidden", "focus:ring-inset"
        )
        attributes["aria-controls"] = "mobile-menu"
        attributes["aria-expanded"] = "false"

        span(classes = "absolute -inset-0.5") {}
        span(classes = "sr-only") {
            +"Open main menu"
        }


        // Icon when menu is closed (Menu open: "hidden", Menu closed: "block")
        svg(classes = "block size-6") {
            attributes["fill"] = "none"
            attributes["viewBox"] = "0 0 24 24"
            attributes["stroke-width"] = "1.5"
            attributes["stroke"] = "currentColor"
            attributes["aria-hidden"] = "true"
            attributes["data-slot"] = "icon"
            unsafe {
                +"""
                     <path stroke-linecap="round" stroke-linejoin="round" d="M3.75 6.75h16.5M3.75 12h16.5m-16.5 5.25h16.5" />
                """.trimIndent()
            }
        } // end svg

        // Icon when menu is open (Menu open: "block", Menu closed: "hidden")
        svg(classes = "hidden size-6") {
            attributes["fill"] = "none"
            attributes["viewBox"] = "0 0 24 24"
            attributes["stroke-width"] = "1.5"
            attributes["stroke"] = "currentColor"
            attributes["aria-hidden"] = "true"
            attributes["data-slot"] = "icon"


            unsafe {
                +"""<path stroke-linecap="round" stroke-linejoin="round" d="M6 18 18 6M6 6l12 12" /> 
              """.trimIndent()
            }
        } // end svg
    } // end button

}
fun FlowContent.notificationButton() {
    // Notification button
    button(type = ButtonType.button, classes = "relative rounded-full bg-gray-800 p-1 text-gray-400 hover:text-white focus:ring-2 focus:ring-white focus:ring-offset-2 focus:ring-offset-gray-800 focus:outline-hidden") {
        span(classes = "absolute -inset-1.5") {}
        span(classes = "sr-only") {
            +"View notifications"
        }
        svg(classes = "size-6") {
            attributes["fill"] = "none"
            attributes["viewBox"] = "0 0 24 24"
            attributes["stroke-width"] = "1.5"
            attributes["stroke"] = "currentColor"
            attributes["aria-hidden"] = "true"
            attributes["data-slot"] = "icon"

            unsafe {
                +"""
                        <path stroke-linecap="round" stroke-linejoin="round" d="M14.857 17.082a23.848 23.848 0 0 0 5.454-1.31A8.967 8.967 0 0 1 18 9.75V9A6 6 0 0 0 6 9v.75a8.967 8.967 0 0 1-2.312 6.022c1.733.64 3.56 1.085 5.455 1.31m5.714 0a24.255 24.255 0 0 1-5.714 0m5.714 0a3 3 0 1 1-5.714 0" />
         
                """.trimIndent()
            }
        }
    }
}
fun FlowContent.profileDropdown() {
    // Profile dropdown
    div(classes = "relative ml-3") {
        div {
            button(type = ButtonType.button, classes = "relative flex rounded-full bg-gray-800 text-sm focus:ring-2 focus:ring-white focus:ring-offset-2 focus:ring-offset-gray-800 focus:outline-hidden") {
                attributes["id"] = "user-menu-button"
                attributes["aria-expanded"] = "false"
                attributes["aria-haspopup"] = "true"

                span(classes = "absolute -inset-1.5") {}
                span(classes = "sr-only") {
                    +"Open user menu"
                }
                img(src = "https://images.unsplash.com/photo-1472099645785-5658abf4ff4e?ixlib=rb-1.2.1&ixid=eyJhcHBfaWQiOjEyMDd9&auto=format&fit=facearea&facepad=2&w=256&h=256&q=80",
                    classes = "size-8 rounded-full") {
                    attributes["alt"] = ""
                }
            }
        }

        // Dropdown menu
        // Comment: Dropdown menu, show/hide based on menu state.
        // Entering: "transition ease-out duration-100"
        //   From: "transform opacity-0 scale-95"
        //   To: "transform opacity-100 scale-100"
        // Leaving: "transition ease-in duration-75"
        //   From: "transform opacity-100 scale-100"
        //   To: "transform opacity-0 scale-95"
        div(classes = "absolute right-0 z-10 mt-2 w-48 origin-top-right rounded-md bg-white py-1 shadow-lg ring-1 ring-black/5 focus:outline-hidden") {
            attributes["role"] = "menu"
            attributes["aria-orientation"] = "vertical"
            attributes["aria-labelledby"] = "user-menu-button"
            attributes["tabindex"] = "-1"

            // Comment: Active: "bg-gray-100 outline-hidden", Not Active: ""
            a(href = "#", classes = "block px-4 py-2 text-sm text-gray-700") {
                attributes["role"] = "menuitem"
                attributes["tabindex"] = "-1"
                attributes["id"] = "user-menu-item-0"
                +"Your Profile"
            }
            a(href = "#", classes = "block px-4 py-2 text-sm text-gray-700") {
                attributes["role"] = "menuitem"
                attributes["tabindex"] = "-1"
                attributes["id"] = "user-menu-item-1"
                +"Settings"
            }
            a(href = "#", classes = "block px-4 py-2 text-sm text-gray-700") {
                attributes["role"] = "menuitem"
                attributes["tabindex"] = "-1"
                attributes["id"] = "user-menu-item-2"
                +"Sign out"
            }
        }
    }
}
fun FlowContent.mobileMenu() {
    div(classes = "sm:hidden") {
        id = "mobile-menu"
        div(classes = "space-y-1 px-2 pt-2 pb-3") {
            navLinks()
        }
    }
}
fun FlowContent.navLinks() {
    a(href = "#", classes = "rounded-md bg-gray-900 px-3 py-2 text-sm font-medium text-white") {
        attributes["aria-current"] = "page"
        +"Dashboard"
    }
    a(href = "#", classes = "rounded-md px-3 py-2 text-sm font-medium text-gray-300 hover:bg-gray-700 hover:text-white") {
        +"Team"
    }
    a(href = "#", classes = "rounded-md px-3 py-2 text-sm font-medium text-gray-300 hover:bg-gray-700 hover:text-white") {
        +"Projects"
    }
    a(href = "#", classes = "rounded-md px-3 py-2 text-sm font-medium text-gray-300 hover:bg-gray-700 hover:text-white") {
        +"Calendar"
    }
}
