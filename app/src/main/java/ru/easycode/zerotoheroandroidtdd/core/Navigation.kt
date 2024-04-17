package ru.easycode.zerotoheroandroidtdd.core

import ru.easycode.zerotoheroandroidtdd.core.livedata.LiveDataWrapper

interface Navigation {

    interface Read : LiveDataWrapper.Read<Screen>
    interface Update : LiveDataWrapper.Update<Screen>
    interface Mutable : Read, Update

    class Base : LiveDataWrapper.Abstract<Screen>(), Mutable {
        override fun update(screen: Screen) {
            liveData.value = screen
        }
    }
}
