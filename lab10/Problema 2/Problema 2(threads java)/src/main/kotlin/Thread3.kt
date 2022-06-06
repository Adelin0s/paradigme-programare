class Thread3(val x:Array<Int>):Thread() {
    public override fun run() {
        for (i in x)
            println(i)
    }
}