package ru.easycode.zerotoheroandroidtdd.core

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import ru.easycode.zerotoheroandroidtdd.create.CreateFragment
import ru.easycode.zerotoheroandroidtdd.list.ListFragment

interface Screen {
    fun show(supportFragmentManager: FragmentManager, containerId: Int)

    object ListScreen : Replace(ListFragment::class.java)
    object CreateScreen : Replace(CreateFragment::class.java)
    object Pop : Screen {
        override fun show(supportFragmentManager: FragmentManager, containerId: Int) {
            supportFragmentManager.popBackStack()
        }
    }

    abstract class Replace(private val fragmentClass: Class<out Fragment>) : Screen {
        override fun show(supportFragmentManager: FragmentManager, containerId: Int) {
            supportFragmentManager.beginTransaction()
                .replace(containerId, fragmentClass.getDeclaredConstructor().newInstance())
                .addToBackStack(fragmentClass.name)
                .commit()
        }
    }

    abstract class Add(private val fragmentClass: Class<out Fragment>) : Screen {
        override fun show(supportFragmentManager: FragmentManager, containerId: Int) {
            supportFragmentManager.beginTransaction()
                .add(containerId, fragmentClass.getDeclaredConstructor().newInstance())
                .addToBackStack(fragmentClass.name)
                .commit()
        }
    }
}
