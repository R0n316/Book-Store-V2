package ru.alex.bookstore.http.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.alex.bookstore.database.entity.Reaction;
import ru.alex.bookstore.dto.UserDto;
import ru.alex.bookstore.service.ReviewReactionService;

import static org.springframework.http.HttpStatus.OK;

@RestController
@RequestMapping("/api/reviews")
public class BookReviewRestController {

    private final ReviewReactionService reviewReactionService;

    @Autowired
    public BookReviewRestController(ReviewReactionService reviewReactionService) {
        this.reviewReactionService = reviewReactionService;
    }

    @PatchMapping("/{id}")
    public ResponseEntity<HttpStatus> respondReview(@PathVariable("id") Integer reviewId,
                                                    @AuthenticationPrincipal UserDto user,
                                                    Reaction reaction){
        // TODO ограничить доступ к этому методу в Spring Security
        reviewReactionService.respondReview(reaction,reviewId,user.id());
        return new ResponseEntity<>(OK);
    }
}
