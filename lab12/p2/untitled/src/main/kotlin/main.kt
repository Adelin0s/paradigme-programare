import java.io.File

fun <T> translate(offset:Int, str:T, fct: (Int, T) -> T) = fct(offset,str)

fun caesarEncode( text: String, n: Int): String
{
    var result = String()
    for (i in text.indices) {
        if (Character.isUpperCase(text[i])) {
            val ch = ((text[i].code + n - 65) % 26 + 65).toChar()
            result += ch
        } else if(Character.isLowerCase(text[i])){
            val ch = ((text[i].code + n - 97) % 26 + 97).toChar()
            result += ch
        }
    }
    return result
}

fun main(args: Array<String>) {
    val n = 5

    val fileText = File("src/main/kotlin/input.md").readLines().toString()

    var mess = fileText.split(" ").filter { it.length in 5..6 }

    mess.forEach{println(it + "->" + caesarEncode(it, n))}
}