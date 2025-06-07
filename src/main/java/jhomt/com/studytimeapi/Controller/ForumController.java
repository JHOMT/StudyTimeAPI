package jhomt.com.studytimeapi.Controller;

import jhomt.com.studytimeapi.Domain.Forum.*;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/forums")
public class ForumController {

    private final ForumService forumService;

    public ForumController(ForumService forumService) {
        this.forumService = forumService;
    }

    @PostMapping
    public ResponseEntity<?> registerForum(@RequestBody DataRegisterForum dataRegisterForum) {
        try {
            DataListForum forum = forumService.registerForum(dataRegisterForum);
            return new ResponseEntity<>(forum, HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>("Error registering forum: " + e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    @PutMapping
    public ResponseEntity<?> updateForum(@RequestBody DataUpdateForum dataUpdateForum) {
        try {
            DataListForum updatedForum = forumService.updateForum(dataUpdateForum);
            return new ResponseEntity<>(updatedForum, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>("Error updating forum: " + e.getMessage(), HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/by-course/{courseId}")
    public ResponseEntity<?> listForums(@PathVariable Integer courseId) {
        try {
            List<DataListForum> forums = forumService.listForumsByCourseId(courseId);
            return new ResponseEntity<>(forums, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>("Error retrieving forums: " + e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
