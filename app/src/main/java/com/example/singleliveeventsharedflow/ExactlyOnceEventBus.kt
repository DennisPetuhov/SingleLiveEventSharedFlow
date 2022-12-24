//package com.example.singleliveeventsharedflow
//
//import kotlinx.coroutines.sync.Semaphore
//
//class ExactlyOnceEventBus<T> {
//    private val buffer = ArrayDeque<T>()
//    private val semaphore = Semaphore(Int.MAX_VALUE, Int.MAX_VALUE)
//
//    fun send(event: T) {
//        synchronized(buffer) { buffer.add(event) }
//        semaphore.release()
//    }
//
//    suspend fun receive(): T {
//        semaphore.acquire()
//        return synchronized(buffer) { buffer.removeFirst() }
//    }
//}
//
//
//
//
//Another solution is to introduce a dedicated primitive (not a channel!) that can handle these kinds
//of "exactly once events" despite cancellation. The idea is that the receiver has to get a permit to
//receive an event first and, if it is not cancelled, retrieve the event from the underlying buffer.
//This idea can be directly implemented on top of a synchronized array deque and a semaphore (credit goes to @ndkoval).
//You can write a trivial implementation of such ExactlyOnceEventBus:
//
//import kotlinx.coroutines.sync.*
//
//class ExactlyOnceEventBus<T> {
//    private val buffer = ArrayDeque<T>()
//    private val semaphore = Semaphore(Int.MAX_VALUE, Int.MAX_VALUE)
//
//    fun send(event: T) {
//        synchronized(buffer) { buffer.add(event) }
//        semaphore.release()
//    }
//
//    suspend fun receive(): T {
//        semaphore.acquire()
//        return synchronized(buffer) { buffer.removeFirst() }
//    }
//}
//With this implementation, eventBus.send(event) can be called from any thread, while eventBus.receive()
//will consume an event only when it was not cancelled.
//
//The implementation is trivial. The challenge is that it is still something that many developers might
//have to repeat over and over, so it might make sense to provide some ready-to-use primitive with it.
//Since we expected that people would like to use it as a Flow it looks like providing some kind of a special
//ExactlyOnceEventFlow (name TBD) in kotlinx.coroutines might be a good idea.
//
