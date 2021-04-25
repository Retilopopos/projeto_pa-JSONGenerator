class NameVisitor: Visitor {

    var result: String = ""

    override fun visit(myObject: MyObject) {
        result += "{\n"
    }

    override fun visit(objectString: ObjectIsString) {
        result += "$objectString, \n"
    }

    override fun visit(objectNumber: ObjectIsNumber) {
        result += "$objectNumber, \n"
    }

    override fun endvisit(Object: MyObject) {
        result += "}\n"
    }

    fun iniciate(obj : Any): String{
        val receivedObj = MyObject(obj)
        receivedObj.accept(this)
        return result
    }
}