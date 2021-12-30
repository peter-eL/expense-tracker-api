package com.pairlearning.expensetracker.helper;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;

public final class Helper {

    private Helper() {
    }

    public static int getUserId(HttpServletRequest request) {
        return (int) request.getAttribute("userId");
    }

    public static ResponseEntity<Map<String, Boolean>> getSuccessBoolMapResponse() {
        Map<String, Boolean> map = new HashMap<>();
        map.put("success", true);
        return new ResponseEntity<>(map, HttpStatus.OK);
    }

}
