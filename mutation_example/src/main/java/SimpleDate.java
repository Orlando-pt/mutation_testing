public class SimpleDate {
    private int year;
    private int month;
    private int day;

    /**
     * @param year  tem de ser superior a 0
     * @param month so pode ser entre 0 e 12
     * @param day   se tivermos tempo vemos a cena dos meses terem maximo de dias
     */
    public SimpleDate(int day, int month, int year) {
        if (year < 0) throw new IllegalArgumentException("Year cannot be negative");

        if (month < 0 || month > 12) throw new IllegalArgumentException("Month must be between 0-12");

        if (day < 0 || day > numberDaysMonth(month, year))
            throw new IllegalArgumentException("Day must be between 0 and the max number of days of the given month");

        this.year = year;
        this.month = month;
        this.day = day;
    }

    public int numberDaysMonth(int month, int year) {
        if (month == 1 || month == 3 || month == 5 || month == 7 || month == 8 || month == 10 || month == 12) {
            return 31;
        } else if (month == 4 || month == 6 || month == 9 || month == 11) {
            return 30;
        } else {
            if ((year % 400 == 0) || ((year % 4 == 0) && (year % 100 != 0))) {
                return 29;
            } else {
                return 28;
            }
        }
    }

    //TODO Corrigir
    public void addDays(int days) {
        int sumDays = this.day + days;
        int remainingDays = sumDays - numberDaysMonth(this.month, this.year)
        if (remainingDays < 0) {
            this.day = sumDays;
            return;
        }

        int sumMonths = this.month + 1;
        int remainingMonths = sumMonths - 12;

        if (remainingMonths < 0) {
            this.month = sumMonths;
            return;
        }

        this.year += 1;
    }

    public int getYear() {
        return year;
    }

    public int getMonth() {
        return month;
    }

    public int getDay() {
        return day;
    }
}
