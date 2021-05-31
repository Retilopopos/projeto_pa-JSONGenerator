package Visitors

import InstantiateObject
import objects.MainObject
import objects.ObjectIsString
import relation.Children
import relation.Element
import relation.Parent
import tests.Carro
import tests.Estudante

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
