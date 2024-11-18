package com.cobook.CoBook_BE.controller;

import com.cobook.CoBook_BE.model.Space;
import com.cobook.CoBook_BE.service.TestFirebaseQuery;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("testSpace")
public class SpaceController {
    Space space;

    private final TestFirebaseQuery testFirebaseQuery = new TestFirebaseQuery();

    @GetMapping("/getAllSpace")
    public ResponseEntity<List<Space>> getAllSpace() throws Exception {
        List<Space> list = testFirebaseQuery.getSpaces();
        return ResponseEntity.ok(list);
    }
}
