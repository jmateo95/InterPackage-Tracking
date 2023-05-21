package com.interpackage.tracking.controller;
import com.interpackage.tracking.aspect.RequiredRole;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;


@RestController
@RequestMapping("/api/tracking/v1")
public class Hola {
    @Autowired
    private HttpServletRequest request;


    @GetMapping("/hola/{name}")
    @RequiredRole({"Admin","Client"})
    public ResponseEntity<Object> getHola(@PathVariable String name) {
        return ResponseEntity.ok(
                new HashMap<String, Object>() {{
                    put("mensaje", "Hola " + name);
                    put("estado", HttpStatus.OK.value());
                }}
        );
    }

    @GetMapping("/adios/{name}")
    @RequiredRole({"Admin", "role test"})
    public ResponseEntity<Object> getAdios(@PathVariable String name) {
        return ResponseEntity.ok(
                new HashMap<String, Object>() {{
                    put("mensaje", "Adios " + name);
                    put("estado", HttpStatus.OK.value());
                }}
        );
    }
}
