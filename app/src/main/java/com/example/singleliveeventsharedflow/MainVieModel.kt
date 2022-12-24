package com.example.singleliveeventsharedflow

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope

import kotlinx.coroutines.launch


class MainVieModel : ViewModel() {

    private val uploadAuction: SingleLiveEvent<String> = SingleLiveEvent()
    fun getUploadAuction(): SingleLiveEvent<String> {
        return uploadAuction
    }

    fun onAuctionToUpload(msg: String) {
        uploadAuction.setValue(msg)
    }

        fun openDetails(msg:String) {

        viewModelScope.launch {
            uploadAuction.postValue(msg)
        }
    }



////    private val _action = MutableSharedFlow<String>(replay = 0)
//    private val _action = Channel<String>(Channel.BUFFERED)
//    val action  = _action.receiveAsFlow().onEach { event -> processEventInUI(event) }
//        .launchIn(Dispatchers.Main.immediate)
//
//    fun openDetails(msg:String) {
//
//        viewModelScope.launch {
//            _action.send(msg)
//        }
//    }



}