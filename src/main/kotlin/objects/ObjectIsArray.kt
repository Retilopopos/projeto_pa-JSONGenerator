package objects

import relation.Element
import relation.Parent


class ObjectIsArray(val list: List<Element>): Parent() {
    override val child: MutableList<Element> = mutableListOf()
    init {
        list.forEach { child.add(it) }
    }
    override fun toString(): String {
        return "[${
            list.joinToString(separator = ", ") {
                "$it"
            }
        }]"
    }

}