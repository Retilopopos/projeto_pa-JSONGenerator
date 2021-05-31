package plugins

import InstantiateObject
import com.sun.tools.javac.Main
import objects.*
import org.eclipse.swt.SWT
import org.eclipse.swt.events.SelectionAdapter
import org.eclipse.swt.events.SelectionEvent
import org.eclipse.swt.layout.FillLayout
import org.eclipse.swt.layout.GridLayout
import org.eclipse.swt.widgets.Button
import org.eclipse.swt.widgets.Label
import org.eclipse.swt.widgets.Shell
import org.eclipse.swt.widgets.Text
import relation.Element
import tree.TreeSkeleton
import tree.expandAll
import java.lang.Exception

class AddToTree: Actions {
    override fun apply(treeSkeleton: TreeSkeleton){
        val newWindow = Shell(treeSkeleton.shell)
        var item = treeSkeleton.tree.selection.first()
        newWindow.setSize(200,200)

        newWindow.layout= GridLayout(2,false)
        newWindow.text = "Add element"
        val label = Label(newWindow, SWT.NULL)
        label.text = "Name:"
        val nTextString = Text(newWindow, SWT.BORDER)
        val label2 = Label(newWindow, SWT.NULL)
        label2.text = "Value:"
        val nTextElement = Text(newWindow,SWT.BORDER)
        val ok = Button(newWindow, SWT.PUSH)
        ok.text = "OK"
        newWindow.open()
        ok.addSelectionListener(object: SelectionAdapter(){
            override fun widgetSelected(e: SelectionEvent) {
                when(item.data) {
                    is ObjectIsString -> {
                        val inputWindow = Shell(treeSkeleton.shell)
                        inputWindow.text = "Error"
                        inputWindow.setSize(170,80)
                        inputWindow.layout = FillLayout(SWT.VERTICAL)
                        val errorMessage = Label(inputWindow, SWT.NULL)
                        errorMessage.text = "You have to select object"
                        inputWindow.open()
                    }
                    is ObjectIsNumber -> {
                        val inputWindow = Shell(treeSkeleton.shell)
                        inputWindow.text = "Error"
                        inputWindow.setSize(170,80)
                        inputWindow.layout = FillLayout(SWT.VERTICAL)
                        val errorMessage = Label(inputWindow, SWT.NULL)
                        errorMessage.text = "You have to select object"
                        inputWindow.open()
                    }
                    is ObjectIsBoolean -> {
                        val inputWindow = Shell(treeSkeleton.shell)
                        inputWindow.text = "Error"
                        inputWindow.setSize(170,80)
                        inputWindow.layout = FillLayout(SWT.VERTICAL)
                        val errorMessage = Label(inputWindow, SWT.NULL)
                        errorMessage.text = "You have to select object"
                        inputWindow.open()
                    }
                    is MainObject, is ObjectIsArray -> {
                        val x= InstantiateObject().instantiate(nTextElement.text)
                        (item.data as MainObject).list.add(Pair(nTextString.text as String,x))
                    }
                }
                newWindow.close()
                treeSkeleton.info.text = item.data.toString()
                treeSkeleton.tree.removeAll()
                treeSkeleton.createTree(treeSkeleton.instantiate, treeSkeleton.tree)
                treeSkeleton.tree.expandAll()
                treeSkeleton.plugins()
            }
        })
    }
}