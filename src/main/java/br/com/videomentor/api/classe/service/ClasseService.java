package br.com.videomentor.api.classe.service;

import br.com.videomentor.api.classe.converter.ClasseConverter;
import br.com.videomentor.api.classe.dto.ClasseDto;
import br.com.videomentor.api.classe.model.Classe;
import br.com.videomentor.api.classe.repository.ClasseRepository;
import br.com.videomentor.api.commons.AbstractService;
import br.com.videomentor.api.exceptions.HandleRuntimeException;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.webjars.NotFoundException;

@Service
public class ClasseService implements AbstractService<ClasseDto> {

  @Autowired
  private ClasseConverter classeConverter;

  @Autowired
  private ClasseRepository classeRepository;

  @Override
  public ClasseDto create(ClasseDto classeDto) {
    try {
      Classe classe = classeConverter.dtoToOrm(classeDto);
      return classeConverter.ormToDto(classeRepository.save(classe));
    } catch (Exception e) {
      new HandleRuntimeException("Erro ao registrar Classe");
      return null;
    }
  }

  @Override
  public ClasseDto retrieveById(UUID uuid) {
    Classe classe = classeRepository.findById(uuid).orElseThrow(() -> new NotFoundException("Classe not found"));
    return classeConverter.ormToDto(classe);
  }

  @Override
  public Page<ClasseDto> retrieveAll(Pageable pageable) {
    List<Classe> classes = classeRepository.findAll().stream().collect(Collectors.toList());
    List<ClasseDto> classeDtos = classes.stream().map(c -> classeConverter.ormToDto(c)).collect(Collectors.toList());
    Page<ClasseDto> page = new PageImpl<ClasseDto>(classeDtos, pageable, classes.size());
    return page;
  }

  @Override
  public ClasseDto update(ClasseDto classeDto) {
    try {
      Classe classe = classeRepository.getReferenceById(classeDto.getIdClasse());
      classeRepository.save(classeConverter.dtoToOrm(classeDto, classe));
      return classeConverter.ormToDto(classe, classeDto);
    } catch (Exception e) {
      new HandleRuntimeException(e.getMessage());
      return null;
    }
  }

  @Override
  public void delete(UUID uuid) {
    try {
      Classe classe = classeRepository.findById(uuid).orElseThrow(() -> new NotFoundException("Classe not found"));

      classeRepository.deleteById(classe.getIdClasse());
    } catch (Exception e) {
      throw new HandleRuntimeException("Erro ao deletar a classe");
    }
  }

  public long countAll() {
    return classeRepository.count();
  }

}
