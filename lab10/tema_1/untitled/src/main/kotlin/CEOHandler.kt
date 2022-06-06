import kotlinx.coroutines.delay
import kotlinx.coroutines.*

class CEOHandler(var next1:Handler?=null) : Handler {
    override suspend fun handleRequest(messageToBeProcessed: String): Unit = coroutineScope {
        // <Priority><messageToBeProcessed>
        val priority = "\\d".toRegex().find(messageToBeProcessed)

        val message = messageToBeProcessed.slice(4..messageToBeProcessed.length - 2)

        if (priority?.value?.toInt() == 1) {
            launch {
                println("Sunt CEO si execut mesajul: $message")
                delay(5000L)
            }
        }
        else {
            launch {
                next1?.handleRequest(messageToBeProcessed)
            }
        }
    }

    override fun addNextHandler(handler: Handler) {
        next1 = handler
    }
}