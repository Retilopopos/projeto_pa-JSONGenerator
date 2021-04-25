fun main(){
    val visitor = NameVisitor()
    val a = Aluno(null,"Bruno",null, "Lei", Aluno("bleh","Carlos", 2, "IGE"))
    val b = visitor.iniciate(a)
    print(b)

}