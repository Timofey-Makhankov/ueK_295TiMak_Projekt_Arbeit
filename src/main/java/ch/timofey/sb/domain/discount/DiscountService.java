package ch.timofey.sb.domain.discount;

import ch.timofey.sb.exception.DiscountNotFoundException;
import ch.timofey.sb.exception.InvalidIdPlacementException;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
@Log4j2
public class DiscountService {
    private final DiscountRepository discountRepository;

    public DiscountService(DiscountRepository discountRepository) {
        this.discountRepository = discountRepository;
    }

    public List<Discount> getAllDiscounts() {
        log.info("all discount Items has been tried to be accessed");
        return Optional.of(discountRepository.findAll()).orElseThrow(() -> new DiscountNotFoundException("No Discounts have been found"));
    }

    public Discount getDiscountById(int index) {
        log.info("discount Item has been tried to be accessed");
        return discountRepository.findById(index).orElseThrow(() -> new DiscountNotFoundException("discount with given Id could not be found"));
    }

    public void addDiscount(Discount discount) throws InvalidIdPlacementException {
        if (discount.getId() != 0) {
            throw new InvalidIdPlacementException("The Id shall not be passed in a Post Request");
        }
        discountRepository.save(discount);
        log.info("discount has been added to the DB");
    }

    public void updateDiscount(Integer index, Discount discount) throws InvalidIdPlacementException {
        if (index != null) {
            if (discount.getId() == 0) {
                throw new InvalidIdPlacementException("The Id has to be Passed to the Request");
            }
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
