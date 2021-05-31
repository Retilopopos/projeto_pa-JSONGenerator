package plugins

import objects.*
import org.eclipse.swt.SWT
import org.eclipse.swt.events.SelectionAdapter
import org.eclipse.swt.events.SelectionEvent
import org.eclipse.swt.layout.FillLayout
import org.eclipse.swt.layout.GridData
import org.eclipse.swt.layout.GridLayout
import org.eclipse.swt.widgets.*
import tree.TreeSkeleton
import tree.expandAll
import java.lang.Exception

class Edit: Actions {
    override fun apply(treeSkeleton: TreeSkeleton){
        val newWindow = Shell(treeSkeleton.shell)
        var item = treeSkeleton.tree.selection.first()
        newWindow.setSize(100,100)

        newWindow.layout= GridLayout(2,false)
        newWindow.text = "Edit Element"

        val nText = Text(newWindow, SWT.BORDER)
        val ok = Button(newWindow, SWT.PUSH)
        ok.text = "OK"
        newWindow.open()
        ok.addSelectionListener(object: SelectionAdapter(){
            override fun widgetSelected(e: SelectionEvent) {
                when(item.data) {
                    is ObjectIsString -> (item.data as ObjectIsString).value = nText.text
                    is ObjectIsNumber -> try{
                            (item.data as ObjectIsNumber).value = nText.text.toInt()
                    } catch (e: Exception){
                        val inputWindow = Shell(treeSkeleton.shell)
                        inputWindow.text = "Error"
                        inputWindow.setSize(170,80)
                        inputWindow.layout = FillLayout(SWT.VERTICAL)
                        val errorMessage = Label(inputWindow, SWT.NULL)
                        errorMessage.text = "Wrong type of input!"
                        inputWindow.open()
                    }
                    is ObjectIsEnum -> {
                        val errorWindow = Shell(treeSkeleton.shell)
                        errorWindow.text = "Error"
                        errorWindow.setSize(170,80)
                        errorWindow.layout = FillLayout(SWT.VERTICAL)
                        val errorMessage = Label(errorWindow, SWT.NULL)
                        errorMessage.text = "Can't modify enum!"
                        errorWindow.open()
                    }
                    is ObjectIsBoolean -> (item.data as ObjectIsBoolean).value = nText.text.toBoolean()
                    is MainObject, is ObjectIsArray -> {
                        val errorWindow = Shell(treeSkeleton.shell)
                        errorWindow.text = "Error"
                        errorWindow.setSize(170,80)
                        errorWindow.layout = FillLayout(SWT.VERTICAL)
                        val errorMessage = Label(errorWindow, SWT.NULL)
                        errorMessage.text = "Can't modify main element!"
                        errorWindow.open()
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