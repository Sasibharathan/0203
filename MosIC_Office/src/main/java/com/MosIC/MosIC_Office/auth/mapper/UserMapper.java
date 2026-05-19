package com.MosIC.MosIC_Office.auth.mapper;

import com.MosIC.MosIC_Office.auth.dto.RegisterRequest;
import com.MosIC.MosIC_Office.auth.dto.UserDTO;
import com.MosIC.MosIC_Office.auth.entity.UserEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring")
public interface UserMapper {

    // UserEntity → UserDTO (used for getAllUsers)
    UserDTO toUserDTO(UserEntity userEntity);

    // List<UserEntity> → List<UserDTO>
    List<UserDTO> toUserDTOs(List<UserEntity> userEntities);

    // RegisterRequest → UserEntity (id and password handled manually)
    @Mapping(target = "id",       ignore = true)
    @Mapping(target = "password", ignore = true)
    UserEntity toUserEntity(RegisterRequest registerRequest);
}
