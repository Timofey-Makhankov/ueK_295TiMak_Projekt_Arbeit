package ch.timofey.sb.domain.discount;

import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class DiscountService {
    private final DiscountRepository discountRepository;

    public DiscountService(DiscountRepository discountRepository) {
        this.discountRepository = discountRepository;
    }

    public List<Discount> getAllDiscounts() {
        return discountRepository.findAll();
    }

    public Optional<Discount> getDiscountById(int index) {
        return discountRepository.findById(index);
    }

    public void addDiscount(Discount discount) {
        discountRepository.save(discount);
    }

    public void updateDiscount(int index, Discount discount) {
        discount.setId(index);
        discountRepository.save(discount);
    }

    public void deleteDiscount(int index) {
        discountRepository.deleteById(index);
    }
}
