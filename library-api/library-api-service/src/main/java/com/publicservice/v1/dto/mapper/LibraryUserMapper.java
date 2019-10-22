package com.publicservice.v1.dto.mapper;

import com.publicservice.entities.LibraryUser;
import com.publicservice.v1.dto.model.LibraryUserDto;
import org.mapstruct.InjectionStrategy;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring", injectionStrategy = InjectionStrategy.CONSTRUCTOR)
public interface LibraryUserMapper {

  LibraryUserDto toUserDto(LibraryUser libraryUser);

  LibraryUser toUser(LibraryUserDto libraryUserDto);
}
