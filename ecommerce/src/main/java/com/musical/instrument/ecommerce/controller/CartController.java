package com.musical.instrument.ecommerce.controller;

import com.musical.instrument.ecommerce.Entity.Account;
import com.musical.instrument.ecommerce.Entity.Product;
import com.musical.instrument.ecommerce.dto.request.Cart.AddCartItemDTO;
import com.musical.instrument.ecommerce.dto.request.Cart.CartDTO;
import com.musical.instrument.ecommerce.dto.request.Cart.CartDetailDTO;
import com.musical.instrument.ecommerce.dto.response.ResponseDTO;
import com.musical.instrument.ecommerce.exception.CreateDataFailException;
import com.musical.instrument.ecommerce.exception.DataNotFoundException;
import com.musical.instrument.ecommerce.repositpory.AccountRepository;
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
        responseDTO.setSuccessCode("LOAD_CART_DETAIL_SUCCESS");
        return ResponseEntity.ok().body(responseDTO);
    }

    @PostMapping("/{id}")
    @PreAuthorize("hasRole('USER') or hasRole('ADMIN')" )
    public ResponseEntity<ResponseDTO> AddItem(@PathVariable("id") Long productId, @Valid @RequestBody AddCartItemDTO itemDTO)
    throws CreateDataFailException {
        ResponseDTO responseDTO = new ResponseDTO();
        try {
            Product product = productRepository.findById(productId)
                                               .orElseThrow(() -> new DataNotFoundException("PRODUCT_NOT_FOUND"));
            Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
            String username = ((UserDetails)principal).getUsername();
            Account account = accountRepository.findByUsername(username).get();

            CartDTO cartDTO = cartService.AddItemToCart(account.getId(),product,itemDTO.getQuantity());

            responseDTO.setData(cartDTO);
            responseDTO.setSuccessCode("ADD_ITEM_SUCCESS");
        }catch (Exception e){
            responseDTO.setErrorCode("ADD_ITEM_FAIL");
            throw new CreateDataFailException(e.getMessage());
        }

        return ResponseEntity.ok().body(responseDTO);
    }

}
