package plugins


import objects.MainObject
import org.eclipse.swt.graphics.Image
import org.eclipse.swt.widgets.Display
import relation.Children
import tree.TreeSkeleton
import tree.traverse


class Icons {
    fun apply(treeWindow: TreeSkeleton){
        var fileImage: Image = Image(Display.getDefault(), "C:\\Users\\Bruno\\Desktop\\Mestrado\\PA\\ProjetoPA\\projeto_pa-JSONGenerator\\src\\main\\kotlin\\icons\\file.png")
        var folderImage: Image = Image(Display.getDefault(), "C:\\Users\\Bruno\\Desktop\\Mestrado\\PA\\ProjetoPA\\projeto_pa-JSONGenerator\\src\\main\\kotlin\\icons\\folder.png")

        treeWindow.tree.traverse {
            when(it.data) {
                is MainObject -> {
                    it.image = Image(Display.getDefault(), folderImage.imageData.scaledTo(30,30))
                }
                is Children -> {
                    it.image = Image(Display.getDefault(), fileImage.imageData.scaledTo(30,30))
                }
            }
        }

    }
}