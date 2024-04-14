package ma.emsi.expensebackend.entity;

import lombok.Data;
import lombok.NoArgsConstructor;
import jakarta.persistence.*;

@Entity
@Data
@NoArgsConstructor
public class Budget {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @ManyToOne
    @JoinColumn(name = "category_id")
    private Category category;
    private double amount;
    private int month;
    private int year;
    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;
}
