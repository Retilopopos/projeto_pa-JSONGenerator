package Objects

import relation.Element
import Visitors.Visitor
import relation.Children

data class ObjectIsString (override val value: String) : Children() {

    override fun toString(): String {
        return "\"$value\""
    }
}