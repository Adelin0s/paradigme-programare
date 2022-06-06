fun main(args: Array<String>)
{
    val t1=Thread1("Fisier1.txt","Textul pt fisierul 1")
    val t2=Thread1("Fisier2.txt","Textul pt fisierul 2")
    val t3=Thread1("Fisier3.txt","Textul pt fisierul 3")
    val t4=Thread1("Fisier4.txt","Textul pt fisierul 4")
    t1.run()
    t2.run()
    t3.run()
    t4.run()
}