package Objects

import relation.Element
import relation.Parent


class MainObject(val list: List<Pair<String, Element>>): Parent() {
    override var children: MutableList<Element> = mutableListOf()
    init {
        list.forEach { children.add(it.second) }
    }
    override fun toString(): String {
        return "{ ${
            list.joinToString(separator = ", ") { 
                "\"${it.first}\": ${it.second}" 
            }
         } }"
    }

}