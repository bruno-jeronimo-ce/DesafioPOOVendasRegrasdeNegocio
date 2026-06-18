package vendas.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import vendas.dto.request.VendaRequest;
import vendas.dto.response.VendaResponse;
import vendas.service.interfaces.IVendaService;

import java.util.List;

@RestController
@RequestMapping("/api/vendas")
@RequiredArgsConstructor
public class VendaController {

    private final IVendaService vendaService;

    @PostMapping
    public ResponseEntity<VendaResponse> registrarVenda(
            @RequestBody VendaRequest request) {

        return ResponseEntity.status(HttpStatus.CREATED)
                .body(vendaService.registrarVenda(request));
    }

    @GetMapping
    public ResponseEntity<List<VendaResponse>> listarTodas() {
        return ResponseEntity.ok(vendaService.listarTodas());
    }

    @GetMapping("/{id}")
    public ResponseEntity<VendaResponse> buscarPorId(
            @PathVariable Long id) {

        return ResponseEntity.ok(
                vendaService.buscarPorId(id)
        );
    }

    @GetMapping("/cliente/{clienteId}")
    public ResponseEntity<List<VendaResponse>> buscarPorCliente(
            @PathVariable Long clienteId) {

        return ResponseEntity.ok(
                vendaService.buscarPorCliente(clienteId)
        );
    }
}