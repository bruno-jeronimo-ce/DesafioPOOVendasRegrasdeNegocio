package vendas.controller;

import lombok.RequiredArgsConstructor;
import vendas.model.Estoque;
import vendas.service.interfaces.IEstoqueService;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/estoque")
@RequiredArgsConstructor
public class EstoqueController {

    private final IEstoqueService estoqueService;

    @GetMapping
    public ResponseEntity<List<Estoque>> listarTodos() {
        return ResponseEntity.ok(estoqueService.listarTodos());
    }

    @GetMapping("/{produtoId}")
    public ResponseEntity<Estoque> buscarPorProduto(
            @PathVariable Long produtoId) {

        return ResponseEntity.ok(
                estoqueService.buscarPorProduto(produtoId)
        );
    }

    @PutMapping("/{produtoId}")
    public ResponseEntity<Estoque> atualizarEstoque(
            @PathVariable Long produtoId,
            @RequestBody Estoque estoque) {

        return ResponseEntity.ok(
                estoqueService.atualizarEstoque(produtoId, estoque)
        );
    }
}