class HappyWorkerFactory : AbstractFactory {
    override fun getHandler(handle : String) : Handler {
        return when (handle) {
            "happy" -> HappyWorkerHandler()
            else -> throw IllegalArgumentException("No class name for $handle")
        }
    }
}