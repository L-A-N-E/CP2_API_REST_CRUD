package br.com.fiap3esph.Checkpoint2.controller;

import br.com.fiap3esph.Checkpoint2.model.OrderModel;
import br.com.fiap3esph.Checkpoint2.service.OrderService;
import jakarta.persistence.EntityNotFoundException;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/orders")
public class OrderController {
    @Autowired
    private OrderService orderService;

    @PostMapping
    public ResponseEntity<Object> createOrder(@Valid @RequestBody OrderModel order){
        try{
            OrderModel newOrder = orderService.createOrder(order);

            return ResponseEntity.status(HttpStatus.CREATED).body(newOrder);
        }catch (IllegalArgumentException e){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
    }

    @GetMapping
    public List<OrderModel> readOrders(){
        return orderService.readAllOrders();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Object> getOrder(@PathVariable("id") Long id){
        try{
            OrderModel searchOrder = orderService.readOrderById(id);

            return ResponseEntity.status(HttpStatus.OK).body(searchOrder);
        }catch (EntityNotFoundException e){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<Object> updateOrder(@PathVariable("id") Long id, @Valid @RequestBody OrderModel order){
        try{
            OrderModel updateOrder = orderService.updateOrder(id, order);

            return ResponseEntity.status(HttpStatus.OK).body(updateOrder);
        }catch (EntityNotFoundException e){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Object> deleteOrder(@PathVariable("id") Long id){
        try{
            orderService.deleteOrderById(id);

            return ResponseEntity.status(HttpStatus.NO_CONTENT).body("Ordem deletada com sucesso!");
        } catch (EntityNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }
    }
}
