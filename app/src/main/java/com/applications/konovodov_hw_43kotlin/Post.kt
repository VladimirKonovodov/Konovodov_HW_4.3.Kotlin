package com.applications.konovodov_hw_43kotlin

data class Post(
    val id: Long,
    val author: String,
    val date: String,
    val content: String,
    val created: Int,//в секундах с момента создания
    val commentCount: Int,
    var likeCounter: Int,
    val videoContent: Boolean,
    val videoSrc: String
) {


    fun publishedAgo(/*sec: Int*/): String {
        val sec = this.created
        val secInYear = 365 * 24 * 60 * 60
        val secInDay = 24 * 60 * 60
        val secInHour = 60 * 60
        val secInMinute = 60

        val years = sec / secInYear
        val days = (sec % secInYear) / secInDay
        val hours = ((sec % secInYear) % secInDay) / secInHour
        val minutes = (((sec % secInYear) % secInDay) % secInHour) / secInMinute
        val seconds = (((sec % secInYear) % secInDay) % secInHour) % secInMinute

        val countYearsStr = alfabetAmount(years, "MALE")
        val yearsStr = if (countYearsStr == "") ""
        else declensionFunc(years, "YEARS")

        val countDaysStr = alfabetAmount(days, "MALE")
        val daysStr = if (countDaysStr == "") ""
        else declensionFunc(days, "DAYS")

        val countHoursStr = alfabetAmount(hours, "MALE")
        val hoursStr = if (countHoursStr == "") ""
        else declensionFunc(hours, "HOURS")

        val countMinutesStr = alfabetAmount(minutes, "FEMALE")
        val minutesStr = if (countMinutesStr == "") ""
        else declensionFunc(minutes, "MINUTES")

        val countSecondsStr = alfabetAmount(seconds, "FEMALE")
        val secondsStr = if (countSecondsStr == "") ""
        else declensionFunc(seconds, "SECONDS")
        //return "давным давно в далекой галактике"

        return "опубликовано$countYearsStr$yearsStr$countDaysStr$daysStr$countHoursStr$hoursStr$countMinutesStr$minutesStr$countSecondsStr$secondsStr назад"
    }

    private fun declensionFunc(amount: Int, thing: String): String {
        when (thing) {
            "YEARS" -> {
                val year = when (amount) {
                    0 -> ""
                    1 -> " год"
                    in 2..4 -> " года"
                    in 5..10 -> " лет"
                    else -> " лет"
                }
                return year
            }
            "DAYS" -> {
                val day = when (amount % 20) {
                    0 -> ""
                    1 -> " день"
                    in 2..4 -> " дня"
                    in 5..20 -> " дней"
                    /*21 -> " день"
                    in 22..24 -> " дня"
                    in 25..30 -> " дней"
                    31 -> "день"
                    32..*/
                    else -> "до фига дней"
                }
                return day
            }

            "HOURS" -> {
                val hour = when (amount % 20) {
                    0 -> ""
                    1 -> " час"
                    in 2..4 -> " часа"
                    in 5..20 -> " часов"
                    else -> "до фига часов"
                }
                return hour
            }
            "MINUTES" -> {
                val minute = when (amount % 20) {
                    0 -> ""
                    1 -> " минуту"
                    in 2..4 -> " минуты"
                    in 5..20 -> " минут"
                    else -> "до фига минут"
                }
                return minute
            }
            "SECONDS" -> {
                val second = when (amount % 10) {
                    0 -> " секунд"
                    1 -> " секунду"
                    in 2..4 -> " секунды"
                    in 5..10 -> " секунд"
                    else -> "до фига секунд"
                }
                return second
            }
        }
        return "null"
    }


    private fun alfabetAmount(amount: Int, sex: String): String {
        val secondRank = (amount / 10).toInt()
        val firstRank: Int
        val firstRankStr: String
        val secondRankStr = when (secondRank) {
            2 -> " двадцать"
            3 -> " тридцать"
            4 -> " сорок"
            5 -> " пятьдесят"
            6 -> " шестьдесят"
            7 -> " семьдесят"
            8 -> " восемьдесят"
            9 -> " девяносто"
            else -> ""
        }
        if (secondRank == 1) {
            firstRank = ((amount % 10).toInt()) + 10
        } else {
            firstRank = (amount % 10).toInt()
        }
        if ((sex == "FEMALE") && (firstRank in 1..3)) {
            firstRankStr = when (firstRank) {
                0 -> ""
                1 -> " одну"
                2 -> " две"
                3 -> " три"
                else -> ""
            }
        } else {
            firstRankStr = when (firstRank) {
                0 -> ""
                1 -> " один"
                2 -> " два"
                3 -> " три"
                4 -> " четыре"
                5 -> " пять"
                6 -> " шесть"
                7 -> " семь"
                8 -> " восемь"
                9 -> " девять"
                10 -> " десять"
                11 -> " одиннадцать"
                12 -> " двенадцать"
                13 -> " тринадцать"
                14 -> " четырнадцать"
                15 -> " пятнадцать"
                16 -> " шестнадцать"
                17 -> " семнадцать"
                18 -> " восемнадцать"
                19 -> " девятнадцать"
                else -> ""
            }
        }
        return secondRankStr + firstRankStr
    }
}