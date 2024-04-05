package by.mapper.map_interfaces.rank;


import by.database.entity.Rank;
import by.dto.rank_dto.FromDtoToRank;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface DtoToRankMapper {
    DtoToRankMapper INSTANCE = Mappers.getMapper(DtoToRankMapper.class);

    Rank dtoToRank(FromDtoToRank fromDtoToRank);
}
