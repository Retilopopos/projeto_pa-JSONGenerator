package objects

import relation.Children

data class ObjectIsString (override var value: String) : Children() {

    override fun toString(): String {
        return "\"$value\""
    }
}