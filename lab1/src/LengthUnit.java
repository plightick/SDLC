/**
 * Единицы измерения длины для утилиты «Высота и длина».
 * Эталон — метр; остальные величины заданы через коэффициент к метру.
 * Для вывода результата хранятся русские формы: 1 метр, 2 метра, 5 метров.
 */
public enum LengthUnit {
    INCH("Дюйм", "дюйм", "дюйма", "дюймов", 0.0254),
    YARD("Ярд", "ярд", "ярда", "ярдов", 0.9144),
    CENTIMETER("Сантиметр", "сантиметр", "сантиметра", "сантиметров", 0.01),
    METER("Метр", "метр", "метра", "метров", 1.0),
    AMERICAN_COCKROACH("Американский таракан", "американский таракан", "американских таракана", "американских тараканов", 0.04),
    GIRAFFE_NECK("Шея жирафа", "шея жирафа", "шеи жирафа", "шей жирафа", 2.4),
    LONGEST_SNAKE("Самая длинная змея", "самая длинная змея", "самые длинные змеи", "самых длинных змей", 10.0),
    HUMAN_TONGUE("Человеческий язык", "человеческий язык", "человеческих языка", "человеческих языков", 0.1),
    FOOTBALL_FIELD("Футбольное поле", "футбольное поле", "футбольных поля", "футбольных полей", 105.0);

    private final String displayName;
    private final String one;
    private final String few;
    private final String many;
    private final double metersPerUnit;

    LengthUnit(String displayName, String one, String few, String many, double metersPerUnit) {
        this.displayName = displayName;
        this.one = one;
        this.few = few;
        this.many = many;
        this.metersPerUnit = metersPerUnit;
    }

    public String getDisplayName() {
        return displayName;
    }

    public double getMetersPerUnit() {
        return metersPerUnit;
    }

    /**
     * Подбирает форму слова по русским правилам: 1 метр, 2 метра, 5 метров.
     */
    public String formatWithValue(double value) {
        return formatNumber(value) + " " + chooseForm(value);
    }

    private String chooseForm(double value) {
        if (value != Math.rint(value) || Double.isInfinite(value) || Double.isNaN(value)) {
            return few;
        }
        long n = Math.abs(Math.round(value));
        long lastTwo = n % 100;
        long last = n % 10;
        if (lastTwo >= 11 && lastTwo <= 14) {
            return many;
        }
        if (last == 1) {
            return one;
        }
        if (last >= 2 && last <= 4) {
            return few;
        }
        return many;
    }

    private static String formatNumber(double value) {
        if (value == Math.rint(value) && !Double.isInfinite(value)) {
            return String.valueOf(Math.round(value));
        }
        String text = String.format(java.util.Locale.US, "%.6f", value);
        int end = text.length();
        while (end > 0 && text.charAt(end - 1) == '0') {
            end--;
        }
        if (end > 0 && text.charAt(end - 1) == '.') {
            end--;
        }
        return text.substring(0, end);
    }

    @Override
    public String toString() {
        return displayName;
    }

    public static LengthUnit fromDisplayName(String name) {
        for (LengthUnit unit : values()) {
            if (unit.displayName.equals(name)) {
                return unit;
            }
        }
        throw new IllegalArgumentException("Неизвестная единица измерения: " + name);
    }
}
