import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class ManagerHandler(var next1:Handler?=null) : Handler {
    override suspend fun handleRequest(messageToBeProcessed: String) : Unit = coroutineScope {
        val priority = "\\d".toRegex().find(messageToBeProcessed)

        val message = messageToBeProcessed.slice(4..messageToBeProcessed.length - 2)

        if (priority?.value?.toInt() == 3) {
            launch {
                println("Sunt Manager si execut mesajul: $message")
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