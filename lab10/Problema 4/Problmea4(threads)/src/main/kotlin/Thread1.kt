import java.io.File

class Thread1(val x:String,val y:String):Thread() {
    override fun run() {
        Write(y,x)

    }

    fun Write(line : String,fname:String) {
        File(fname).appendText(line)
        print("done")
    }


}

