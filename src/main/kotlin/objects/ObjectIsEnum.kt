package objects

import relation.Children

data class ObjectIsEnum (override var value: Enum<*>) : Children() {

    override fun toString(): String {
        return "\"${value.name}\""
    }
}