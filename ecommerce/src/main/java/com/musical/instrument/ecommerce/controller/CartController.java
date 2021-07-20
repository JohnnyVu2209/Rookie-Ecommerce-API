package com.musical.instrument.ecommerce.controller;

import com.musical.instrument.ecommerce.Entity.Account;
import com.musical.instrument.ecommerce.Entity.CartItem;
import com.musical.instrument.ecommerce.Entity.Product;
import com.musical.instrument.ecommerce.dto.request.Cart.AddCartItemDTO;
import com.musical.instrument.ecommerce.dto.request.Cart.CartDTO;
import com.musical.instrument.ecommerce.dto.request.Cart.CartDetailDTO;
import com.musical.instrument.ecommerce.dto.request.Cart.RemoveItemDTO;
import com.musical.instrument.ecommerce.dto.response.ErrorCode;
import com.musical.instrument.ecommerce.dto.response.ResponseDTO;
import com.musical.instrument.ecommerce.dto.response.SuccessCode;
import com.musical.instrument.ecommerce.exception.CreateDataFailException;
import com.musical.instrument.ecommerce.exception.DataNotFoundException;
import com.musical.instrument.ecommerce.exception.DeleteDataFailException;
import com.musical.instrument.ecommerce.repositpory.AccountRepository;
import com.musical.instrument.ecommerce.repositpory.CartItemRepository;
import com.musical.instrument.ecommerce.repositpory.ProductRepository;
import com.musical.instrument.ecommerce.service.CartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Optional;

@RestController
@RequestMapping("/cart")
public class CartController {
    @Autowired
    private AccountRepository accountRepository;

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private CartItemRepository cartItemRepository;

    @Autowired
    private CartService cartService;


    @GetMapping("/detail")
    @PreAuthorize("hasRole('USER') or hasRole('ADMIN')" )
    public ResponseEntity<ResponseDTO> CartDetail(){
        ResponseDTO responseDTO = new ResponseDTO();
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        String username = ((UserDetails)principal).getUsername();
        CartDetailDTO cartDetailDTO = cartService.CartDetail(accountRepository.findByUsername(username).get().getId());
        if(cartDetailDTO.getCartItems().size() != 0){
            responseDTO.setData(cartDetailDTO);
        }else {
            responseDTO.setData("You have not add any item to cart");
        }
        responseDTO.setSuccessCode(SuccessCode.LOAD_CART_DETAIL_SUCCESS);
        return ResponseEntity.ok().body(responseDTO);
    }

    @PostMapping("/")
    @PreAuthorize("hasRole('USER') or hasRole('ADMIN')" )
    public ResponseEntity<ResponseDTO> AddItem(@Valid @RequestBody AddCartItemDTO itemDTO)
    throws CreateDataFailException {
        ResponseDTO responseDTO = new ResponseDTO();
        try {
            Product product = productRepository.findById(itemDTO.getId())
                                               .orElseThrow(() -> new DataNotFoundException(ErrorCode.ERR_PRODUCT_NOT_FOUND));
            Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
            String username = ((UserDetails)principal).getUsername();
            Account account = accountRepository.findByUsername(username).get();

            CartDTO cartDTO = cartService.AddItemToCart(account.getId(),product,itemDTO.getQuantity());

            responseDTO.setData(cartDTO);
            responseDTO.setSuccessCode(SuccessCode.ADD_ITEM_SUCCESS);
        }catch (Exception e){
            throw new CreateDataFailException(ErrorCode.ERR_ADD_ITEM_FAIL);
        }

        return ResponseEntity.ok().body(responseDTO);
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<ResponseDTO> RemoveItem(@PathVariable("id") Long productId) throws DeleteDataFailException {
        ResponseDTO responseDTO = new ResponseDTO();
        try {
            Product product = productRepository.findById(productId)
                                               .orElseThrow(()-> new DataNotFoundException(ErrorCode.ERR_PRODUCT_NOT_FOUND));
            Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
            String username = ((UserDetails)principal).getUsername();
            Account account = accountRepository.findByUsername(username).get();
            CartItem cartItem = cartItemRepository.findByProduct(product)
                                                .orElseThrow(() -> new DataNotFoundException(ErrorCode.ERR_ITEM_NOT_EXIST_IN_CART));
            CartDTO cartDTO = cartService.RemoveItemFromCart(account.getId(), cartItem);
            responseDTO.setData(cartDTO);
            responseDTO.setSuccessCode(SuccessCode.REMOVE_ITEM_SUCCESS);
        }catch (Exception e){
            throw new DeleteDataFailException(ErrorCode.ERR_REMOVE_ITEM_FAIL);
        }
        return ResponseEntity.ok().body(responseDTO);
    }

}
