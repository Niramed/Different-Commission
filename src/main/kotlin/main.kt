fun main() {
    val cardType = "VK Pay" // тип карты
    val prevAmount = 0 // сумма предыдущих переводов в этом месяце
    val amount = 15001 * 100 // сумма перевода
    println("Комисcия по переводу ${amount / 100} рублей составит ${commis(cardType, prevAmount, amount)} копеек")

}

fun commis(cardType: String, prevAmount: Int, amount: Int): Int {
    val commisAmount = 0
    when (cardType) {
        "Mastercard", "Maestro" -> return limitMasMaeCard(amount, prevAmount)
        "Visa", "Мир" -> return limitVisMirCard(amount, prevAmount)
        "VK Pay" -> return limitVkPay(amount, prevAmount)
    }
    return commisAmount
}

fun limitMasMaeCard(amount: Int, prevAmount: Int): Int {
    var commisAmount = 0
    when {
        amount <= 150000 * 100 && prevAmount + amount <= 600000 * 100 -> commisAmount =
            if (amount < 75000 * 100) 0 else amount * 6 / 1000 + 20 * 100
        amount > 150000 * 100 -> println("Перевод не отправлен, превышена максимальная сумма перевода по одной карте!")
        prevAmount + amount > 600000 * 100 -> println("Перевод не отправлен, превышена максимальная сумма переводов в этом месяце!")
    }
    return commisAmount
}

fun limitVisMirCard(amount: Int, prevAmount: Int): Int {
    var commisAmount = 0
    when {
        amount <= 150000 * 100 && prevAmount + amount <= 600000 * 100 -> commisAmount =
            if (amount * 75 / 10000 <= 3500) 3500 else amount * 75 / 10000
        amount > 150000 * 100 -> println("Перевод не отправлен, превышена максимальная сумма перевода по одной карте!")
        prevAmount + amount > 600000 * 100 -> println("Перевод не отправлен, превышена максимальная сумма переводов в этом месяце!")
    }
    return commisAmount
}

fun limitVkPay(amount: Int, prevAmount: Int): Int {
    var commisAmount = 0
    when {
        amount <= 15000 * 100 && prevAmount + amount <= 40000 * 100 -> commisAmount = 0
        amount > 15000 * 100 -> println("Перевод не отправлен, превышена максимальная сумма перевода со счета VK Pay за один раз!")
        prevAmount + amount > 40000 * 100 -> println("Перевод не отправлен, превышена максимальная сумма переводов в этом месяце!")
    }
    return commisAmount
}



