package com.example.calculator

fun calc(exp: String?): Double {
    if (exp == null) {
        return 0.0
    }

    "Expression: $exp".also(::println)
    val num = exp.split("+", "-", "*", "/")

    val digitNum = num.map { s: String ->
        s.toDouble()
    }.toMutableList()

    var ops = exp.filter { op -> op == '+' || op == '-' || op == '/' || op == '*' }

// For solving division and multiplication

    while (ops.contains("/") || ops.contains("*")) {
        for ((indexes, op) in ops.withIndex()) {
            if (op == '/') {
                digitNum[indexes] = digitNum[indexes] / digitNum[indexes + 1]
                digitNum.removeAt(indexes + 1)
                ops = ops.filterIndexed { index, _ -> index != indexes }
                break
            } else if (op == '*') {
                digitNum[indexes + 1] = digitNum[indexes] * digitNum[indexes + 1]
                digitNum.removeAt(indexes)
                ops = ops.filterIndexed { index, _ -> index != indexes }
                break
            }

        }
    }
// For solving addition and subtraction
    digitNum.also(::println)
    while (ops.contains("+") || ops.contains("-")) {
        for ((indexes, op) in ops.withIndex()) {
            if (op == '+') {
                digitNum[indexes] = digitNum[indexes] + digitNum[indexes + 1]
                digitNum.removeAt(indexes + 1)
                ops = ops.filterIndexed { index, _ -> index != indexes }
                digitNum.also(::println)
                break
            } else if (op == '-') {
                digitNum[indexes + 1] = digitNum[indexes] - digitNum[indexes + 1]
                digitNum.removeAt(indexes)
                ops = ops.filterIndexed { index, _ -> index != indexes }
                break
            }

        }
    }

    return digitNum[0]
}