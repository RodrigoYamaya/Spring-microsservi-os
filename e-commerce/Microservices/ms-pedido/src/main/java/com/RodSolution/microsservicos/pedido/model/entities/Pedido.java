package com.RodSolution.microsservicos.pedido.model.entities;

import jakarta.persistence.*;
import lombok.Data;

import java.util.List;
import java.util.UUID;

@Entity
@Data
public class Pedido {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;
    private String descricao;

    @OneToMany(mappedBy = "pedido", cascade = CascadeType.ALL)
    private List<ItemPedido> itens;
}
