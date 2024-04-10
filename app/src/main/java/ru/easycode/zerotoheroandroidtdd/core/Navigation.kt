package ru.easycode.zerotoheroandroidtdd.core

interface Navigation {
    interface Update {
        fun update(screen: Screen)
    }

    interface Mutable : Update {
        fun checkScreen(expected: Screen)
    }
}
