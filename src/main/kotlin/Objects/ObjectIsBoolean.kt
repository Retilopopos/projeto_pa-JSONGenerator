package Objects

import relation.Children

data class ObjectIsBoolean(override val value: Boolean) : Children() {

    override fun toString(): String {
        return "$value"
    }
}