package ch.timofey.sb.domain.discount;

import ch.timofey.sb.exception.DiscountNotFoundException;
import ch.timofey.sb.exception.InvalidIdPlacementException;
import io.swagger.v3.oas.annotations.Operation;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Objects;

@RestController
@Log4j2
@RequestMapping("/api/discount")
public class DiscountController {

    private final DiscountService discountService;

    public DiscountController(DiscountService discountService) {
        this.discountService = discountService;
    }

    @GetMapping
    @Operation(summary = "Fetches all discounts", description = "lorem Ipsum")
    @ResponseStatus(code = HttpStatus.OK)
    public ResponseEntity<List<Discount>> getAllDiscount() {
        return ResponseEntity.ok().body(discountService.getAllDiscounts().orElseThrow(() -> new DiscountNotFoundException("No Discounts have been found")));
    }

    @GetMapping("/{discountId}")
    @ResponseStatus(code = HttpStatus.OK)
    public ResponseEntity<Discount> getDiscount(@PathVariable("discountId") Integer discountId) throws DiscountNotFoundException{
        return ResponseEntity.ok().body(discountService.getDiscountById(discountId).orElseThrow(() -> new DiscountNotFoundException("discount with given Id could not be found")));
    }

    @PostMapping()
    @ResponseStatus(code = HttpStatus.CREATED)
    public void postDiscount(@Valid @RequestBody Discount discount) throws InvalidIdPlacementException {
        if (discount.getId() != 0){
            throw new InvalidIdPlacementException("The Id shall not be passed in a Post Request");
        }
        discountService.addDiscount(discount);
    }

    @PutMapping("/{discountId}")
    @ResponseStatus(code = HttpStatus.OK)
    public void updateDiscount(@Valid @PathVariable("discountId") Integer discountId, @RequestBody Discount discount) throws InvalidIdPlacementException {
        if (discount.getId() == 0){
            throw new InvalidIdPlacementException("The Id has to be Passed to the Request");
        }
        discountService.updateDiscount(discountId, discount);
    }

    @DeleteMapping("/{discountId}")
    @ResponseStatus(code = HttpStatus.NO_CONTENT)
    public void deleteDiscount(@Valid @PathVariable("discountId") Integer discountId) {
        discountService.deleteDiscount(discountId);
    }

    @ExceptionHandler(DiscountNotFoundException.class)
    public ResponseEntity<String> handleNoSuchElementException(DiscountNotFoundException nfe){
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(nfe.getMessage());
    }

    @ExceptionHandler(InvalidIdPlacementException.class)
    public ResponseEntity<String> handleNoSuchElementException(InvalidIdPlacementException iipe){
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(iipe.getMessage());
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<String> handleMethodArgumentNotValidException(MethodArgumentNotValidException manve){
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(Objects.requireNonNull(manve.getFieldError()).getDefaultMessage());
    }
}
