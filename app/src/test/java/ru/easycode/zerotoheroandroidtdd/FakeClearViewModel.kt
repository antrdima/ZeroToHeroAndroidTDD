package ru.easycode.zerotoheroandroidtdd

import androidx.lifecycle.ViewModel
import org.junit.Assert
import ru.easycode.zerotoheroandroidtdd.core.viewmodel.ClearViewModel

interface FakeClearViewModel : ClearViewModel {

    companion object {
        const val CLEAR = "clearCall"
    }

    fun checkClearCalled(expected: Class<out ViewModel>)

    class Base(private val order: Order = Order()) : FakeClearViewModel {

        private lateinit var actual: Class<out ViewModel>

        override fun checkClearCalled(expected: Class<out ViewModel>) {
            Assert.assertEquals(expected, actual)
        }

        override fun clear(clazz: Class<out ViewModel>) {
            order.add(CLEAR)
            actual = clazz
        }
    }
}
