import kotlinx.coroutines.coroutineScope

interface Handler {
    suspend fun handleRequest(messageToBeProcessed : String) = coroutineScope {  }
    fun addNextHandler(handler: Handler)
}