package Objects

import relation.Children

data class ObjectIsNumber(override val value: Number) : Children() {

    override fun toString(): String {
        return "$value"
    }
}