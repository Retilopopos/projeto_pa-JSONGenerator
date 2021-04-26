package Objects

import relation.Element
import relation.Parent


class MainObject(val list: List<Pair<String, Element>>): Parent() {
    override var child: MutableList<Element> = mutableListOf()
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