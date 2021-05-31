package plugins
import tree.TreeSkeleton
import java.io.File
import java.util.*
import kotlin.reflect.KClass
import kotlin.reflect.KMutableProperty
import kotlin.reflect.full.createInstance
import kotlin.reflect.full.declaredMemberProperties
import kotlin.reflect.full.hasAnnotation
import kotlin.reflect.jvm.isAccessible

@Target(AnnotationTarget.PROPERTY)
annotation class Inject

@Target(AnnotationTarget.PROPERTY)
annotation class Injectadd

class PluginsInjector  {
    companion object {
        private val map: MutableMap<String, List<KClass<*>>> = mutableMapOf()

        init {
            val scanner = Scanner(File("src/main/kotlin/di.properties"))
            while(scanner.hasNextLine()) {
                val line = scanner.nextLine()
                val parts = line.split("=")
                map[parts[0]] = parts[1].split(",")
                    .map { Class.forName(it).kotlin }
            }
            scanner.close()
        }

        fun create(treeSkeleton: TreeSkeleton) : TreeSkeleton {
            val o = treeSkeleton::class
            o.declaredMemberProperties.forEach {
                if(it.hasAnnotation<Inject>()) {
                    it.isAccessible = true
                    val key = o.simpleName + "." + it.name
                    val obj = map[key]!!.first().createInstance()
                    (it as KMutableProperty<*>).setter.call(treeSkeleton, obj)
                } else if(it.hasAnnotation<Injectadd>()){
                    it.isAccessible = true
                    val key = o.simpleName + "." + it.name
                    val allActions =  mutableListOf<Actions>()
                    map[key]!!.forEach {
                        it1 -> allActions.add(it1.createInstance() as Actions)
                    }
                    (it as KMutableProperty<*>).setter.call(treeSkeleton, allActions)
                }
            }
            return treeSkeleton
        }
    }

}