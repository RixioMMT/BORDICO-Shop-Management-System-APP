package org.BORDICO.Service;

import lombok.RequiredArgsConstructor;
import org.BORDICO.Exceptions.CustomException;
import org.BORDICO.Model.DTO.CartItemDTO;
import org.BORDICO.Model.Entity.Cart;
import org.BORDICO.Model.Entity.CartItem;
import org.BORDICO.Model.Inputs.CartItemInput;
import org.BORDICO.Model.Inputs.PageInput;
import org.BORDICO.Model.Pagination.PageOutput;
import org.BORDICO.Repository.CartItemRepository;
import org.BORDICO.Repository.CartRepository;
import org.BORDICO.Repository.ProductInventoryRepository;
import org.BORDICO.Repository.ProductRepository;
import org.BORDICO.Util.ModelMapperUtil;
import org.BORDICO.Util.PageUtil;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class CartItemService {
    private final CartItemRepository cartItemRepository;
    private final ModelMapper modelMapper;
    private final ModelMapperUtil modelMapperUtil;
    private final ProductInventoryRepository productInventoryRepository;
    private final CartRepository cartRepository;
    private final ProductRepository productRepository;
    public CartItemDTO createCartItem(CartItemInput cartItemInput) throws CustomException {
        CartItem cartItem = new CartItem();
        return getCartItemFromInput(cartItemInput, cartItem);
    }
    public PageOutput<CartItemDTO> getAllCartItems(PageInput pageInput) {
        Page<CartItem> cartItems = cartItemRepository.findAll(PageUtil.buildPage(pageInput));
        return new PageOutput<>(
                cartItems.getNumber(),
                cartItems.getTotalPages(),
                cartItems.getTotalElements(),
                cartItems.getContent().stream()
                        .map(cartItem -> modelMapperUtil.map(cartItem, CartItemDTO.class))
                        .collect(Collectors.toSet())
        );
    }
    public CartItemDTO getCartItemById(Long id) throws CustomException {
        CartItem cartItem = cartItemRepository.findById(id)
                .orElseThrow(() -> new CustomException("Cart Item with ID " + id + " not found"));
        return modelMapper.map(cartItem, CartItemDTO.class);
    }
    public CartItemDTO updateCartItem(Long id, CartItemInput cartItemInput) throws CustomException {
        CartItem cartItem = cartItemRepository.findById(id)
                .orElseThrow(() -> new CustomException("Cart Item with ID " + id + " not found"));
        return getCartItemFromInput(cartItemInput, cartItem);
    }
    public void deleteCartItem(Long id) throws CustomException {
        CartItem cartItem = cartItemRepository.findById(id)
                .orElseThrow(() -> new CustomException("Cart Item with ID " + id + " not found"));
        cartItemRepository.delete(cartItem);
    }
    private CartItemDTO getCartItemFromInput(CartItemInput cartItemInput, CartItem cartItem) throws CustomException {
        Cart cart = cartRepository.findById(cartItemInput.getCartId())
                .orElseThrow(() -> new CustomException("Cart with ID " + cartItemInput.getCartId() + " not found"));
        if (!productInventoryRepository.existsByItemNameAndItemColorType(cartItemInput.getItemName(), cartItemInput.getItemColorType())) {
            throw new CustomException("Product with name " + cartItemInput.getItemName() + " and color " + cartItemInput.getItemColorType() + " not found in inventory.");
        }
        Integer availableQuantity = productInventoryRepository.countAvailableItemsByItemNameAndItemColorType(cartItemInput.getItemName(), cartItemInput.getItemColorType());
        if (cartItemInput.getItemQuantity() > availableQuantity) {
            throw new CustomException("Insufficient product quantity for " + cartItemInput.getItemName() + " in color " + cartItemInput.getItemColorType());
        }
        BigDecimal productPrice = productRepository.findProductPriceByProductName(cartItemInput.getItemName());
        cartItem.setItemName(cartItemInput.getItemName());
        cartItem.setItemColorType(cartItemInput.getItemColorType());
        cartItem.setCartItemPrice(productPrice);
        cartItem.setItemQuantity(cartItemInput.getItemQuantity());
        cartItem.setCart(cart);
        cartItem = cartItemRepository.save(cartItem);
        return modelMapper.map(cartItem, CartItemDTO.class);
    }
}