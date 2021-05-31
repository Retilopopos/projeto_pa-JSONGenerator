import plugins.PluginsInjector
import tests.Carro
import tree.TreeSkeleton

fun main(){
    val aluno = tests.Estudante("Bruno", 22, true, Carro.Mustang)
    val aluno2 = tests.Estudante("Miguel", 22, true, Carro.Mazda, aluno)
    val x = InstantiateObject().instantiate(aluno2)
    val y = PluginsInjector.create(TreeSkeleton(x))
    y.open()
}