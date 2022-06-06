import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class HappyWorkerHandler(var next1:Handler?=null) : Handler {
    override suspend fun handleRequest(messageToBeProcessed: String) : Unit = coroutineScope {
        val priority = "\\d".toRegex().find(messageToBeProcessed)

        val message = messageToBeProcessed.slice(4..messageToBeProcessed.length - 2)

        if (priority?.value?.toInt() == 4) {
            launch {
                println("Sunt HappyWorker si execut mesajul: $message")
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