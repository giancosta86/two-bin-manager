package info.gianlucacosta.twobinmanager.db;

import javax.persistence.*;
import java.util.Collections;
import java.util.Objects;
import java.util.Set;
import java.util.UUID;

/**
 * Solution entity
 */
@Entity
@Table(
        name = "Solutions",
        indexes = @Index(
                unique = false,
                columnList = "problemId"
        )
)
public class SolutionEntity {
    @Id
    private UUID id;

    @ManyToOne(
            fetch = FetchType.LAZY,
            optional = false
    )
    @JoinColumn(
            name = "problemId",
            nullable = false,
            foreignKey = @ForeignKey(
                    foreignKeyDefinition = "foreign key (problemId) references Problems on delete cascade"
            )
    )
    private ProblemEntity problem;


    @Column(
            nullable = true,
            length = Integer.MAX_VALUE
    )
    private String solver;


    @Column(
            nullable = true
    )
    private Integer elapsedTimeInSeconds;


    @ElementCollection(
            fetch = FetchType.LAZY
    )
    @CollectionTable(
            name = "Solutions_Blocks",
            joinColumns = @JoinColumn(
                    name = "solutionId",
                    nullable = false
            ),
            foreignKey = @ForeignKey(
                    foreignKeyDefinition = "foreign key (solutionId) references Solutions on delete cascade"
            ),
            uniqueConstraints = @UniqueConstraint(
                    columnNames = {"solutionId", "anchorLeft", "anchorTop"}
            )
    )
    @Column(
            nullable = false
    )
    private Set<AnchoredBlockValue> blocks;


    public SolutionEntity() {
    }

    public SolutionEntity(UUID id, ProblemEntity problem, String solver, Integer elapsedTimeInSeconds, Set<AnchoredBlockValue> blocks) {
        this.id = id;
        this.problem = problem;

        this.solver = solver;
        this.elapsedTimeInSeconds = elapsedTimeInSeconds;

        this.blocks = Collections.unmodifiableSet(
                blocks
        );
    }

    public UUID getId() {
        return id;
    }

    public ProblemEntity getProblem() {
        return problem;
    }

    void setProblem(ProblemEntity problem) {
        this.problem = problem;
    }

    public String getSolver() {
        return solver;
    }

    public Integer getElapsedTimeInSeconds() {
        return elapsedTimeInSeconds;
    }

    public Set<AnchoredBlockValue> getBlocks() {
        return blocks;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        SolutionEntity that = (SolutionEntity) o;
        return Objects.equals(id, that.id) &&
                Objects.equals(problem, that.problem) &&
                Objects.equals(solver, that.solver) &&
                Objects.equals(elapsedTimeInSeconds, that.elapsedTimeInSeconds) &&
                Objects.equals(blocks, that.blocks);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, problem, solver, elapsedTimeInSeconds, blocks);
    }
}
