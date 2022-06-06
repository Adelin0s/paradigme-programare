import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class ExecutiveHandler(var next1:Handler?=null) : Handler {
    override suspend fun handleRequest(messageToBeProcessed: String): Unit = coroutineScope {
        val priority = "\\d".toRegex().find(messageToBeProcessed)

        val message = messageToBeProcessed.slice(4..messageToBeProcessed.length - 2)

        if (priority?.value?.toInt() == 2) {
            launch {
                println("Sunt Executive si execut mesajul: $message")
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