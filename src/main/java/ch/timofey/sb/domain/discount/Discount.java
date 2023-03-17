package ch.timofey.sb.domain.discount;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Table(name = "discount")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Discount {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "discount_id")
    private int id;

    @Column(name = "id_book")
    private int idBook = 0;

    @Column(name = "code")
    private int code = 0;
    @Column(name = "discount_in_percent")
    private int discountInPercent = 0;
}
