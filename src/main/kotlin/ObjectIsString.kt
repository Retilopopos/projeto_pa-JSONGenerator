data class ObjectIsString (val StringName: String, val StringValue: String) : Element() {
    override fun accept(v: Visitor) {
        v.visit(this)
    }

    override fun toString(): String {
        return "\"${StringName}\": \"${StringValue}\""
    }
}