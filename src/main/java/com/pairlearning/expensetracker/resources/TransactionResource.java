package com.pairlearning.expensetracker.resources;

import com.pairlearning.expensetracker.domain.Transaction;
import com.pairlearning.expensetracker.services.TransactionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Map;

import static com.pairlearning.expensetracker.helper.Helper.getSuccessBoolMapResponse;
import static com.pairlearning.expensetracker.helper.Helper.getUserId;

@SuppressWarnings("unused")
@RestController
@RequestMapping("/api/categories/{categoryId}/transactions")
public class TransactionResource {

    @Autowired
    TransactionService transactionService;

    @GetMapping("")
    public ResponseEntity<List<Transaction>> getAllTransactions(HttpServletRequest request, @PathVariable("categoryId") Integer categoryId) {
        List<Transaction> transactions = transactionService.fetchAllTransactions(getUserId(request), categoryId);
        return new ResponseEntity<>(transactions, HttpStatus.OK);
    }


    @GetMapping("{transactionId}")
    public ResponseEntity<Transaction> getTransactionById(HttpServletRequest request,
                                                          @PathVariable("categoryId") Integer categoryId,
                                                          @PathVariable("transactionId") Integer transactionId) {
        Transaction transaction = transactionService.fetchTransactionById(getUserId(request), categoryId, transactionId);
        return new ResponseEntity<>(transaction, HttpStatus.OK);
    }

    @PostMapping("")
    public ResponseEntity<Transaction> addTransaction(HttpServletRequest request,
                                                      @PathVariable("categoryId") Integer categoryId,
                                                      @RequestBody Map<String, Object> transactionMap) {
        Double amount = Double.valueOf(transactionMap.get("amount").toString());
        String note = (String) transactionMap.get("note");
        Long transactionDate = (Long) transactionMap.get("transactionDate");
        Transaction transaction = transactionService.addTransaction(getUserId(request), categoryId, amount, note, transactionDate);
        return new ResponseEntity<>(transaction, HttpStatus.CREATED);
    }

    /**
     * TODO: FIX FOR WRONG CATEGORY NUMBER
     */
    @PutMapping("/{transactionId}")
    public ResponseEntity<Map<String, Boolean>> updateTransaction(HttpServletRequest request,
                                                                  @PathVariable("categoryId") Integer categoryId,
                                                                  @PathVariable("transactionId") Integer transactionId,
                                                                  @RequestBody Transaction transaction) {
        transactionService.updateTransaction(getUserId(request), categoryId, transactionId, transaction);
        return getSuccessBoolMapResponse();
    }



    @DeleteMapping("/{transactionId}")
    public ResponseEntity<Map<String, Boolean>> deleteTransaction(HttpServletRequest request,
                                                                  @PathVariable("categoryId") Integer categoryId,
                                                                  @PathVariable("transactionId") Integer transactionId) {

        transactionService.removeTransaction(getUserId(request), categoryId, transactionId);
        return getSuccessBoolMapResponse();
    }

}
