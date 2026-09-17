package br.com.txp.devshowcase.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import br.com.txp.devshowcase.dto.ProfileRequestDTO;
import br.com.txp.devshowcase.dto.ProfileResponseDTO;
import br.com.txp.devshowcase.model.Profile;
import br.com.txp.devshowcase.repository.ProfileRepository;

@Service
public class ProfileService {

    @Autowired
    private ProfileRepository repository;

    @Transactional
    public ProfileResponseDTO cadastrar(ProfileRequestDTO dto) {
        Profile profile = new Profile(
            dto.name(),
            dto.email(),
            dto.bio()
        );

        profile = repository.save(profile);

        return ProfileResponseDTO.fromEntity(profile);
    }

    @Transactional(readOnly = true)
    public ProfileResponseDTO buscarPorId(Long id) {
        Profile profile = repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Perfil não encontrado"));

        return ProfileResponseDTO.fromEntity(profile);
    }
}