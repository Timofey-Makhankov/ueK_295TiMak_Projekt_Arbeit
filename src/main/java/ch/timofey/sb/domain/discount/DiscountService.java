package ch.timofey.sb.domain.discount;

import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Service
public class DiscountService {

    private final List<Discount> discountList = new ArrayList<>(Arrays.asList(
            new Discount(22.78, 2, 30),
            new Discount(75.90, 1, 70),
            new Discount(18.21, 3, 50),
            new Discount(31.53, 2, 70),
            new Discount(156.08, 4, 25)
    ));
//    @Autowired
//    private DiscountRepository discountRepository;

    public List<Discount> getAllDiscounts() {
        return discountList;
    }

    public Discount getDiscountById(int index) {
        try {
            return discountList.get(index);
        } catch (IndexOutOfBoundsException e) {
            return null;
        }
    }

    public void addDiscount(Discount discount) {
        discountList.add(discount);
    }

    public void updateDiscount(int index, Discount discount) {
        try {
            discountList.set(index, discount);
        } catch (IndexOutOfBoundsException ignored) {}
    }

    public void deleteDiscount(int index){
        try {
            discountList.remove(index);
        } catch (IndexOutOfBoundsException ignored){}
    }
}
