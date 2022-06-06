class Thread1(val x:Array<Int>,val alpha:Int):Thread() {
    public override fun run() {
        for (i in x.indices)
        {
            x[i]=x[i]*alpha;
        }
    }
}