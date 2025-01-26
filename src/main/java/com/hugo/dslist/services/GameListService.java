package com.hugo.dslist.services;

import com.hugo.dslist.dto.GameDTO;
import com.hugo.dslist.dto.GameListDTO;
import com.hugo.dslist.dto.GameMinDTO;
import com.hugo.dslist.entities.Game;
import com.hugo.dslist.entities.GameList;
import com.hugo.dslist.repositories.GameListRepository;
import com.hugo.dslist.repositories.GameRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class GameListService {

    @Autowired
    private GameListRepository gameListRepository;


    @Transactional(readOnly = true)
    public List<GameListDTO> findAll() {
        List<GameList> result = gameListRepository.findAll();
        return result.stream().map(x -> new GameListDTO(x)).toList();
    }
}
