package info.gianlucacosta.twobinmanager.db;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import java.util.Objects;

/**
 * Color - value object
 */
@Embeddable
public class ColorValue {
    @Column(
            nullable = false
    )
    private double red;

    @Column(
            nullable = false
    )
    private double green;

    @Column(
            nullable = false
    )
    private double blue;

    @Column(
            nullable = false
    )
    private double opacity;


    public ColorValue() {
    }

    public ColorValue(double red, double green, double blue, double opacity) {
        this.red = red;
        this.green = green;
        this.blue = blue;
        this.opacity = opacity;
    }

    public double getRed() {
        return red;
    }

    public double getGreen() {
        return green;
    }

    public double getBlue() {
        return blue;
    }

    public double getOpacity() {
        return opacity;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ColorValue that = (ColorValue) o;
        return Double.compare(that.red, red) == 0 &&
                Double.compare(that.green, green) == 0 &&
                Double.compare(that.blue, blue) == 0 &&
                Double.compare(that.opacity, opacity) == 0;
    }

    @Override
    public int hashCode() {
        return Objects.hash(red, green, blue, opacity);
    }
}
