package com.testproject.core.library.live_data

import androidx.fragment.app.Fragment
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import androidx.lifecycle.distinctUntilChanged
import androidx.lifecycle.map
import kotlin.properties.ReadWriteProperty
import kotlin.reflect.KProperty

/**
 * @author Pawlo Nikitin
 */
inline fun <reified T, LD : LiveData<T>> Fragment.observe(
    liveData: LD,
    crossinline block: (T) -> Unit
) {
    liveData.observe(viewLifecycleOwner, Observer<T> { block(it) })
}

inline fun <X, Y> LiveData<X>.mapDistinct(crossinline transform: (X) -> Y): LiveData<Y> {
    return map(transform).distinctUntilChanged()
}

inline fun <X, Y> LiveData<X>.mapNotDistinct(crossinline transform: (X) -> Y): LiveData<Y> {
    return map(transform)
}

fun <T : Any> LiveData<T>.requireValue(): T = checkNotNull(value)

fun <T> MutableLiveData<T>.onNext(next: T) {
    this.value = next
}

fun <T : Any> MutableLiveData<T>.delegate(): ReadWriteProperty<Any, T> {
    return object : ReadWriteProperty<Any, T> {
        override fun setValue(thisRef: Any, property: KProperty<*>, value: T) = onNext(value)
        override fun getValue(thisRef: Any, property: KProperty<*>): T = requireValue()
    }
}
