package relation

import Visitors.Visitor

abstract class Parent: Element() {
    abstract val child: MutableList<Element>
    override fun accept(v: Visitor) {
        if(v.visit(this))
            child.forEach{
                it.accept(v)
            }
    }
}