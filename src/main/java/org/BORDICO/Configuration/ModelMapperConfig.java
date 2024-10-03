package org.BORDICO.Configuration;

import org.BORDICO.Model.DTO.MaterialDTO;
import org.BORDICO.Model.DTO.UserDTO;
import org.BORDICO.Model.Entity.Material;
import org.BORDICO.Model.Entity.Role;
import org.BORDICO.Model.Entity.User;
import org.modelmapper.ModelMapper;
import org.modelmapper.PropertyMap;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.Set;
import java.util.stream.Collectors;

@Configuration
public class ModelMapperConfig {

    @Bean
    public ModelMapper modelMapper() {
        ModelMapper modelMapper = new ModelMapper();
        modelMapper.addMappings(new PropertyMap<User, UserDTO>() {
            @Override
            @SuppressWarnings("unchecked")
            protected void configure() {
                map().setId(source.getId());
                map().setEmail(source.getEmail());
                map().setPhone(source.getPhone());
                map().setFirstName(source.getFirstName());
                map().setLastName(source.getLastName());
                map().setProfileImageUrl(source.getProfileImageUrl());
                map().setCreatedAt(source.getCreatedAt());
                map().setUpdatedAt(source.getUpdatedAt());
                using(ctx -> ((Set<Role>) ctx.getSource()).stream()
                        .map(role -> role.getRolePosition().name())
                        .collect(Collectors.toSet()))
                        .map(source.getRoles(), destination.getRoleNames());
            }
        });

        modelMapper.addMappings(new PropertyMap<Material, MaterialDTO>() {
            @Override
            protected void configure() {
                map().setId(source.getId());
                map().setSupplyName(source.getSupplyName());
                map().setSupplyIsYarn(source.isSupplyIsYarn());
                map().setYarnGrams(source.getYarnGrams());
            }
        });

        return modelMapper;
    }
}