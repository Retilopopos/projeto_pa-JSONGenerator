import Objects.*
import Visitors.NameVisitor
import com.sun.tools.javac.Main
import relation.Element
import kotlin.reflect.full.declaredMemberExtensionProperties
import kotlin.reflect.full.declaredMemberProperties

val a = "Bruno"
val a1 = ObjectIsNumber(1)
val a3 = "Carlos"
val a4 = ObjectIsNumber(2)
val a5 = MainObject(listOf(Pair(a,a1)))
val b = listOf(Pair(a,a1),Pair(a3,a4), Pair("Objeto", a5))
val c = arrayListOf(ObjectIsBoolean(true), ObjectIsNull(), ObjectIsString("a"))
fun main(){

    val visitor = NameVisitor() { it -> it is ObjectIsNumber}
    print("JSONString" + MainObject(b))
    print("\n")
    val ass=MainObject(b).accept(visitor)
    val clazz = MainObject(b)::class
    val show = clazz.declaredMemberProperties.map { it.call(MainObject(b)) }
    print("show $show")
    print("\n")
    print("Search: " + visitor.searchList)
    print("\n")
    print("JSONArray" + ObjectIsArray(c))

}