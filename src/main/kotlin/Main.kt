fun main(){
    val visitor = NameVisitor()
    val a = Aluno("Bruno",null, "Lei", Aluno("Carlos", 2, "IGE"))
    val b = visitor.iniciate(a)
    print(b)

}