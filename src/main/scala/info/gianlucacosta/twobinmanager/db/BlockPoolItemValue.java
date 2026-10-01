package info.gianlucacosta.twobinmanager.db;


import javax.persistence.Column;
import javax.persistence.Embeddable;
import java.util.Objects;

/**
 * Item in the block pool - value object
 */
@Embeddable
public class BlockPoolItemValue {
    @Column(
            nullable = false
    )
    private int width;

    @Column(
            nullable = false
    )
    private int height;

    @Column(
            nullable = false
    )
    private int quantity;

    public BlockPoolItemValue() {
    }

    public BlockPoolItemValue(int width, int height, int quantity) {
        this.width = width;
        this.height = height;
        this.quantity = quantity;
    }

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }

    public int getQuantity() {
        return quantity;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        BlockPoolItemValue that = (BlockPoolItemValue) o;
        return width == that.width &&
                height == that.height &&
                quantity == that.quantity;
    }

    @Override
    public int hashCode() {
        return Objects.hash(width, height, quantity);
    }
}
