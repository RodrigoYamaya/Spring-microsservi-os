package com.RodSolution.microsservicos.pedido.model.entities;

import jakarta.persistence.*;
import lombok.*;

import java.util.UUID;

@Entity
@Data
public class ItemPedido {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;
    private String nome;
    private Integer quantidade;

    @ManyToOne
    @JoinColumn(name = "pedido_id")
    private Pedido pedido;

}
