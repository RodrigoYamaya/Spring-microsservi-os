package com.RodSolution.microsservicos.pedido.controller;


import com.RodSolution.microsservicos.pedido.model.entities.Pedido;
import com.RodSolution.microsservicos.pedido.service.PedidoService;
import org.springframework.amqp.rabbit.core.RabbitTemplate;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/pedidos")
public class PedidoController {

    private final RabbitTemplate rabbitTemplate;
    private final PedidoService pedidoService;

    public PedidoController(RabbitTemplate rabbitTemplate, PedidoService pedidoService) {
        this.rabbitTemplate = rabbitTemplate;
        this.pedidoService = pedidoService;
    }

    @Value("${broker.queue.processamento.name}")
    private String routingKey;

    @PostMapping("/save")
    public ResponseEntity<?> save(@RequestBody  Pedido pedido) {
        Pedido pedidoSave = pedidoService.savePedido(pedido);
        rabbitTemplate.convertAndSend("", routingKey, pedidoSave.getDescricao());
        return ResponseEntity.status(HttpStatus.CREATED).body("Pedido saldo e enviado para processamento: " + pedido.getDescricao());
    }


    @GetMapping()
    public ResponseEntity<List<Pedido>> findAll() {
        List<Pedido> listaPedido =  pedidoService.listarPedido();
        return ResponseEntity.status(HttpStatus.OK).body(listaPedido);
    }


    @DeleteMapping("pedido/{id}")
    public ResponseEntity<?> deletePedido(@PathVariable(value = "id") UUID id) {
        pedidoService.deletarPedido(id);
        return ResponseEntity.status(HttpStatus.OK).body("Pedido com o ID " + id + " deletado com sucesso.");
    }











}
