package com.alura.forohub.domain.topico;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class TopicoService {

    private final TopicoRepository topicoRepository;
    private final String TOPICO_NOT_FOUND = "Topico no encontrado";

    @Transactional(readOnly = true)
    public List<TopicoResponse> listarTopico(){
        var listaTopicos = topicoRepository.findAll();

         return listaTopicos.stream().
                map(topico -> new TopicoResponse(
                        topico.getId(),
                        topico.getTitulo(),
                        topico.getMensaje(),
                        topico.getFechaCreacion(),
                        topico.getStatus().name(),
                        topico.getNombreAutor(),
                        topico.getNombreCurso()
                )).toList();
    }

    @Transactional(readOnly = true)
    public TopicoResponse listarTopicoPorId(Long id){

        Topico topicoBuscado = topicoRepository.findById(id).orElseThrow(() -> new IllegalArgumentException(TOPICO_NOT_FOUND));

        return new TopicoResponse(
                topicoBuscado.getId(),
                topicoBuscado.getTitulo(),
                topicoBuscado.getMensaje(),
                topicoBuscado.getFechaCreacion(),
                topicoBuscado.getStatus().name(),
                topicoBuscado.getNombreAutor(),
                topicoBuscado.getNombreCurso()
        );
    }

    @Transactional
    public TopicoResponse guardarTopico(TopicoRequest topicoRequest){
        var topico = new Topico();

        topico.setMensaje(topicoRequest.mensaje());
        topico.setStatus(Status.ACTIVO);
        topico.setFechaCreacion(LocalDateTime.now());
        topico.setTitulo(topicoRequest.titulo());
        topico.setNombreAutor(topicoRequest.autor());
        topico.setNombreCurso(topicoRequest.curso());
        topicoRepository.save(topico);



        return new TopicoResponse(
                topico.getId(),
                topico.getTitulo(),
                topico.getMensaje(),
                topico.getFechaCreacion(),
                topico.getStatus().name(),
                topico.getNombreAutor(),
                topico.getNombreCurso()
        );
    }

    @Transactional
    public TopicoResponse actualizarTopico(TopicoActualizarRequest topicoActualizarRequest){
        var topicoBuscado = topicoRepository.findById(topicoActualizarRequest.id()).orElseThrow(() -> new IllegalArgumentException(TOPICO_NOT_FOUND));

        topicoBuscado.setMensaje(topicoActualizarRequest.mensaje());
        topicoBuscado.setStatus(Status.ACTUALIZADO);
        topicoBuscado.setFechaCreacion(LocalDateTime.now());
        topicoBuscado.setTitulo(topicoActualizarRequest.titulo());
        topicoBuscado.setNombreAutor(topicoActualizarRequest.autor());
        topicoBuscado.setNombreCurso(topicoActualizarRequest.curso());
        topicoRepository.save(topicoBuscado);

        return new TopicoResponse(
                topicoBuscado.getId(),
                topicoBuscado.getTitulo(),
                topicoBuscado.getMensaje(),
                topicoBuscado.getFechaCreacion(),
                topicoBuscado.getStatus().name(),
                topicoBuscado.getNombreAutor(),
                topicoBuscado.getNombreCurso()
        );
    }

    @Transactional
    public void eliminarTopico(Long id){
        var topicoBuscado = topicoRepository.findById(id).orElseThrow(() -> new IllegalArgumentException(TOPICO_NOT_FOUND));
        topicoBuscado.setStatus(Status.ELIMINADO);
        topicoRepository.save(topicoBuscado);
    }

}
