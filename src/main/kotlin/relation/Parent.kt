package relation

import Visitors.Visitor

abstract class Parent: Element() {
    abstract val children: MutableList<Element>
    override fun accept(v: Visitor) {
        if(v.visit(this))
            children.forEach{
                it.accept(v)
            }
    }
}