package objects

import relation.Element
import relation.Parent


class MainObject(var list: MutableList<Pair<String, Element>>): Parent() {
    override val child: MutableList<Element> = mutableListOf()
    init {
        list.forEach { child.add(it.second) }
    }
    override fun toString(): String {
        return "{ ${
            list.joinToString(separator = ", ") { 
                "\"${it.first}\": ${it.second}" 
            }
         } }"
    }

}