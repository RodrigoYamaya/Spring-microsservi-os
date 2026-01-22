package com.RodSolution.microsservicos.pedido.repository;

import com.RodSolution.microsservicos.pedido.model.entities.ItemPedido;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface ItemPedidoRepository extends JpaRepository<ItemPedido, UUID> {
}
