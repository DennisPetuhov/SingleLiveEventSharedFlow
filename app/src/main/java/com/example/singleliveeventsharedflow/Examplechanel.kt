package com.example.singleliveeventsharedflow


import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.invoke
import kotlinx.coroutines.launch

class Examplechanel {
}

suspend fun main() = coroutineScope {

    val channel = Channel<Resource>()
    launch {
        for (n in 1..5) {
            // отправляем данные через канал
            channel.send(Resource.Error("error"))
        }
    }

    // получаем данные из канала


    var x = channel.receive()
//    channel.close()
    println(x)

//    channel.receiveAsFlow()
//        .onEach { println(it) }
//        .launchIn(Dispatchers.Main.immediate()) // immediate is the key here!
//         channel.receiveAsFlow()
//             .launchIn(Dispatchers.Main)
//            .o{
//                println(it)
//             }
//            .launchIn(Dispatchers.Main.immediate) // immediate is the key here!
//        println(number)

    println("End")
}

fun myChanel(chanel: Channel<Resource>) {
    chanel.receiveAsFlow()
        .onEach { println(it) }.flowOn(Dispatchers.Main.immediate)
//        .launchIn(Dispatchers.Main)

}


sealed class Resource {
    data class Error(val message: String) : Resource()
    data class Success(val data: String) : Resource()
    data class Loading(val loadingMsg: String) : Resource()
}