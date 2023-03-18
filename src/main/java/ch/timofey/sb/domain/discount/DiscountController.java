package ch.timofey.sb.domain.discount;

import ch.timofey.sb.exception.NotFoundException;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
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
        return ResponseEntity.ok().body(discountService.getAllDiscounts());
    }

    @GetMapping("/{discountId}")
    @ResponseStatus(code = HttpStatus.OK)
    public ResponseEntity<Discount> getDiscount(@PathVariable("discountId") Integer discountId) throws NotFoundException{
        return ResponseEntity.ok().body(discountService.getDiscountById(discountId).orElseThrow(() -> new NotFoundException("discount with given Id could not be found")));
    }

    @PostMapping()
    @ResponseStatus(code = HttpStatus.CREATED)
    public void postDiscount(@Valid @RequestBody Discount discount) {
        discountService.addDiscount(discount);
    }

    @PutMapping("/{discountId}")
    @ResponseStatus(code = HttpStatus.OK)
    public void updateDiscount(@PathVariable("discountId") Integer discountId, @RequestBody Discount discount) {
        discountService.updateDiscount(discountId, discount);
    }

    @DeleteMapping("/{discountId}")
    @ResponseStatus(code = HttpStatus.NO_CONTENT)
    public void deleteDiscount(@PathVariable("discountId") Integer discountId) {
        discountService.deleteDiscount(discountId);
    }

    @ExceptionHandler(NotFoundException.class)
    public ResponseEntity<String> handleNoSuchElementException(NotFoundException nfe){
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(nfe.getMessage());
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<String> handleMethodArgumentNotValidException(MethodArgumentNotValidException manve){
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(manve.getFieldError().getDefaultMessage());
    }
}
