package plugins

import tree.TreeSkeleton

interface Actions {
    val name: String
    fun apply(treeSkeleton: TreeSkeleton)
}