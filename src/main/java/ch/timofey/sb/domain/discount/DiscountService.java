package ch.timofey.sb.domain.discount;

import lombok.extern.log4j.Log4j2;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@Log4j2
public class DiscountService {
    Logger logger = LogManager.getLogger(DiscountService.class);
    private final DiscountRepository discountRepository;

    public DiscountService(DiscountRepository discountRepository) {
        this.discountRepository = discountRepository;
    }

    public List<Discount> getAllDiscounts() {
        log.info("all discount Items has been tried to be accessed");
        return discountRepository.findAll();
    }

    public Optional<Discount> getDiscountById(int index) {
        log.info("discount Item has been tried to be accessed");
        return discountRepository.findById(index);
    }

    public void addDiscount(Discount discount) {
        discountRepository.save(discount);
        log.info("discount has been added to the DB");
    }

    public void updateDiscount(Integer index, Discount discount) {
        if (index != null) {
            discount.setId(index);
            discountRepository.save(discount);
            log.info("Discount has been updated");
        }
    }

    public void deleteDiscount(int index) {
        discountRepository.deleteById(index);
        log.info("discount has been in DB deleted");
    }
}
