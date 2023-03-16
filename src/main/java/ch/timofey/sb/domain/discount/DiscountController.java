package ch.timofey.sb.domain.discount;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/discount")
public class DiscountController {

    private final DiscountService discountService;

    public DiscountController(DiscountService discountService){
        this.discountService = discountService;
    }

    @GetMapping
    @ResponseStatus(code = HttpStatus.OK)
    public ResponseEntity<List<Discount>> getAllDiscount() {
        return ResponseEntity.ok().body(discountService.getAllDiscounts());
    }

    @GetMapping("/{discountId}")
    @ResponseStatus(code = HttpStatus.OK)
    public ResponseEntity<Discount> getDiscount(@PathVariable("discountId") Integer discountId) {
        return ResponseEntity.ok().body(discountService.getDiscountById(discountId).orElse(null));
    }

    @PostMapping()
    @ResponseStatus(code = HttpStatus.CREATED)
    public void postDiscount(@RequestBody Discount discount) {
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
}
