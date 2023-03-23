package ch.timofey.sb.domain.discount;

import ch.timofey.sb.exception.DiscountNotFoundException;
import ch.timofey.sb.exception.InvalidIdPlacementException;
import io.swagger.v3.oas.annotations.Operation;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Objects;

@RestController
@Log4j2
@RequestMapping("/api/discounts")
public class DiscountController {

    private final DiscountService discountService;

    public DiscountController(DiscountService discountService) {
        this.discountService = discountService;
    }

    @GetMapping
    @PreAuthorize("hasAuthority('READ')")
    @Operation(summary = "Fetches all discounts in the Database", description = "This Request Method will get all the Discounts on The Database if available. Otherwise it will return a 404 Code. to access the API, you need to have the Role <USER>")
    @ResponseStatus(code = HttpStatus.OK)
    public ResponseEntity<List<Discount>> getAllDiscount() {
        return ResponseEntity.ok().body(discountService.getAllDiscounts());
    }

    @GetMapping("/{discountId}")
    @PreAuthorize("hasAuthority('READ')")
    @Operation(summary = "Fetches a discount in the Database with a given id", description = "This Request Method will get a given Discount on the Database with the given id in the URL. Otherwise it will return a 404 Code. to access the API, you need to have the Role <USER>")
    @ResponseStatus(code = HttpStatus.OK)
    public ResponseEntity<Discount> getDiscount(@PathVariable("discountId") Integer discountId) throws DiscountNotFoundException {
        return ResponseEntity.ok().body(discountService.getDiscountById(discountId));
    }

    @PostMapping()
    @PreAuthorize("hasAuthority('CREATE')")
    @Operation(summary = "Creates a discount and saves it to the database", description = "This Request Method will create a discount model to the Database *without* giving an id, Otherwise it will return a 401 Code If Id is given. To access the API, you need to have the Role <ADMIN>")
    @ResponseStatus(code = HttpStatus.CREATED)
    public void postDiscount(@Valid @RequestBody Discount discount) throws InvalidIdPlacementException {
        discountService.addDiscount(discount);
    }

    @PutMapping("/{discountId}")
    @PreAuthorize("hasAuthority('UPDATE')")
    @Operation(summary = "Updates a discount in the Database with a given id", description = "This Request Method will update a discount model in the Database with the given Discount Id. Otherwise it will return a 401 Code If Id is NOT given. To access the API, you need to have the Role <ADMIN>")
    @ResponseStatus(code = HttpStatus.OK)
    public void updateDiscount(@Valid @PathVariable("discountId") Integer discountId, @RequestBody Discount discount) throws InvalidIdPlacementException {
        discountService.updateDiscount(discountId, discount);
    }

    @DeleteMapping("/{discountId}")
    @PreAuthorize("hasAuthority('DELETE')")
    @Operation(summary = "Deletes a given discount in the database with a given Id", description = "This Request Method will delete a discount in the Database, if it is in the database. Otherwise it will return the code 401. To access the API, you need to have the Role <ADMIN>")
    @ResponseStatus(code = HttpStatus.NO_CONTENT)
    public void deleteDiscount(@Valid @PathVariable("discountId") Integer discountId) {
        discountService.deleteDiscount(discountId);
    }

    @ExceptionHandler(DiscountNotFoundException.class)
    public ResponseEntity<String> handleNoSuchElementException(DiscountNotFoundException dnfe) {
        log.error("The given Discount was not found in the database");
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(dnfe.getMessage());
    }

    @ExceptionHandler(InvalidIdPlacementException.class)
    public ResponseEntity<String> handleNoSuchElementException(InvalidIdPlacementException iipe) {
        log.error("The Id of Model was not placed correctly to a specific HTTP Method");
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(iipe.getMessage());
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<String> handleMethodArgumentNotValidException(MethodArgumentNotValidException manve) {
        log.error("The given values of the Model has failed the requirements");
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(Objects.requireNonNull(manve.getFieldError()).getDefaultMessage());
    }
}
