public class SimpleDate {

    private int year;
    private int month;
    private int day;

    /**
     *
     * @param year tem de ser superior a 0
     * @param month so pode ser entre 0 e 12
     * @param day se tivermos tempo vemos a cena dos meses terem maximo de dias
     */

    public SimpleDate(int day, int month, int year) {
        this.year = year;
        this.month = month;
        this.day = day;
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
