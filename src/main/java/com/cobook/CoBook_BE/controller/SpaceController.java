package com.cobook.CoBook_BE.controller;

import com.cobook.CoBook_BE.model.*;
import com.cobook.CoBook_BE.service.TestFirebaseQuery;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
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

    @GetMapping("/findSpace/{sid}")
    public ResponseEntity<List<Space>> findSpace(@PathVariable String sid) throws Exception {
        List<Space> space = new ArrayList<>();
        space.add(testFirebaseQuery.getSpace(sid));
        return ResponseEntity.ok(space);
    }

    @GetMapping("/getSpaceMember/{sid}")
    public ResponseEntity<List<SpaceMember>> getSpaceMember(@PathVariable String sid) throws Exception {
        List<SpaceMember> list = testFirebaseQuery.getSpaceMembers(sid);
        return ResponseEntity.ok(list);
    }

    @GetMapping("/getReceipts/{sid}")
    public ResponseEntity<List<Receipt>> getReceipts(@PathVariable String sid) throws Exception {
        List<Receipt> list = testFirebaseQuery.getReceipts(sid);
        return ResponseEntity.ok(list);
    }
}
