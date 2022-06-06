class FactoryProducer {
    fun getFactory(choice : String) : AbstractFactory {
        return when (choice) {
            "EliteFactory" -> EliteFactory()
            "HappyWorkerFactory" -> HappyWorkerFactory()
            else -> throw IllegalArgumentException("No class available for $choice")
        }
    }
}