/*
package Objects

import relation.Element
import Visitors.Visitor
import kotlin.reflect.full.declaredMemberProperties

class MyObject(val nome: String? = null, val receivingObject: Any) : Element() {

    constructor(receivingObject: Any) : this(null, receivingObject)

    fun checkType(objName: String, objValue: Any?, v: Visitor){

        when(objValue){
            is Number -> ObjectIsNumber(objValue).accept(v)
            is String -> ObjectIsString(objValue).accept(v)
            else -> objValue?.let { MyObject(objName, it).accept(v) }
        }
    }

    override fun accept(v: Visitor) {
        v.visit(this)
        val clazz = receivingObject::class
        clazz.declaredMemberProperties.map{ checkType(it.name, it.call(receivingObject), v) }
        v.endvisit(this)
    }

    fun encapsulateJson(): String {
        if (nome == null){
            return "{\n"
        }else
            return "\"$nome\" : {\n"
    }


}*/
