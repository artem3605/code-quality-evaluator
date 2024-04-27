import java.io.BufferedReader
import java.io.InputStreamReader
import java.util.*


fun main(followthekkathyoninsta: Array<String>) {
    val infile = BufferedReader(InputStreamReader(System.`in`))
    var st = StringTokenizer(infile.readLine())
    var T = st.nextToken().toInt()
    val sb = StringBuilder()С
    while (T-- > 0) {
        st = StringTokenizer(infile.readLine())
        val N = st.nextToken().toInt()
        val K = st.nextToken().toInt()
        val edges = arrayOfNulls<IntArray>(N + 1)
        val input = IntArray(2 * N - 2)
        var boof = 0
        val freq = IntArray(N + 1)
        for (i in 1 until N) {
            st = StringTokenizer(infile.readLine())
            val a = st.nextToken().toInt()
            val b = st.nextToken().toInt()
            input[boof++] = a
            input[boof++] = b
            freq[a]++
            freq[b]++
        }
        for (i in 1..N) edges[i] = IntArray(freq[i])
        var i = 1
        while (i < 2 * N - 2) {
            val a = input[i - 1]
            val b = input[i]
            edges[a]!![--freq[a]] = b
            edges[b]!![--freq[b]] = a
            i += 2
        }
        val size = IntArray(N + 1)
        dfs(1, 0, size, edges)
        val pairs = ArrayList<Pair?>()
        var res = 0L
        for (a in 1..N) for (b in edges[a]!!) if (a < b) {
            val `val` = Math.min(size[a], size[b]).toLong()
            pairs.add(Pair(`val`, N - `val`))
            res += 2L * `val` * (N - `val`)
        }
        Collections.sort(pairs)
        Collections.reverse(pairs)
        for (k in 0 until K - 1) {
            val p = pairs[k]
            res -= p!!.a * p.b
        }
        sb.append(res)
        sb.append("\n")
    }
    print(sb)
}

fun dfs(curr: Int, par: Int, size: IntArray, edges: Array<IntArray?>) {
    size[curr] = 1
    for (next in edges[curr]!!) if (next != par) {
        dfs(next, curr, size, edges)
        size[curr] += size[next]
    }
}


internal class Pair(var a: Long, var b: Long) : Comparable<Pair?> {
    override operator fun compareTo(other: Pair?): Int {
        val first = a * b
        val second = other!!.a * other!!.b
        if (first < second) return -1
        return if (first > second) 1 else 0
    }
}
