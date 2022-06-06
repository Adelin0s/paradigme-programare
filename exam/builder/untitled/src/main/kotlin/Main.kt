class SportCar {
    var parts = listOf<String>()

    fun getParts() {
        for (part in parts)
            println(part)
    }
}

class SuvCar {
    var parts = listOf<String>()

    fun getParts() {
        for (part in parts)
            println(part)
    }
}

interface BuilderCar {
    abstract fun setColor();
    abstract fun setDoors();
    abstract fun setEngine();
}

class BuilderSportCar: BuilderCar {
    private var car = SportCar()

    override fun setColor() {
        car.parts += "Color: Red"
    }

    override fun setDoors() {
        car.parts += "Doors: 2"
    }

    override fun setEngine() {
        car.parts += "Engine: 2.0"
    }

    fun getBuilder(): SportCar {
        return this.car
    }
}

class BuilderSuvCar: BuilderCar {
    private var car = SuvCar()

    override fun setColor() {
        car.parts += "Color: Black"
    }

    override fun setDoors() {
        car.parts += "Doors: 4"
    }

    override fun setEngine() {
        car.parts += "Engine: 3.0"
    }

    fun getBuilder(): SuvCar {
        return this.car
    }
}

fun main(args: Array<String>) {
    val builderSportCar = BuilderSportCar()
    builderSportCar.setColor()
    builderSportCar.setDoors()
    builderSportCar.setEngine()
    val car = builderSportCar.getBuilder()
    car.getParts()
}