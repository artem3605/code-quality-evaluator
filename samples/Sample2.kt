fun main() {
    val t = readln().toInt()
    repeat(t) {
        val n = readln().toInt()
        val m = if (n == 1) 1 else if (n == 2) 3 else n * 2
        var sum = 0
        for (i in 1..n) {
            sum += (2 * i - 1) * i
        }
        val p = List(n) {it + 1}
        println("$sum $m")
        when (n) {
            1 -> {
                println("1 1 1")
            }
            2 -> {
                println("1 1 1 2")
                println("1 2 1 2")
                println("2 1 1 2")
            }
            else -> {
                for (i in n downTo 1) {
                    println("1 $i ${p.joinToString(" ")}")
                    println("2 $i ${p.joinToString(" ")}")
                }
            }
        }
    }
}