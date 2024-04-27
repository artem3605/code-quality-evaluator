fun main() {
    repeat(readln().toInt()) { _ ->
        val x = readln().map { it - '0' }.toIntArray()
        val y = readln().map { it - '0' }.toIntArray()

        var i = 0
        while (i < x.size && x[i] == y[i]) i += 1

        if (i < x.size && x[i] < y[i]) {
            swap_543(x, y, i)
        }
        i += 1

        while (i < x.size) {
            if (x[i] > y[i]) swap_543(x, y, i)
            i += 1
        }
        println(x.joinToString(""))
        println(y.joinToString(""))
    }
}

fun swap_543(x: IntArray, y: IntArray, i: Int) {
    val tmp = x[i]
    x[i] = y[i]
    y[i] = tmp
}
