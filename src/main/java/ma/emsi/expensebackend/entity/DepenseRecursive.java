package ma.emsi.expensebackend.entity;

import lombok.Data;
import lombok.NoArgsConstructor;
import jakarta.persistence.*;

import java.time.LocalDate;

@Entity
@Data
@NoArgsConstructor
public class DepenseRecursive {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private double montant;

    @Enumerated(EnumType.STRING)
    private Frequence frequence;

    @Column(name = "date_prochaine_occurrence")
    private LocalDate dateProchaineOccurrence;

    // Getters and setters
}
