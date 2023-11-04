package br.com.wanderley.victor.punchguardian.comum.services;

import br.com.wanderley.victor.punchguardian.comum.models.dtos.PessoaDTO;
import br.com.wanderley.victor.punchguardian.comum.models.entities.Pessoa;
import br.com.wanderley.victor.punchguardian.comum.models.mappers.PessoaMapper;
import br.com.wanderley.victor.punchguardian.comum.repositories.PessoaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PessoaService {
    final
    PessoaRepository repository;
    @Autowired
    public PessoaService(PessoaRepository repository) {
        this.repository = repository;
    }

    public PessoaDTO create(PessoaDTO pessoaDTO) {
        Pessoa pessoa = PessoaMapper.toEntity(pessoaDTO);
        Pessoa savedPessoa = repository.save(pessoa);
        return PessoaMapper.toDTO(savedPessoa);
    }

    public PessoaDTO findById(Integer id) {
        Optional<Pessoa> pessoa = repository.findById(id);
        return pessoa.map(PessoaMapper::toDTO).orElse(null);
    }


    public List<PessoaDTO> findAll(){
        return PessoaMapper.toPessoaDTOList(repository.findAll());
    }

    public PessoaDTO update(Integer id, PessoaDTO pessoaDTO) {
        if (!repository.existsById(id)) {
            return null;
        }

        pessoaDTO.setId(id);
        Pessoa updatedPessoa = repository.save(PessoaMapper.toEntity(pessoaDTO));
        return PessoaMapper.toDTO(updatedPessoa);
    }

    public void delete(Integer id) {
        if (repository.existsById(id)) {
            repository.deleteById(id);
        }
    }


}
