package Objects


import relation.Children

data class ObjectIsEnum (override val value: Enum<*>) : Children() {

    override fun toString(): String {
        return "\"${value.name}\""
    }
}