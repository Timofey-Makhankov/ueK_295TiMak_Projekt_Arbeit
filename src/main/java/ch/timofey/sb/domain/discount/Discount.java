package ch.timofey.sb.domain.discount;

import lombok.Getter;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
@Getter
public class Discount {
    @Id
    @GeneratedValue
    private int id;
    private double discount = 0;
    private int code = 0;
    private int discountInPercent = 0;

    public Discount(double discount, int code, int discountInPercent){
        ++id;
        this.discount = discount;
        this.code = code;
        this.discountInPercent = discountInPercent;
    }

    public Discount() {

    }
}
