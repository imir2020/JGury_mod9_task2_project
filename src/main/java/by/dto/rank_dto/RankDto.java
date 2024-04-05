package by.dto.rank_dto;

import by.database.repository.Greid;
import lombok.Builder;
import lombok.Value;


@Value
@Builder
public class RankDto {
    Long id;
    Greid rankName;
    Long salary;
}
