package ru.easycode.zerotoheroandroidtdd.core

import ru.easycode.zerotoheroandroidtdd.core.viewmodel.LiveDataWrapper

interface Navigation {

    interface Read : LiveDataWrapper.Read<Screen>
    interface Update : LiveDataWrapper.Update<Screen>
    interface Mutable : Read, Update

    class Base : LiveDataWrapper.Abstract<Screen>(), Mutable
}