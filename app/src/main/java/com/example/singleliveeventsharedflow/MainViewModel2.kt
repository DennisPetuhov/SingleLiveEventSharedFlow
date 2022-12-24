package com.example.singleliveeventsharedflow


import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.channels.Channel

import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch

class MainViewModel2 : ViewModel() {

//        private val _action = MutableSharedFlow<String> (replay = 0)
//
//    val action = _action.onEach { _action.resetReplayCache() }
//
//    fun upload(msg: String) {
//        viewModelScope.launch {
//            _action.emit(msg)
//        }
//    }


    private val _action = Channel<String>(0)
    val action get() = _action.receiveAsFlow()

    fun upload(msg: String) {

        if (!_action.isClosedForSend) {
            println("поток открыт")
            viewModelScope.launch {

                _action.send(msg)
                _action.close()
            }
        } else {
            println("поток закрыт")
        }
    }


}