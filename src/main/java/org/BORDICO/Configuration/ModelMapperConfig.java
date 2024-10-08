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
                map().setItemName(source.getItemName());
                map().setItemColorType(source.getItemColorType());
                map().setIsSold(source.getIsSold());
                map().setManufacturedDate(source.getManufacturedDate());
                map().setSoldAt(source.getSoldAt());
            }
        });

        modelMapper.addMappings(new PropertyMap<Category, CategoryDTO>() {
            @Override
            @SuppressWarnings("unchecked")
            protected void configure() {
                map().setId(source.getId());
                map().setCategoryName(source.getCategoryName());
                map().setCategoryDescription(source.getCategoryDescription());
                using(ctx -> ((Set<Product>) ctx.getSource()).stream()
                        .map(Product::getProductName)
                        .collect(Collectors.toSet()))
                        .map(source.getProducts(), destination.getProductNames());
            }
        });

        modelMapper.addMappings(new PropertyMap<Review, ReviewDTO>() {
            @Override
            protected void configure() {
                map().setId(source.getId());
                map().setTitle(source.getTitle());
                map().setDescription(source.getDescription());
                map().setReviewCategory(source.getReviewCategory());
                map().setProductId(source.getProduct().getId());
                map().setUserId(source.getUser().getId());
            }
        });

        modelMapper.addMappings(new PropertyMap<Notification, NotificationDTO>() {
            @Override
            protected void configure() {
                map().setId(source.getId());
                map().setNotificationName(source.getNotificationName());
                map().setNotificationDescription(source.getNotificationDescription());
                map().setUserId(source.getUser().getId());
            }
        });

        modelMapper.addMappings(new PropertyMap<Cart, CartDTO>() {
            @Override
            @SuppressWarnings("unchecked")
            protected void configure() {
                map().setId(source.getId());
                map().setCartPrice(source.getCartPrice());
                map().setCartQuantity(source.getCartQuantity());
                map().setCartStatus(source.getCartStatus());
                map().setUserId(source.getUser().getId());
                using(ctx -> ((Set<CartItem>) ctx.getSource()).stream()
                        .map(CartItem::getId)
                        .collect(Collectors.toSet()))
                        .map(source.getCartItems(), destination.getCartItemsId());
                map().setPaymentId(source.getPayment().getId());
            }
        });

        modelMapper.addMappings(new PropertyMap<CartItem, CartItemDTO>() {
            @Override
            protected void configure() {
                map().setId(source.getId());
                map().setItemName(source.getItemName());
                map().setItemColorType(source.getItemColorType());
                map().setCartItemPrice(source.getCartItemPrice());
                map().setItemQuantity(source.getItemQuantity());
                map().setCartId(source.getCart().getId());
            }
        });

        modelMapper.addMappings(new PropertyMap<Payment, PaymentDTO>() {
            @Override
            protected void configure() {
                map().setClientName(source.getClientName());
                map().setBank(source.getBank());
                map().setConfirmationNumber(source.getConfirmationNumber());
                map().setPaymentPrice(source.getPaymentPrice());
                map().setPaymentMethod(source.getPaymentMethod());
                map().setPaymentStatus(source.getPaymentStatus());
                map().setConfirmationDate(source.getConfirmationDate());
                map().setCartId(source.getCart().getId());
                map().setIncomeOrderId(source.getIncomeOrder().getId());
            }
        });

        modelMapper.addMappings(new PropertyMap<IncomeOrder, IncomeOrderDTO>() {
            @Override
            protected void configure() {
                map().setClientName(source.getClientName());
                map().setShippingAddress(source.getShippingAddress());
                map().setOrderPrice(source.getOrderPrice());
                map().setPaymentMethod(source.getPaymentMethod());
                map().setIncomePlatform(source.getIncomePlatform());
                map().setPaymentId(source.getPayment().getId());
                map().setIncomeId(source.getIncome().getId());
                map().setShippingId(source.getShipping().getId());
            }
        });

        modelMapper.addMappings(new PropertyMap<Income, IncomeDTO>() {
            @Override
            protected void configure() {
                map().setId(source.getId());
                map().setProductReference(source.getProductReference());
                map().setIncomeDescription(source.getIncomeDescription());
                map().setIncomePrice(source.getIncomePrice());
                map().setIncomePlatform(source.getIncomePlatform());
                map().setPaymentMethod(source.getPaymentMethod());
                map().setIncomeOrderId(source.getIncomeOrder().getId());
            }
        });

        modelMapper.addMappings(new PropertyMap<Shipping, ShippingDTO>() {
            @Override
            protected void configure() {
                map().setId(source.getId());
                map().setCarrier(source.getCarrier());
                map().setTrackingNumber(source.getTrackingNumber());
                map().setShippingDate(source.getShippingDate());
                map().setShippingStatus(source.getShippingStatus());
                map().setIncomeOrder(source.getIncomeOrder().getId());
            }
        });

        return modelMapper;
    }
}