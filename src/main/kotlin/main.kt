val maxLimitInDay = 150_000
val maxLimitInMonthCard = 600_000
val maxLimitInMonthVkPay = 40_000
val maxLimitInMonthPromo = 75_000
val maxLimitOnce = 15_000
val baseCommisionMaestro = 0.006
val baseCommisionVisa = 0.0075
val minBaseCommisionMaestro = 20.0
val minBaseCommisionVisa = 35.0

fun main() {

    resultCommision("МИР", 1_000, 601000, 0)
}

fun resultCommision(typeCard: String = "Vk Pay", amount: Int, maxAmountInMonth: Int = 0, maxAmountInDay: Int) {
    println("Ваш тип карты/счета: $typeCard")
    println("Перевод на сумму: $amount руб.")
    when (typeCard) {
        "MasterCard", "Maestro" -> resultCommisionMasterCardMaestro(amount, maxAmountInMonth, maxAmountInDay)
        "Visa", "МИР" -> resultCommisionVisaMir(amount, maxAmountInMonth, maxAmountInDay)
        "Vk Pay" -> resultCommisionVkPay(amount, maxAmountInMonth)
    }
}

fun resultCommisionMasterCardMaestro(amount: Int, maxAmountInMonth: Int = 0, maxAmountInDay: Int = 0) {
    when {
        maxAmountInDay > maxLimitInDay -> println("Превышен максимальный лимит суммы перевода ($maxLimitInDay руб.) в день!")
        maxAmountInMonth > maxLimitInMonthCard -> println("Превышен максимальный лимит суммы перевода ($maxLimitInMonthCard руб.) в месяц!")
        maxAmountInMonth > maxLimitInMonthPromo -> println(
            "Ваша комиссия с суммы $amount руб. составит " +
                    "${amount * baseCommisionMaestro + minBaseCommisionMaestro} руб."
        )
        else -> println("Коммисия за данный перевод остуствует")
    }
}

fun resultCommisionVisaMir(amount: Int, maxAmountInMonth: Int = 0, maxAmountInDay: Int = 0) {
    when {
        maxAmountInDay > maxLimitInDay -> println("Превышен максимальный лимит суммы перевода ($maxLimitInDay руб.) в день!")
        maxAmountInMonth > maxLimitInMonthCard -> println("Превышен максимальный лимит суммы перевода ($maxLimitInMonthCard руб.) в месяц!")
        amount * baseCommisionVisa < minBaseCommisionVisa -> println("Ваша комиссия с суммы $amount руб. составит $minBaseCommisionVisa руб.")
        else -> println("Ваша комиссия с суммы $amount руб. составит " + "${amount * baseCommisionVisa} руб.")
    }
}

fun resultCommisionVkPay(amount: Int, maxAmountInMonth: Int) {
    when {
        amount > maxLimitOnce -> println("Превышен лимит перевода ($maxLimitOnce руб. за 1 раз)!")
        maxAmountInMonth > maxLimitInMonthVkPay -> println("Превышен максимальный лимит суммы перевода ($maxLimitInMonthVkPay руб.) в месяц!")
        else -> println("Коммисия за данный перевод остуствует")
    }
}
