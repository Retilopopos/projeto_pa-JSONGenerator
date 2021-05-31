package tests

import Ignore
import InstantiateObject
import KeyName
import objects.ObjectIsString
import Visitors.NameVisitor
import org.junit.jupiter.api.Test
import kotlin.test.assertEquals

enum class Carro {
    Nissan,
    Mazda,
    Mustang
}
data class Estudante(
    val nome: String,
    val idade: Int,
    val repetente: Boolean,
    val carro: Carro,
    val outroAluno: Estudante? = null
)
data class Estudante2(
    val nome: String,
    @Ignore
    val idade: Int,
    val repetente: Boolean,
    @KeyName("Automóvel")
    val carro: Carro,
    val outroAluno: Estudante? = null
)

class Tests {


    @Test
    fun testObjectIsString() {
        val texto : String = "Aluno"

        assertEquals("\"$texto\"", InstantiateObject().instantiate(texto).toString())
    }
    @Test
    fun testObjectIsBoolean(){
        val vericidade: Boolean = true

        assertEquals("$vericidade", InstantiateObject().instantiate(vericidade).toString())
    }

    @Test
    fun testObjectIsNumber(){
        val numero: Number = 1

        assertEquals("$numero", InstantiateObject().instantiate(numero).toString())
    }

    @Test
    fun testObjectIsNull(){
        val valor = null

        assertEquals("$valor", InstantiateObject().instantiate(valor).toString())
    }

    @Test
    fun testObjectIsArray(){
        val lista = listOf(1,2,3)

        assertEquals("$lista", InstantiateObject().instantiate(lista).toString())
    }

    @Test
    fun testObjectIsEnum(){
        val enumerado = Carro.Mustang

        assertEquals("\"$enumerado\"", InstantiateObject().instantiate(enumerado).toString())
    }

    @Test
    fun testMainObject(){
        val aluno = Estudante("Bruno",22,true, Carro.Mustang)
        val aluno2 = Estudante("Miguel",22,true, Carro.Mazda, aluno)

        assertEquals("{ \"carro\": \"Mazda\", \"idade\": 22, \"nome\": \"Miguel\", \"outroAluno\": { \"carro\": \"Mustang\", \"idade\": 22, \"nome\": \"Bruno\", \"outroAluno\": null, \"repetente\": true }, \"repetente\": true }", InstantiateObject().instantiate(aluno2).toString())
    }

    @Test
    fun testMainObjectMap(){
        val aluno = Estudante("Bruno",22,true, Carro.Mustang)
        val aluno2 = Estudante("Miguel",22,true, Carro.Mazda, aluno)
        val mapa = mapOf(Pair(aluno, aluno2))

        assertEquals("{ \"Estudante(nome=Bruno, idade=22, repetente=true, carro=Mustang, outroAluno=null)\": { \"carro\": \"Mazda\", \"idade\": 22, \"nome\": \"Miguel\", \"outroAluno\": { \"carro\": \"Mustang\", \"idade\": 22, \"nome\": \"Bruno\", \"outroAluno\": null, \"repetente\": true }, \"repetente\": true } }", InstantiateObject().instantiate(mapa).toString())
    }

    @Test
    fun testSearch(){
        val visitor = NameVisitor() { it -> it is ObjectIsString}
        val aluno = Estudante("Bruno",22,true, Carro.Mustang)
        val aluno2 = Estudante("Miguel",22,true, Carro.Mazda, aluno)
        val alunoInstanciado = InstantiateObject().instantiate(aluno2)
        alunoInstanciado.accept(visitor)

        assertEquals("[\"Miguel\", \"Bruno\"]", visitor.searchList.toString())
    }

    @Test
    fun testSearchWithValue(){
        val visitor = NameVisitor() { it -> it is ObjectIsString && it.value == "Bruno"}
        val aluno = Estudante("Bruno",22,true, Carro.Mustang)
        val aluno2 = Estudante("Miguel",22,true, Carro.Mazda, aluno)
        val alunoInstanciado = InstantiateObject().instantiate(aluno2)
        alunoInstanciado.accept(visitor)

        assertEquals("[\"Bruno\"]", visitor.searchList.toString())
    }

    @Test
    fun testIgnoreAndKeyName(){
        val aluno = Estudante2("Bruno",22,true, Carro.Mustang)

        assertEquals("{ \"Automóvel\": \"Mustang\", \"nome\": \"Bruno\", \"outroAluno\": null, \"repetente\": true }", InstantiateObject().instantiate(aluno).toString())
    }

}