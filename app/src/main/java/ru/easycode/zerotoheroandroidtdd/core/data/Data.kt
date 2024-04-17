package ru.easycode.zerotoheroandroidtdd.core.data

interface Data {
    fun areItemsTheSame(other: Data) : Boolean
}