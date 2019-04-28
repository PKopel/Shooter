package test

object SetTest {

    class CustomEquals(val a: Int, val b: Int) {
        override fun equals(other: Any?): Boolean {
            return if (other is CustomEquals)
                (a <= other.a && other.a <= a + b) ||
                        (other.a <= a && a <= other.a + other.b)
            else false
        }

        override fun hashCode(): Int {
            var result = a
            result = 31 * result + b
            return result
        }

    }


    @JvmStatic
    fun main(args: Array<String>) {
        val ce1 = CustomEquals(20, 30)
        val ce2 = CustomEquals(25, 30)
        val ce3 = CustomEquals(30, 20)
        val set = HashSet<CustomEquals>()
        println(ce1 == ce2)
        set.add(ce1)
        set.add(ce2)
        set.add(ce3)
        println(set.size)

    }
}
