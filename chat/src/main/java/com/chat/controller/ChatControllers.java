package com.chat.controller;

import com.chat.models.Mensaje;
import com.chat.repositories.MensajeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

@RestController
public class ChatControllers {

    @Autowired
    private MensajeRepository mensajeRepository;

    // Manejo de mensajes en tiempo real
    @MessageMapping("/mensaje")
    @SendTo("/chat/mensaje")
    public Mensaje recibeMensaje(Mensaje mensaje) {
        // Configurar atributos adicionales del mensaje
        mensaje.setFechaEnvio(LocalDateTime.now()); // Hora de envío

        // Validar si el usuario envió un nick; si no, asignar uno genérico
        if (mensaje.getUsername() == null || mensaje.getUsername().isEmpty()) {
            mensaje.setUsername("Usuario Anónimo");
        }

        // Asignar un chatId (esto debería ser proporcionado por el sistema, ya sea un chat único o un grupo)
        if (mensaje.getChatId() == null || mensaje.getChatId().isEmpty()) {
            // Si no tiene un chatId, podemos asignarle uno por defecto o un chat temporal
            mensaje.setChatId("chat_default");
        }

        // Guardar el mensaje en la base de datos
        Mensaje mensajeGuardado = mensajeRepository.save(mensaje);

        // Log para depuración
        System.out.println("Mensaje guardado: " + mensajeGuardado);

        return mensajeGuardado; // Enviar el mensaje de vuelta a todos los suscriptores
    }

}
