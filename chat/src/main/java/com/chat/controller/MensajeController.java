package com.chat.controller;



import com.chat.models.Mensaje;
import com.chat.repositories.MensajeRepository;
import com.chat.service.MensajeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;


import java.util.List;


@RestController
@RequestMapping("/api/mensajes")
public class MensajeController {

    @Autowired
    private MensajeRepository mensajeRepository;

    // Endpoint para guardar un mensaje
    @PostMapping
    public Mensaje guardarMensaje(@RequestBody Mensaje mensaje) {
        // Asegurarnos de que el mensaje tiene un chatId antes de guardarlo
        if (mensaje.getChatId() == null || mensaje.getChatId().isEmpty()) {
            throw new IllegalArgumentException("El chatId es necesario para guardar el mensaje.");
        }
        return mensajeRepository.save(mensaje);
    }

    // Endpoint para obtener los mensajes de un chat específico
    @GetMapping("/chat/{chatId}")
    public List<Mensaje> obtenerMensajesPorChat(@PathVariable String chatId) {
        return mensajeRepository.findByChatId(chatId);
    }

    // Endpoint para obtener mensajes de un usuario específico
    @GetMapping("/usuario/{username}")
    public List<Mensaje> obtenerMensajesPorUsuario(@PathVariable String username) {
        return mensajeRepository.findByUsername(username);
    }
}
