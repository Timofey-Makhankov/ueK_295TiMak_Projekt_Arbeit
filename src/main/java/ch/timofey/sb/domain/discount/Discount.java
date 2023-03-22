package ch.timofey.sb.domain.discount;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.PositiveOrZero;

@Entity
@Data
@NoArgsConstructor
@Table(name = "discount")
public class Discount {

    @Transient
    private static final String POSITIVE_OR_ZERO_MESSAGE = "Please Give a Positiv Number";

    @Transient
    private static final String NOT_NULL_MESSAGE = "This Field is required to have a Value";

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "discount_id")
    private int id;

    @PositiveOrZero(message = POSITIVE_OR_ZERO_MESSAGE)
    @Column(name = "id_customer")
    private int idCustomer = 0;

    @NotNull(message = NOT_NULL_MESSAGE)
    @PositiveOrZero(message = POSITIVE_OR_ZERO_MESSAGE)
    @Column(name = "code")
    private int code = 0;

    @NotNull(message = NOT_NULL_MESSAGE)
    @PositiveOrZero(message = POSITIVE_OR_ZERO_MESSAGE)
    @Column(name = "discount_in_percent")
    private int discountInPercent = 0;
}
