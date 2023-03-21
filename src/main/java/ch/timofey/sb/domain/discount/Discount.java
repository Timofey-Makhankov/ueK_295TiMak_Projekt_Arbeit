package ch.timofey.sb.domain.discount;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.*;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "discount")
public class Discount {

    private final String positiveOrZeroMessage = "Please Give a Positiv Number";
    private final String notNullMessage = "This Field is required to have a Value";
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "discount_id")
    private int id;

    @PositiveOrZero(message = positiveOrZeroMessage)
    @Column(name = "id_customer")
    private int idCustomer = 0;

    @NotNull(message = notNullMessage)
    @PositiveOrZero(message = positiveOrZeroMessage)
    @Column(name = "code")
    private int code = 0;

    @NotNull(message = notNullMessage)
    @PositiveOrZero(message = positiveOrZeroMessage)
    @Column(name = "discount_in_percent")
    private int discountInPercent = 0;
}
