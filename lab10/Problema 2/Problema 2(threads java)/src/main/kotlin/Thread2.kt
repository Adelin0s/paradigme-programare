class Thread2(val x:Array<Int>):Thread() {
    public override fun run() {
        x.sort()
    }
}