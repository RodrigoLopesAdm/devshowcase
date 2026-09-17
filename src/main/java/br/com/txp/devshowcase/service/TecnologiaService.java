package br.com.txp.devshowcase.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import br.com.txp.devshowcase.dto.TechnologyRequestDTO;
import br.com.txp.devshowcase.dto.TechnologyResponseDTO;
import br.com.txp.devshowcase.model.Tecnologia;
import br.com.txp.devshowcase.repository.TecnologiaRepository;

@Service
public class TecnologiaService {

    @Autowired
    private TecnologiaRepository repository;

    @Transactional(readOnly = true)
    public List<TechnologyResponseDTO> buscarTodos() {
        return repository.findAll()
                .stream()
                .map(TechnologyResponseDTO::fromEntity)
                .toList();
    }

    @Transactional
    public TechnologyResponseDTO cadastrar(TechnologyRequestDTO dto) {
        Tecnologia tecnologia = new Tecnologia(dto.name());

        tecnologia = repository.save(tecnologia);

        return TechnologyResponseDTO.fromEntity(tecnologia);
    }
}
