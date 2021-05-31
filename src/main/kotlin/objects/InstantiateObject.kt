import objects.*
import relation.Element
import kotlin.reflect.full.declaredMemberProperties
import kotlin.reflect.full.findAnnotation
import kotlin.reflect.full.hasAnnotation

@Target(AnnotationTarget.PROPERTY)
annotation class Ignore

@Target(AnnotationTarget.PROPERTY)
annotation class KeyName(val name: String)

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
            is Map<*,*> -> MainObject(receivingObject.toList().map { Pair(it.first.toString(), instantiate(it.second)) } as MutableList<Pair<String, Element>>)
            else -> MainObject(receivingObject::class.declaredMemberProperties.filterNot { it.hasAnnotation<Ignore>() }.map{
                Pair(if(it.hasAnnotation<KeyName>()) it.findAnnotation<KeyName>()!!.name else it.name, instantiate(it.call(receivingObject)))
            } as MutableList<Pair<String, Element>>)
        }
    }
}