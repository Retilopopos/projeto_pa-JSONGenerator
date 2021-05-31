package objects

import relation.Children

data class ObjectIsBoolean(override var value: Boolean) : Children() {
    override fun toString(): String {
        return "$value"
    }
}