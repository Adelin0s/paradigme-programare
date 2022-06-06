import kotlinx.*
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking

fun main(args: Array<String>) = runBlocking {
    val factoryProducer = FactoryProducer()
    val eliteFactory = factoryProducer.getFactory("EliteFactory")
    val happyWorkerFactory = factoryProducer.getFactory("HappyWorkerFactory")

    val ceo = eliteFactory.getHandler("ceo")
    val executive=eliteFactory.getHandler("executive")
    val manager=eliteFactory.getHandler("manager")
    val happy_worker=happyWorkerFactory.getHandler("happy")

    ceo.addNextHandler(executive);
    executive.addNextHandler(manager)
    manager.addNextHandler(happy_worker)

    ceo.handleRequest("<1><Mesajul_1!>")
    ceo.handleRequest("<2><Mesajul_2!>")
    ceo.handleRequest("<3><Mesajul_3!>")
    ceo.handleRequest("<4><Mesajul_4!>")
}