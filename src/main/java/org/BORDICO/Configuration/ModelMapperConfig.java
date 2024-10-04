package org.BORDICO.Configuration;

import org.BORDICO.Model.DTO.*;
import org.BORDICO.Model.Entity.*;
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
                map().setSupplyIsYarn(source.getSupplyIsYarn());
                map().setYarnGrams(source.getYarnGrams());
            }
        });

        modelMapper.addMappings(new PropertyMap<Pattern, PatternDTO>() {
            @Override
            @SuppressWarnings("unchecked")
            protected void configure() {
                map().setId(source.getId());
                map().setPatternName(source.getPatternName());
                map().setPatternImageUrl(source.getPatternImageUrl());
                map().setPatternPdfUrl(source.getPatternPdfUrl());
                using(ctx -> ((Set<Product>) ctx.getSource()).stream()
                        .map(Product::getProductName)
                        .collect(Collectors.toSet()))
                        .map(source.getProducts(), destination.getProductNames());
                using(ctx -> ((Set<Material>) ctx.getSource()).stream()
                        .map(Material::getSupplyName)
                        .collect(Collectors.toSet()))
                        .map(source.getMaterials(), destination.getMaterialNames());
            }
        });

        modelMapper.addMappings(new PropertyMap<Product, ProductDTO>() {
            @Override
            @SuppressWarnings("unchecked")
            protected void configure() {
                map().setId(source.getId());
                map().setProductName(source.getProductName());
                map().setProductPrice(source.getProductPrice());
                map().setProductWidth(source.getProductWidth());
                map().setProductHeight(source.getProductHeight());
                map().setProductLength(source.getProductLength());
                map().setProductWeight(source.getProductWeight());
                map(source.getPattern().getPatternName(), destination.getPatternName());
                using(ctx -> ((Set<Category>) ctx.getSource()).stream()
                        .map(Category::getCategoryName)
                        .collect(Collectors.toSet()))
                        .map(source.getCategories(), destination.getCategoryNames());
                using(ctx -> ((Set<Review>) ctx.getSource()).stream()
                        .map(Review::getTitle)
                        .collect(Collectors.toSet()))
                        .map(source.getReviews(), destination.getReviews());
            }
        });

        modelMapper.addMappings(new PropertyMap<Inventory, InventoryDTO>() {
            @Override
            protected void configure() {
                map().setId(source.getId());
                map().setProductName(source.getProductName());
                map().setProductColorType(source.getProductColorType());
                map().setIsSold(source.getIsSold());
                map().setManufacturedDate(source.getManufacturedDate());
                map().setSoldAt(source.getSoldAt());
            }
        });

        return modelMapper;
    }
}