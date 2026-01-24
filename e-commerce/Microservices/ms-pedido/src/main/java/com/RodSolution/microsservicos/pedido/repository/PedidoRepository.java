package com.RodSolution.microsservicos.pedido.repository;

import com.RodSolution.microsservicos.pedido.model.entities.Pedido;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface PedidoRepository extends JpaRepository<Pedido, UUID> {
}
