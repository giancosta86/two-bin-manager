package info.gianlucacosta.twobinmanager.db;

import javax.persistence.*;
import java.util.*;

/**
 * Problem entity
 */
@Entity
@Table(
        name = "Problems",
        indexes = @Index(
                unique = true,
                columnList = "name"
        )
)
public class ProblemEntity {
    @Id
    private UUID id;

    @Column(
            unique = true,
            nullable = false,
            length = Integer.MAX_VALUE
    )
    private String name;

    @Column(
            nullable = false
    )
    private int initialFrameWidth;

    @Column(
            nullable = false
    )
    private int initialFrameHeight;

    @Column(
            nullable = false
    )
    private int frameMode;

    @Column(
            nullable = true
    )
    private Integer timeLimitInSeconds;

    @Column(
            nullable = false
    )
    private boolean canRotateBlocks;

    @Column(
            nullable = false
    )
    private int resolution;

    @ElementCollection(
            fetch = FetchType.LAZY
    )
    @CollectionTable(
            name = "BlockPools",
            joinColumns = @JoinColumn(
                    name = "problemId",
                    nullable = false
            ),
            foreignKey = @ForeignKey(
                    foreignKeyDefinition = "foreign key (problemId) references Problems on delete cascade"
            ),
            uniqueConstraints = @UniqueConstraint(
                    columnNames = {"problemId", "width", "height"}
            )
    )
    @Column(
            nullable = false
    )
    private Set<BlockPoolItemValue> blockPoolItems;


    @ElementCollection(
            fetch = FetchType.LAZY
    )
    @OrderColumn(
            name = "colorIndex",
            nullable = false
    )
    @CollectionTable(
            name = "ColorPools",
            joinColumns = @JoinColumn(
                    name = "problemId",
                    nullable = false
            ),
            foreignKey = @ForeignKey(
                    foreignKeyDefinition = "foreign key (problemId) references Problems on delete cascade"
            ),
            uniqueConstraints = @UniqueConstraint(
                    columnNames = {"problemId", "red", "green", "blue", "opacity"}
            )
    )
    @Column(
            nullable = false
    )
    private List<ColorValue> blockColorsPool;


    public ProblemEntity() {
    }

    public ProblemEntity(
            UUID id,
            String name,

            int initialFrameWidth,
            int initialFrameHeight,
            int frameMode,

            Integer timeLimitInSeconds,
            boolean canRotateBlocks,
            int resolution,

            Set<BlockPoolItemValue> blockPoolItems,
            List<ColorValue> blockColorsPool) {
        this.id = id;
        this.name = name;
        this.initialFrameWidth = initialFrameWidth;
        this.initialFrameHeight = initialFrameHeight;
        this.frameMode = frameMode;
        this.timeLimitInSeconds = timeLimitInSeconds;
        this.canRotateBlocks = canRotateBlocks;
        this.resolution = resolution;

        this.blockPoolItems = Collections.unmodifiableSet(
                blockPoolItems
        );

        this.blockColorsPool = Collections.unmodifiableList(
                blockColorsPool
        );
    }


    public UUID getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public int getInitialFrameWidth() {
        return initialFrameWidth;
    }

    public int getInitialFrameHeight() {
        return initialFrameHeight;
    }

    public int getFrameMode() {
        return frameMode;
    }

    public Integer getTimeLimitInSeconds() {
        return timeLimitInSeconds;
    }

    public boolean isCanRotateBlocks() {
        return canRotateBlocks;
    }

    public int getResolution() {
        return resolution;
    }

    public Set<BlockPoolItemValue> getBlockPoolItems() {
        return blockPoolItems;
    }

    public List<ColorValue> getBlockColorsPool() {
        return blockColorsPool;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ProblemEntity that = (ProblemEntity) o;
        return initialFrameWidth == that.initialFrameWidth &&
                initialFrameHeight == that.initialFrameHeight &&
                frameMode == that.frameMode &&
                canRotateBlocks == that.canRotateBlocks &&
                resolution == that.resolution &&
                Objects.equals(id, that.id) &&
                Objects.equals(name, that.name) &&
                Objects.equals(timeLimitInSeconds, that.timeLimitInSeconds) &&
                Objects.equals(blockPoolItems, that.blockPoolItems) &&
                Objects.equals(blockColorsPool, that.blockColorsPool);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, initialFrameWidth, initialFrameHeight, frameMode, timeLimitInSeconds, canRotateBlocks, resolution, blockPoolItems, blockColorsPool);
    }
}
