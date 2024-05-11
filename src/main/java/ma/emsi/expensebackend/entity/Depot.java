package ma.emsi.expensebackend.entity;

import lombok.Data;
import lombok.NoArgsConstructor;
import jakarta.persistence.*;
import java.time.LocalDate;

@Entity
@Data
@NoArgsConstructor
public class Depot {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "date_depot")
    private LocalDate dateDepot;

    private double montant;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

}


