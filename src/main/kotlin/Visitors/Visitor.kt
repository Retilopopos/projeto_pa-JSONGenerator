package Visitors

import relation.Children
import relation.Parent

interface Visitor {
    fun visit(children: Children)
    fun visit(parent: Parent): Boolean=true
}