package com.proyecto.web.controller;

import com.proyecto.web.security.JwtTokenUtil;
import com.proyecto.web.service.SendFactExternoService;
import com.proyecto.web.service.UserJwtServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by jonat on 21/02/2020.
 */
@RestController
public class SendFactExternoController {

    @Value("${jwt.header}")
    private String tokenHeader;

    @Autowired
    private JwtTokenUtil jwtTokenUtil;

    @Autowired
    private UserJwtServiceImpl userJwtService;

    @Autowired
    private SendFactExternoService sendFactExternoService;

}
