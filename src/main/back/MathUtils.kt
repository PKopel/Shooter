package main.back

fun angle(x1: Int, x2:Int, y1:Int, y2:Int) : Double =
        Math.atan2((y1-y2).toDouble(),(x1-x2).toDouble())

infix fun <T : Comparable<T>> ClosedRange<T>.intersection(r: ClosedRange<T>): ClosedRange<T>? {
    val l = when {
        this contains r.start -> r.start
        r contains this.start -> this.start
        else -> null
    }
    val h = when {
        this contains r.endInclusive -> r.endInclusive
        r contains this.endInclusive -> this.endInclusive
        else -> null
    }
    return if (l != null && h != null) l..h
    else null
}

infix operator fun <T : Comparable<T>> ClosedRange<T>.contains(n: T): Boolean {
    return this.start <= n && n <= this.endInclusive
}