package ch.timofey.sb.domain.discount;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/discount")
public class DiscountController {

    @Autowired private DiscountService discountService;

    @GetMapping
    public ResponseEntity<List<Discount>> getAllDiscount(){
        return ResponseEntity.ok().body(discountService.getAllDiscounts());
    }

    @GetMapping("/{discountId}")
    public ResponseEntity<Discount> getDiscount(@PathVariable("discountId") Integer discountId){
        return ResponseEntity.ok().body(discountService.getDiscountById(discountId));
    }

    @PostMapping()
    @ResponseStatus(code = HttpStatus.CREATED)
    public void postDiscount(@RequestBody Discount discount){
        discountService.addDiscount(discount);
    }

    @PutMapping("/{discountId}")
    @ResponseStatus(code = HttpStatus.ACCEPTED)
    public void updateDiscount(@PathVariable("discountId") Integer discountId, @RequestBody Discount discount){
        discountService.updateDiscount(discountId, discount);
    }

    @DeleteMapping("/{discountId}")
    @ResponseStatus(code = HttpStatus.ACCEPTED)
    public void deleteDiscount(@PathVariable("discountId") Integer discountId){
        discountService.deleteDiscount(discountId);
    }
}
