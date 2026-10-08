import java.util.ArrayList;
import java.util.List;

/**
 * Активная модель: хранит данные, выполняет конвертацию
 * и уведомляет подписчиков об изменении состояния.
 */
public class LengthModel {

    public interface ModelListener {
        void onModelChanged();
    }

    private double lengthValue;
    private LengthUnit fromUnit = LengthUnit.METER;
    private LengthUnit toUnit = LengthUnit.CENTIMETER;
    private double result;
    private boolean hasData;

    private final List<ModelListener> listeners = new ArrayList<>();

    public void addListener(ModelListener listener) {
        listeners.add(listener);
    }

    private void notifyListeners() {
        for (ModelListener listener : listeners) {
            listener.onModelChanged();
        }
    }

    /**
     * Устанавливает входные данные, проверяет их и пересчитывает результат.
     */
    public void setData(double lengthValue, LengthUnit fromUnit, LengthUnit toUnit) {
        if (Double.isNaN(lengthValue) || Double.isInfinite(lengthValue)) {
            throw new IllegalArgumentException("Длина должна быть конечным числом!");
        }
        if (lengthValue < 0) {
            throw new IllegalArgumentException("Длина не может быть отрицательной!");
        }
        if (fromUnit == null || toUnit == null) {
            throw new IllegalArgumentException("Необходимо выбрать единицы измерения!");
        }

        this.lengthValue = lengthValue;
        this.fromUnit = fromUnit;
        this.toUnit = toUnit;
        this.result = convert(lengthValue, fromUnit, toUnit);
        this.hasData = true;
        notifyListeners();
    }

    private double convert(double value, LengthUnit from, LengthUnit to) {
        double meters = value * from.getMetersPerUnit();
        return meters / to.getMetersPerUnit();
    }

    public boolean hasData() {
        return hasData;
    }

    public double getLengthValue() {
        return lengthValue;
    }

    public LengthUnit getFromUnit() {
        return fromUnit;
    }

    public LengthUnit getToUnit() {
        return toUnit;
    }

    public double getResult() {
        return result;
    }

    public String getFormattedInput() {
        if (!hasData) {
            return "Данные не введены";
        }
        return fromUnit.formatWithValue(lengthValue);
    }

    public String getFormattedResult() {
        if (!hasData) {
            return "Результат: —";
        }
        return toUnit.formatWithValue(result);
    }

    /** Пример: «500 метров - 50000 сантиметров». */
    public String getFormattedConversion() {
        if (!hasData) {
            return "Данные не введены";
        }
        return getFormattedInput() + " - " + getFormattedResult();
    }
}
