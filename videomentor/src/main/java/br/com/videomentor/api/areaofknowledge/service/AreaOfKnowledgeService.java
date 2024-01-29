package br.com.videomentor.api.areaofknowledge.service;

import br.com.videomentor.api.areaofknowledge.converter.AreaOfKnowledgeConverter;
import br.com.videomentor.api.areaofknowledge.dto.AreaOfKnowledgeDto;
import br.com.videomentor.api.areaofknowledge.model.AreaOfKnowledge;
import br.com.videomentor.api.areaofknowledge.repository.AreaOfKnowledgeRepository;
import br.com.videomentor.api.commons.AbstractService;
import br.com.videomentor.api.exceptions.NotFoundException;
import java.util.List;
import java.util.UUID;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class AreaOfKnowledgeService
  implements AbstractService<AreaOfKnowledgeDto> {

  @Autowired
  private AreaOfKnowledgeRepository areaOfKnowledgeRepository;

  @Autowired
  private AreaOfKnowledgeConverter areaOfKnowledgeConverter;

  @Override
  public AreaOfKnowledgeDto create(AreaOfKnowledgeDto dto) {
    throw new UnsupportedOperationException("Unimplemented method 'create'");
  }

  @Override
  public AreaOfKnowledgeDto retrieveById(UUID uuid) {
    AreaOfKnowledge areaOfKnowledge = areaOfKnowledgeRepository
      .findById(uuid)
      .orElseThrow(() -> new NotFoundException("User not found."));
    return areaOfKnowledgeConverter.ormToDto(areaOfKnowledge);
  }

  @Override
  public Page<AreaOfKnowledgeDto> retrieveAll(Pageable pageable) {
    Page<AreaOfKnowledge> areaOfKnowledgePage = areaOfKnowledgeRepository.findAll(
      pageable
    );
    List<AreaOfKnowledgeDto> areaOfKnowledgeDtoList = areaOfKnowledgeConverter.ormListToDtoList(
      areaOfKnowledgePage.getContent()
    );
    return new PageImpl<>(
      areaOfKnowledgeDtoList,
      pageable,
      areaOfKnowledgePage.getTotalElements()
    );
  }

  @Override
  public AreaOfKnowledgeDto update(AreaOfKnowledgeDto dto) {
    throw new UnsupportedOperationException("Unimplemented method 'update'");
  }

  @Override
  public void delete(UUID uuid) {
    throw new UnsupportedOperationException("Unimplemented method 'delete'");
  }
}
