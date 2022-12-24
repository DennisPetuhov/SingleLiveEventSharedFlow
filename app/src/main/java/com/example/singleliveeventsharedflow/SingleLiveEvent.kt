package com.example.singleliveeventsharedflow


import android.util.Log
import androidx.annotation.MainThread

import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import java.util.concurrent.atomic.AtomicBoolean


class SingleLiveEvent<T> : MutableLiveData<T>() {
    private val mPending = AtomicBoolean(true)

    @MainThread

    override fun observe(owner: LifecycleOwner, observer: Observer<in T>) {
        if (hasActiveObservers()) {
            Log.w(TAG, "Multiple observers registered but only one will be notified of changes.")
        }
        // Observe the internal MutableLiveData
        super.observe(owner) {

            if (mPending.compareAndSet(true, false)
            ) {
                observer.onChanged(it)
            } else {
                println("$mPending ELSE BLOCK")
            }
        }
    }


    @MainThread
    override fun setValue(t: T?) {

        super.setValue(t)
    }

    /**
     * Used for cases where T is Void, to make calls cleaner.
     */
    @MainThread
    fun call() {
        setValue(null)
    }

    companion object {
        private val TAG = "SingleLiveEvent"
    }
}