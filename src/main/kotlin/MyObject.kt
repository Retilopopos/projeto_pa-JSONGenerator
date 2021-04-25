import kotlin.reflect.full.declaredMemberProperties

class MyObject(val receivingObject: Any) : Element() {

    constructor(nome: String, receivingObject: Any) : this(receivingObject)

    fun checkType(objName: String, objValue: Any?, v: Visitor){
        when(objValue){
            is Number -> ObjectIsNumber(objName, objValue).accept(v)
            is String -> ObjectIsString(objName, objValue).accept(v)
            else -> objValue?.let { MyObject(it).accept(v) }
        }
    }

    override fun accept(v: Visitor) {
        v.visit(this)
        val clazz = receivingObject::class
        clazz.declaredMemberProperties.map{ checkType(it.name, it.call(receivingObject), v) }
        v.endvisit(this)
    }



}