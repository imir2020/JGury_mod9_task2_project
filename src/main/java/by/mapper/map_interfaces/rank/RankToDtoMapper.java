package by.mapper.map_interfaces.rank;

import by.database.entity.Rank;
import by.dto.rank_dto.RankDto;
import by.mapper.classes.ranks.RankToDto;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface RankToDtoMapper {
    RankToDtoMapper INSTANCE = Mappers.getMapper(RankToDtoMapper.class);

    RankDto rankToDto(Rank rank);
}
