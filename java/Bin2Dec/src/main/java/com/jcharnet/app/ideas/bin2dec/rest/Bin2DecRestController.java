package com.jcharnet.app.ideas.bin2dec.rest;

import com.jcharnet.app.ideas.bin2dec.engine.BinaryToDecimal;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.oauth2.server.resource.authentication.JwtAuthenticationToken;

import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import java.util.Set;
import java.util.stream.Collectors;


@RestController
@CrossOrigin(origins = "http://localhost:8080")
@RequestMapping("/binary-to-decimal")
public class Bin2DecRestController {

    private static Logger logger = LoggerFactory.getLogger(Bin2DecRestController.class);
    private BinaryToDecimal bin2dec;

    public Bin2DecRestController() {
        this.bin2dec = new BinaryToDecimal();
    }

    @GetMapping("/convert")
    @PreAuthorize("hasAuthority('Bin2Dec')")
    public Bin2DecDTO binaryToDecimal(@RequestParam(value="binaryNumber", defaultValue="0") String binaryNumber, JwtAuthenticationToken authentication) {
        logger.debug(String.format("Binary Number: [%s]", binaryNumber));
        logger.info("Token Attributes: " + authentication.getTokenAttributes().get("scp"));
        Double decimal = 0d;
        try {
        decimal  = this.bin2dec.convertBinaryToDecimal(binaryNumber);
        } catch(IllegalArgumentException ex) {
            logger.error(ex.getMessage());
            throw new ResponseStatusException(HttpStatus.NOT_ACCEPTABLE, ex.getMessage(), ex.getCause());
        }
        Bin2DecDTO bin2decDTO = new Bin2DecDTO(binaryNumber, decimal, authentication.getName());
        if (logger.isDebugEnabled()) {
            logger.debug(String.format("Converted Decimal Number [%s]", decimal));
            logger.debug(String.format("DTO Returned [%s]", bin2decDTO.toString()));

            Set<String> roles = authentication.getAuthorities().stream()
             .map(r -> r.getAuthority()).collect(Collectors.toSet());
                logger.debug("Roles: {}", roles);
        }
        
        
        

        return bin2decDTO;
    }
}
