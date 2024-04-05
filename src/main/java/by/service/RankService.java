package by.service;


import by.database.repository.RankRepository;
import by.dto.rank_dto.RankDto;
import by.mapper.classes.ranks.DtoToRank;
import by.mapper.classes.ranks.RankToDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class RankService {
    private  final RankRepository rankRepository;
    private final RankToDto rankToDto;
    private final DtoToRank dtoToRank;

    public List<RankDto> findAll(){
        return rankRepository.findAllBy().stream().map(rankToDto::mapFrom).toList();
    }

}
