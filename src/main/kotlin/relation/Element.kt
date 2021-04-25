package relation

import Visitors.Visitor

abstract class Element {
    abstract fun accept(v: Visitor)
}