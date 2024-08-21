package com.example.cashcard.controller;

import com.example.cashcard.CashCard;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/*
 Tells Spring that this class is a
 Component of type RestController and
 capable of handling HTTP requests.
 */
@RestController
/*
 Configured to listen for and handle
 HTTP requests to /cashcards
 */
@RequestMapping("/cashcards")
public class CashCardController {

    @GetMapping("/{requestedId}")
    /*
     * @PathVariable makes Spring Web aware of
     * the requestedId supplied in the HTTP request
     * */
    private ResponseEntity<CashCard> findById(@PathVariable Long requestedId) {
        if (requestedId.equals(99L)) {
            CashCard cashCard = new CashCard(99L, 123.45);
            return ResponseEntity.ok(cashCard);
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}
