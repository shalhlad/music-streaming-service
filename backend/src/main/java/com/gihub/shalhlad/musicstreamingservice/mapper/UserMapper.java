package com.gihub.shalhlad.musicstreamingservice.mapper;

import com.gihub.shalhlad.musicstreamingservice.dto.response.UserDto;
import com.gihub.shalhlad.musicstreamingservice.entity.user.User;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;

import java.util.Date;

@Mapper
public interface UserMapper {
    @Mapping(target = "birthday", expression = "java(fromDateToLong(user.getBirthday()))")
    @Mapping(target = "registrationDate", expression = "java(fromDateToLong(user.getRegistrationDate()))")
    UserDto toUserDto(User user);

    @Named("fromDateToLong")
    default Long fromDateToLong(Date date) {
        return date == null ? null : date.getTime();
    }
}
