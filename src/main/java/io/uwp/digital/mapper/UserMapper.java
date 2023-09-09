package io.uwp.digital.mapper;

import io.uwp.digital.dto.UserDTO;
import io.uwp.digital.entity.UserModel;
import org.mapstruct.Mapper;
import org.mapstruct.MappingConstants;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING)
public interface UserMapper {
    UserModel dtoToEntity(UserDTO userDTO);
}
