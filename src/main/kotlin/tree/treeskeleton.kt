package tree
import java.util.*
import InstantiateObject
import objects.MainObject
import objects.ObjectIsArray
import org.eclipse.swt.SWT
import org.eclipse.swt.events.SelectionAdapter
import org.eclipse.swt.events.SelectionEvent
import org.eclipse.swt.layout.GridData
import org.eclipse.swt.layout.GridLayout
import org.eclipse.swt.layout.RowData
import org.eclipse.swt.widgets.*
import relation.Children
import relation.Element
import tests.Carro
import org.eclipse.swt.graphics.Color
import plugins.*


class TreeSkeleton(val instantiate: Element) {
    val shell: Shell
    val tree: Tree
    val info: Label
    val text: Text

    @Inject
    private lateinit var iconsPlugin: Icons
    @Injectadd
    private var actionsPlugin = mutableListOf<Actions>()

    init {
        val display = Display()
        shell = Shell(display)

        shell.text = "File tree skeleton"
        shell.layout = GridLayout(2,false)

        tree = Tree(shell,SWT.BORDER)
        tree.layoutData = GridData(GridData.FILL_BOTH)

        info = Label(shell, SWT.BORDER or SWT.WRAP)
        info.layoutData = GridData(GridData.FILL_BOTH)

        tree.addSelectionListener(object : SelectionAdapter() {
            override fun widgetSelected(e: SelectionEvent) {
                info.text = tree.selection.first().data.toString()
            }
        })

        val searchLabel = Label(shell, SWT.NULL)
        searchLabel.text = "Insert text you want to find here:"

        text = Text(shell,  SWT.SINGLE or SWT.BORDER)
        text.layoutData = GridData(GridData.HORIZONTAL_ALIGN_FILL)
        text.addModifyListener { highlight() }
        createTree(instantiate, tree)

    }

    private fun addActions(treeSkeleton: TreeSkeleton){
        actionsPlugin.forEach {
            val x = Button(shell, SWT.PUSH)
            x.text = it.name
            x.addSelectionListener(object: SelectionAdapter(){
                override fun widgetSelected(e: SelectionEvent) {
                    it.apply(treeSkeleton)
                }
            })
        }
    }

    fun plugins(){
        iconsPlugin.apply(this)
    }

    fun isTree(branch: Any): TreeItem {
        if (branch is Tree) {
            return TreeItem(tree, SWT.NONE)
        }else{
            return TreeItem(branch as TreeItem, SWT.NONE)
        }
    }
    fun createTree(instantiate: Element, branch: Any, name: String? = null){
        val treeItem = isTree(branch)
        when(instantiate) {
            is MainObject -> {
                if (name != null){
                    treeItem.text = "object: $name"
                } else {
                    treeItem.text = "object"
                }
                treeItem.data = instantiate
                instantiate.list.forEach{
                    createTree(it.second, treeItem, it.first)
                }
            }
            is ObjectIsArray -> {
                treeItem.text = "$name"
                treeItem.data = instantiate
                instantiate.list.forEach {
                    createTree(it, treeItem)
                }
            }
                is Children ->{
                    treeItem.text = "$name: $instantiate"
                    treeItem.data = instantiate
            }
        }
    }

    fun open() {

        tree.expandAll()
        iconsPlugin.apply(this)
        addActions(this)
        shell.setSize(500, 500)
        shell.open()
        val display = Display.getDefault()
        while (!shell.isDisposed) {
            if (!display.readAndDispatch()) display.sleep()
        }
        display.dispose()

    }

    fun highlight(){
        tree.traverse {
            if(it.text.contains(text.text) && text.text !=""){
                it.background = Color(223, 122, 22)
            } else {
                it.background = null
            }
        }
    }

}

fun Tree.expandAll() = traverse { it.expanded = true }

fun Tree.traverse(visitor: (TreeItem) -> Unit) {
    fun TreeItem.traverse() {
        visitor(this)
        items.forEach {
            it.traverse()
        }
    }
    items.forEach { it.traverse() }
}