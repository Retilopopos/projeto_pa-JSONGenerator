import Objects.*
import relation.Element
import kotlin.reflect.full.declaredMemberProperties

class InstantiateObject {
    fun instantiate(receivingObject: Any?): Element{
        return when(receivingObject){
            is String -> ObjectIsString(receivingObject)
            is Boolean -> ObjectIsBoolean(receivingObject)
            is Number -> ObjectIsNumber(receivingObject)
            null -> ObjectIsNull(receivingObject)
            is List<Any?> -> ObjectIsArray(receivingObject.map { instantiate(it) })
            is Set<Any?> -> ObjectIsArray(receivingObject.map { instantiate(it) })
            is Enum<*> -> ObjectIsEnum(receivingObject)
            //is Map<*,*> ->
            else -> MainObject(receivingObject::class.declaredMemberProperties.map{ Pair(it.name, instantiate(it.call(receivingObject))) })
        }
    }
}