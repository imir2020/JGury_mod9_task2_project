package by.dto.user_dto;


import lombok.Builder;
import lombok.Value;
import org.springframework.lang.NonNull;
import org.springframework.lang.Nullable;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.multipart.MultipartFile;

@Value
@Builder
@Validated
public class FromDtoToUser {
    String name;
    String birthday;
    String password;
    String status;

    //@NonNull
    MultipartFile image;

}
