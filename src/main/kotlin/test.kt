import Objects.*
import Visitors.NameVisitor
import com.sun.tools.javac.Main
import relation.Element
import kotlin.reflect.full.declaredMemberExtensionProperties
import kotlin.reflect.full.declaredMemberProperties
data class Estudante(
    val nome: String,
    val idade: Int,
    val repetente: Boolean,
    val curso: Curso,
    val outroAluno: Estudante? = null
)
enum class Curso {
    LEI,
    MEI
}
val a = "Bruno"
val a1 = ObjectIsNumber(1)
val a3 = "Carlos"
val a4 = ObjectIsNumber(2)
val a5 = MainObject(listOf(Pair(a,a1)))
val b = listOf(Pair(a,a1),Pair(a3,a4), Pair("Objeto", a5))
val c = arrayListOf(ObjectIsBoolean(true), ObjectIsNull(), ObjectIsString("a"))
fun main(){

    val aluno = Estudante("Bruno",1,true, Curso.LEI)
    val aluno2 = Estudante("Miguel",1,true, Curso.MEI, aluno)
    val visitor = NameVisitor() { it -> it is ObjectIsString}
    print("JSONString" + MainObject(b))
    print("\n")
    val asdf = InstantiateObject().instantiate(listOf(aluno2, aluno))
    val ass2 = asdf.accept(visitor)
    print("Search: " + visitor.searchList)
    print("\n")
    print("JSONArray" + ObjectIsArray(c))
    print("\n")

    print("array: $asdf")

}