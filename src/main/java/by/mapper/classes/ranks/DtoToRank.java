package by.mapper.classes.ranks;


import by.database.entity.Rank;
import by.dto.rank_dto.FromDtoToRank;
import by.mapper.map_interfaces.rank.DtoToRankMapper;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@NoArgsConstructor
public class DtoToRank {

    public Rank mapFrom(FromDtoToRank fromDtoToRank){
        return DtoToRankMapper.INSTANCE.dtoToRank(fromDtoToRank);
    }
}
