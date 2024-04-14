package ma.emsi.expensebackend.entity;

import lombok.Data;
import lombok.NoArgsConstructor;
import jakarta.persistence.*;
import java.util.Date;

@Entity
@Data
@NoArgsConstructor
public class RecurringExpense {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private double amount;
    @Enumerated(EnumType.STRING)
    private Frequency frequency;
    private Date nextOccurrence;
    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;
}
