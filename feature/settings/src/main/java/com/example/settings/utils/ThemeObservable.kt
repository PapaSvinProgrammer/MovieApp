package com.example.settings.utils

object ThemeObservable {
    private val observers = mutableListOf<ThemeObserver>()

    fun subscribe(observer: ThemeObserver) {
        observers.add(observer)
    }

    fun unsubscribe(observer: ThemeObserver) {
        observers.remove(observer)
    }

    fun notify(theme: AppTheme) {
        observers.forEach {
            it.onThemeChanged(theme)
        }
    }
}