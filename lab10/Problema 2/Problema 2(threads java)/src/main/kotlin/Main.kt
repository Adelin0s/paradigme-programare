fun main(args: Array<String>){
    val num= arrayOf(5,1,3,9)
    val alpha=4;
    val t1=Thread1(num,alpha)
    val t2=Thread2(num)
    val t3=Thread3(num)
    t1.run()
    t2.run()
    t3.run()
}