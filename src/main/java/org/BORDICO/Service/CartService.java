package org.BORDICO.Service;

import lombok.RequiredArgsConstructor;
import org.BORDICO.Exceptions.CustomException;
import org.BORDICO.Model.DTO.CartDTO;
import org.BORDICO.Model.Entity.Cart;
import org.BORDICO.Model.Inputs.CartInput;
import org.BORDICO.Model.Entity.User;
import org.BORDICO.Model.Inputs.PageInput;
import org.BORDICO.Model.Pagination.PageOutput;
import org.BORDICO.Repository.CartRepository;
import org.BORDICO.Repository.UserRepository;
import org.BORDICO.Util.ModelMapperUtil;
import org.BORDICO.Util.PageUtil;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class CartService {
    private final CartRepository cartRepository;
    private final ModelMapper modelMapper;
    private final ModelMapperUtil modelMapperUtil;
    private final UserRepository userRepository;
    public CartDTO createCart(CartInput cartInput) throws CustomException {
        Cart cart = new Cart();
        cart.setCartItems(new HashSet<>());
        return getCartFromInput(cartInput, cart);
    }
    public PageOutput<CartDTO> getAllCarts(PageInput pageInput) {
        Page<Cart> carts = cartRepository.findAll(PageUtil.buildPage(pageInput));
        return new PageOutput<>(
                carts.getNumber(),
                carts.getTotalPages(),
                carts.getTotalElements(),
                carts.getContent().stream()
                        .map(cart -> modelMapperUtil.map(cart, CartDTO.class))
                        .collect(Collectors.toSet())
        );
    }
    public CartDTO getCartById(Long id) throws CustomException {
        Cart cart = cartRepository.findById(id)
                .orElseThrow(() -> new CustomException("Cart with ID " + id + " not found"));
        return modelMapper.map(cart, CartDTO.class);
    }
    public CartDTO updateCart(Long id, CartInput cartInput) throws CustomException {
        Cart cart = cartRepository.findById(id)
                .orElseThrow(() -> new CustomException("Cart with ID " + id + " not found"));
        return getCartFromInput(cartInput, cart);
    }
    public void deleteCart(Long id) throws CustomException {
        Cart cart = cartRepository.findById(id)
                .orElseThrow(() -> new CustomException("Cart with ID " + id + " not found"));
        cartRepository.delete(cart);
    }
    private CartDTO getCartFromInput(CartInput cartInput, Cart cart) throws CustomException {
        User user = userRepository.findById(cartInput.getUserId())
                .orElseThrow(() -> new CustomException("User with ID " + cartInput.getUserId() + " not found"));
        cart.setCartPrice(cartInput.getCartPrice());
        cart.setCartQuantity(cartInput.getCartQuantity());
        cart.setCartStatus(cartInput.getCartStatus());
        cart.setUser(user);
        cart = cartRepository.save(cart);
        return modelMapper.map(cart, CartDTO.class);
    }
    public User createCartForUser(User user) {
        Set<Cart> carts = new HashSet<>();
        Cart cart = Cart.builder()
                .user(user)
                .cartItems(new HashSet<>())
                .build();
        cart = cartRepository.save(cart);
        carts.add(cart);
        user.setCarts(carts);
        return userRepository.save(user);
    }
}