package br.com.videomentor.api.module.service;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

import br.com.videomentor.api.commons.AbstractService;
import br.com.videomentor.api.exceptions.HandleRuntimeException;
import br.com.videomentor.api.module.converter.ModuleConverter;
import br.com.videomentor.api.module.dto.ModuleDto;
import br.com.videomentor.api.module.model.Module;
import br.com.videomentor.api.module.repository.ModuleRepository;
import br.com.videomentor.api.subject.model.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.webjars.NotFoundException;

@Service
public class ModuleService implements AbstractService<ModuleDto> {

  @Autowired
  private ModuleConverter moduleConverter;

  @Autowired
  private ModuleRepository moduleRepository;

  @Override
  public ModuleDto create(ModuleDto moduleDto) {
    try {
      Module module = moduleConverter.dtoToOrm(moduleDto);
      return moduleConverter.ormToDto(moduleRepository.save(module));
    } catch (Exception e) {
      new HandleRuntimeException("Erro ao registrar Módulo");
      return null;
    }
  }

  @Override
  public ModuleDto retrieveById(UUID uuid) {
    Module module = moduleRepository.findById(uuid).orElseThrow(() -> new NotFoundException("Module not found"));
    return moduleConverter.ormToDto(module);
  }

  @Override
  public Page<ModuleDto> retrieveAll(Pageable pageable) {
    List<Module> modules = moduleRepository.findAll().stream().collect(Collectors.toList());
    List<ModuleDto> moduleDtos = modules.stream().map(m -> moduleConverter.ormToDto(m)).collect(Collectors.toList());
    Page<ModuleDto> page = new PageImpl<ModuleDto>(moduleDtos, pageable, modules.size());
    return page;
  }

  public List<ModuleDto> retrieveBySubject(Subject subject) {
    List<Module> modules = moduleRepository.findBySubject(subject);
    return modules.stream().map(moduleConverter::ormToDto).collect(Collectors.toList());
  }

  @Override
  public ModuleDto update(ModuleDto moduleDto) {
    try {
      Module module = moduleRepository.getReferenceById(moduleDto.getIdModule());
      moduleRepository.save(moduleConverter.dtoToOrm(moduleDto, module));
      return moduleConverter.ormToDto(module, moduleDto);
    } catch (Exception e) {
      new HandleRuntimeException(e.getMessage());
      return null;
    }
  }

  @Override
  public void delete(UUID uuid) {
    try {
      Module module = moduleRepository.findById(uuid).orElseThrow(() -> new NotFoundException("Module not found"));

      moduleRepository.deleteById(module.getIdModule());
    } catch (Exception e) {
      throw new HandleRuntimeException(
          "Erro ao deletar módulo, verifique se existe algum professor vinculado à disciplina");
    }
  }
}
