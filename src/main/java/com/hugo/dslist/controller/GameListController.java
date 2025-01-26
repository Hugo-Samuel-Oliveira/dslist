package com.hugo.dslist.controller;

import com.hugo.dslist.dto.GameDTO;
import com.hugo.dslist.dto.GameListDTO;
import com.hugo.dslist.dto.GameMinDTO;
import com.hugo.dslist.services.GameListService;
import com.hugo.dslist.services.GameService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(value = "/lists")
public class GameListController {

    @Autowired
    private GameListService gameListService;

    @GetMapping
    public List<GameListDTO> findAll() {
        List<GameListDTO> result = gameListService.findAll();
        return result;
    }

}
