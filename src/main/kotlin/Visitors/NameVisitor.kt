package Visitors

import InstantiateObject
import objects.MainObject
import objects.ObjectIsString
import relation.Children
import relation.Element
import relation.Parent
import tests.Carro
import tests.Estudante
import kotlin.test.assertEquals

class NameVisitor(val enter: (Element) -> Boolean): Visitor {

    val searchList = mutableListOf<Element>()

    override fun visit(children: Children) {
        if(enter(children)) searchList.add(children)
    }
    override fun visit(parent: Parent): Boolean {
        if(enter(parent)) searchList.add(parent)
        return true
    }


}
fun main(){
    val visitor = NameVisitor() { it -> it is ObjectIsString && it.value == "Bruno"}
    val aluno = Estudante("Bruno",22,true, Carro.Mustang)
    val aluno2 = Estudante("Miguel",22,true, Carro.Mazda, aluno)
    val alunoInstanciado = InstantiateObject().instantiate(aluno2)
    alunoInstanciado.accept(visitor)
    print(visitor.searchList.toString())
}