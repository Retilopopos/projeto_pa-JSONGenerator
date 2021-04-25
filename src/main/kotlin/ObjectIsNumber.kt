data class ObjectIsNumber(val NumberName: String, val NumberValue: Number) : Element() {
    override fun accept(v: Visitor) {
        v.visit(this)
    }
    override fun toString(): String {
        return "\"${NumberName}\": $NumberValue"
    }
}