interface Visitor {
    fun visit(Object: MyObject)
    fun visit(ObjectNumber: ObjectIsNumber)
    fun visit(ObjectIsString: ObjectIsString)
    fun endvisit(Object: MyObject)
}