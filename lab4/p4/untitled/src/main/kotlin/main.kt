class Functor(val list: MutableMap<Int,String>) {
    fun map(function: (String) -> String): Functor {
        val result = mutableMapOf<Int,String>()
        for(item in list) {
            result[item.key]=(function(item.value))
        }
        return Functor(result)
    }
}

fun String.toPascalCase(): String {
    val components = this.split(" ")
    var result: String = ""
    for(component in components) {
        result += component.capitalize()
    }
    return result
}

fun main(args: Array<String>) {
    val m_elements = mutableMapOf<Int, String>()

    m_elements[0] = "pere"
    m_elements[1] = "harbuz"
    m_elements[2] = "gutui"
    m_elements[3] = "ana are mere"

    val toPascalCase = {str: String -> str.split("").map{it.capitalize()}.joinToString(separator="")}
    println(m_elements)

    println(Functor( m_elements ))
}