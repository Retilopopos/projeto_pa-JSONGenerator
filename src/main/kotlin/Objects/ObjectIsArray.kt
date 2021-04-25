package Objects

import relation.Element
import relation.Parent


class ObjectIsArray(val list: List<Element>): Parent() {
    override var children: MutableList<Element> = mutableListOf()

    override fun toString(): String {
        return "[ ${
            list.joinToString(separator = ", ") {
                "$it"
            }
        } ]"
    }

}