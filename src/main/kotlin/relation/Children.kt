package relation

import Visitors.Visitor

abstract class Children: Element() {
    abstract val value: Any?
    override fun accept(v: Visitor) {
        v.visit(this)
    }
}