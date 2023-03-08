package com.gihub.shalhlad.musicstreamingservice.mapper;

import com.gihub.shalhlad.musicstreamingservice.entity.user.Role;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper
public interface RoleMapper {
    default List<String> toStringList(List<Role> roles) {
        return roles.stream().map(Role::name).toList();
    }
}
