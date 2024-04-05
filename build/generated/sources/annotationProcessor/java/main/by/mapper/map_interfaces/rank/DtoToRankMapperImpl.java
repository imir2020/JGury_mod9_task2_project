package by.mapper.map_interfaces.rank;

import by.database.entity.Rank;
import by.dto.rank_dto.FromDtoToRank;
import javax.annotation.processing.Generated;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2024-04-05T19:10:53+0300",
    comments = "version: 1.5.3.Final, compiler: IncrementalProcessingEnvironment from gradle-language-java-7.6.jar, environment: Java 17.0.8 (Oracle Corporation)"
)
public class DtoToRankMapperImpl implements DtoToRankMapper {

    @Override
    public Rank dtoToRank(FromDtoToRank fromDtoToRank) {
        if ( fromDtoToRank == null ) {
            return null;
        }

        Rank.RankBuilder rank = Rank.builder();

        rank.rankName( fromDtoToRank.getRankName() );
        rank.salary( fromDtoToRank.getSalary() );

        return rank.build();
    }
}
