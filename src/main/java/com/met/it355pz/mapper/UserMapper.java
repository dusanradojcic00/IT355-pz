package com.met.it355pz.mapper;

import com.met.it355pz.model.User;
import com.met.it355pz.payload.dto.ProfileDTO;
import com.met.it355pz.payload.dto.UserDTO;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface UserMapper {
    public UserDTO toUserDto(User user);

    public ProfileDTO toProfileDto(User user);
}
