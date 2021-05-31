package plugins

import tree.TreeSkeleton

interface Actions {
    fun apply(treeSkeleton: TreeSkeleton)
}