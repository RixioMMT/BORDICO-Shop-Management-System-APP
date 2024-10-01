package org.BORDICO.Configuration;

import org.BORDICO.Model.DTO.UserDTO;
import org.BORDICO.Model.Entity.User;
import org.modelmapper.ModelMapper;
import org.modelmapper.PropertyMap;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ModelMapperConfig {

    @Bean
    public ModelMapper modelMapper() {
        ModelMapper modelMapper = new ModelMapper();

        modelMapper.addMappings(new PropertyMap<User, UserDTO>() {
            @Override
            protected void configure() {
                map().setId(source.getId());
                map().setEmail(source.getEmail());
                map().setPhone(source.getPhone());
                map().setFirstName(source.getFirstName());
                map().setLastName(source.getLastName());
                map().setProfileImageUrl(source.getProfileImageUrl());
                map().setCreatedAt(source.getCreatedAt());
                map().setUpdatedAt(source.getUpdatedAt());
            }
        });

        return modelMapper;
    }
}