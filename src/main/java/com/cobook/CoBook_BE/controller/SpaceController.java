package com.cobook.CoBook_BE.controller;

import com.cobook.CoBook_BE.model.*;
import com.cobook.CoBook_BE.service.TestFirebaseQuery;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

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

    @PostMapping
    public ResponseEntity<Map<String, Object>> receiveData(@RequestBody Map<String, Object> data) {
        // Flask 서버에서 보낸 데이터를 출력
        System.out.println("Received data from Flask server: " + data);

        // 데이터를 처리한 뒤 응답 생성 (필요에 따라 처리 로직 추가)
        return ResponseEntity.ok(Map.of(
                "status", "success",
                "receivedData", data,
                "message", "Data processed successfully in Spring"
        ));
    }
}