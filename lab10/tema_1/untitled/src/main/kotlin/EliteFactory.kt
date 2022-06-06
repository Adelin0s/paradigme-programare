class EliteFactory : AbstractFactory {
    override fun getHandler(handler : String) : Handler {
        return when(handler) {
            "ceo" -> CEOHandler()
            "executive" -> ExecutiveHandler()
            "manager" -> ManagerHandler()
            else -> throw IllegalArgumentException("No class available for $handler")
        }
    }
}