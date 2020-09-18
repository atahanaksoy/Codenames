package com.ozaksoftware.CodeNames.service;

import com.ozaksoftware.CodeNames.DTO.mapper.PlayerMapper;
import com.ozaksoftware.CodeNames.DTO.model.PlayerDTO;
import com.ozaksoftware.CodeNames.domain.Player;
import com.ozaksoftware.CodeNames.repository.PlayerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class PlayerService {

    private final PlayerRepository playerRepository;

    @Autowired
    public PlayerService(PlayerRepository playerRepository) {
        this.playerRepository = playerRepository;
    }

    public PlayerDTO createNewPlayer(PlayerDTO playerDTO) {
        if(playerDTO == null || playerDTO.getNickName() == null || playerDTO.getNickName() == "") {
            return null;
        }
        Player newPlayer = new Player();
        newPlayer.setNickName(playerDTO.getNickName());
        playerRepository.save(newPlayer);
        playerDTO.setId(newPlayer.getId());
        return playerDTO;
    }
    public Player getPlayer(int id) {
        return playerRepository.findOneById(id);
    }
    public List<PlayerDTO> listPlayerDTOs() {
        List<Player> players = (List<Player>) playerRepository.findAll();
        return PlayerMapper.toPlayerDTOList(players);
    }
}

