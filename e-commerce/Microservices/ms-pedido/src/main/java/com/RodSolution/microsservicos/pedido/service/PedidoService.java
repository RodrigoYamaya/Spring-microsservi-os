package com.RodSolution.microsservicos.pedido.service;

import com.RodSolution.microsservicos.pedido.model.entities.ItemPedido;
import com.RodSolution.microsservicos.pedido.model.entities.Pedido;
import com.RodSolution.microsservicos.pedido.repository.PedidoRepository;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class PedidoService {

    private final PedidoRepository pedidoRepository;

    public PedidoService(PedidoRepository pedidoRepository) {
        this.pedidoRepository = pedidoRepository;
    }

    @Transactional
    public Pedido savePedido(Pedido pedido) {
        if(pedido.getId() != null) {
            for(ItemPedido item : pedido.getItens()) {
                item.setPedido(pedido);
            }
        }
        return pedidoRepository.save(pedido);
    }

    public List<Pedido> listarPedido() {
        return pedidoRepository.findAll();
    }


    @Transactional
    public void deletarPedido(UUID id) {
        if(!pedidoRepository.existsById(id)) {
            throw new RuntimeException("Pedido com o ID " + id + " não encontrado");
        }
        pedidoRepository.deleteById(id);
    }
}
