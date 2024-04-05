package by.mapper.classes.ranks;


import by.database.entity.Rank;
import by.dto.rank_dto.RankDto;
import by.mapper.map_interfaces.rank.RankToDtoMapper;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@NoArgsConstructor
public class RankToDto {

    public RankDto mapFrom(Rank rank){
        return RankToDtoMapper.INSTANCE.rankToDto(rank);
    }
}
