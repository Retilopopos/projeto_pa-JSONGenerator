package objects

import relation.Children

data class ObjectIsNull(override val value: Nothing? = null) : Children() {

    override fun toString(): String {
        return "null"
    }
}