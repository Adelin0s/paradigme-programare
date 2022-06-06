import java.io.File

open class Printer(private val fileName: String) {
    private val file = File(fileName)

    fun print(genericData: MutableList<Any>) {
        file.printWriter().use { it ->
            println(genericData)
        }
    }
}

interface Data<TTypeOfData> {
    abstract fun getData(): MutableList<TTypeOfData>
}

open class SimpleData<TTypeOfGeneric>(private val value: TTypeOfGeneric) : Data<TTypeOfGeneric> {

    override fun getData(): MutableList<TTypeOfGeneric> {
        return mutableListOf(value)
    }
}

open class GenericData<TTypeOfData>(private val values: MutableList<TTypeOfData>) : Data<TTypeOfData> {

    override fun getData(): MutableList<TTypeOfData> {
        return values;
    }
}

class Adaptor<TTypeOfData>(private val base_data: Data<TTypeOfData>, private val fileName: String): Data<TTypeOfData>, Printer(fileName) {
    override fun getData(): MutableList<TTypeOfData> {
        return base_data.getData()
    }

    fun writeToFile() {
        File(fileName).printWriter().use {
            it.println(getData())
        }
    }
}

fun main(args: Array<String>) {
    val fileName = "src/main/resources/file.md"

    val simpleDataInt = SimpleData(2)
    val simpleDataDouble = SimpleData(2.2)

    val genericDataInt = GenericData(mutableListOf(1,2,3,4))
    val genericDataDouble = GenericData(mutableListOf(1.1,2,35.6,4.2))

    Adaptor(genericDataDouble, fileName).writeToFile()
}