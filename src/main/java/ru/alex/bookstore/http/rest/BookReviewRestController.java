package ru.alex.bookstore.http.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;
import ru.alex.bookstore.database.entity.Reaction;
import ru.alex.bookstore.dto.UserDto;
import ru.alex.bookstore.service.BookReviewService;
import ru.alex.bookstore.service.ReviewReactionService;

import static org.springframework.http.HttpStatus.OK;

@RestController
@RequestMapping("/api/reviews")
public class BookReviewRestController {

    private final ReviewReactionService reviewReactionService;
    private final BookReviewService bookReviewService;

    @Autowired
    public BookReviewRestController(ReviewReactionService reviewReactionService,
                                    BookReviewService bookReviewService) {
        this.reviewReactionService = reviewReactionService;
        this.bookReviewService = bookReviewService;
    }

    @PatchMapping("/{id}")
    public ResponseEntity<HttpStatus> respondReview(@PathVariable("id") Integer reviewId,
                                                    @AuthenticationPrincipal UserDto user,
                                                    @RequestBody String reaction){
        // TODO ограничить доступ к этому методу в Spring Security
        reviewReactionService.respondToReview(Reaction.valueOf(reaction),reviewId,user.id());
        return new ResponseEntity<>(OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<HttpStatus> deleteReview(@PathVariable("id") Integer reviewId, @AuthenticationPrincipal UserDto user){
        bookReviewService.deleteReview(reviewId,user.id());
        return new ResponseEntity<>(OK);
    }
}