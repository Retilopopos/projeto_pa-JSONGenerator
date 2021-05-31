package objects

import relation.Children

data class ObjectIsNumber(override var value: Number) : Children() {

    override fun toString(): String {
        return "$value"
    }
}