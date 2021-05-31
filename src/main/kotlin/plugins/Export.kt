package plugins

import org.eclipse.swt.SWT
import org.eclipse.swt.events.SelectionAdapter
import org.eclipse.swt.events.SelectionEvent
import org.eclipse.swt.layout.GridLayout
import org.eclipse.swt.widgets.Button
import org.eclipse.swt.widgets.Label
import org.eclipse.swt.widgets.Shell
import org.eclipse.swt.widgets.Text
import tree.TreeSkeleton
import java.io.File

class Export: Actions {
    override fun apply(treeSkeleton: TreeSkeleton){
        val newWindow = Shell(treeSkeleton.shell)
        var item = treeSkeleton.tree.selection.first()


        newWindow.setSize(200,100)

        newWindow.layout= GridLayout(2,false)
        newWindow.text = "Export to file"
        val label = Label(newWindow, SWT.NULL)
        label.text = "File name:"
        val nText = Text(newWindow, SWT.BORDER)
        val ok = Button(newWindow, SWT.PUSH)


        ok.text = "OK"
        newWindow.open()
        ok.addSelectionListener(object: SelectionAdapter(){
            override fun widgetSelected(e: SelectionEvent) {
                var fileName = nText.text + ".txt"
                val myfile = File(fileName)
                myfile.printWriter().use { out ->
                    out.println(item.data)
                }
                newWindow.close()
                }
        })
    }


}

