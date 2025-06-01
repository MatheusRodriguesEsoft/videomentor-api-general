package br.com.videomentor.api.subject.service;

import br.com.videomentor.api.commons.AbstractService;
import br.com.videomentor.api.exceptions.HandleRuntimeException;
import br.com.videomentor.api.exceptions.NotFoundException;
import br.com.videomentor.api.subject.converter.SubjectConverter;
import br.com.videomentor.api.subject.dto.SubjectDto;
import br.com.videomentor.api.subject.model.Subject;
import br.com.videomentor.api.subject.repository.SubjectRepository;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class SubjectService implements AbstractService<SubjectDto> {

  @Autowired
  private SubjectConverter subjectConverter;

  @Autowired
  private SubjectRepository subjectRepository;

  @Override
  public SubjectDto create(SubjectDto subjectDto) {
    try {
      Subject subject = subjectConverter.dtoToOrm(subjectDto);
      return subjectConverter.ormToDto(subjectRepository.save(subject));
    } catch (Exception e) {
      new HandleRuntimeException("Erro ao registrar Disciplina");
      return null;
    }
  }

  @Override
  public SubjectDto retrieveById(UUID uuid) {
    Subject subject = subjectRepository
        .findById(uuid)
        .orElseThrow(() -> new NotFoundException("Subject not found"));
    return subjectConverter.ormToDto(subject);
  }

  @Override
  public Page<SubjectDto> retrieveAll(Pageable pageable) {
    List<Subject> subjects = subjectRepository
        .findAll()
        .stream()
        .collect(Collectors.toList());
    List<SubjectDto> subjectDtos = subjects
        .stream()
        .map(s -> subjectConverter.ormToDto(s))
        .collect(Collectors.toList());
    Page<SubjectDto> page = new PageImpl<SubjectDto>(
        subjectDtos,
        pageable,
        subjects.size());
    return page;
  }

  @Override
  public SubjectDto update(SubjectDto subjectDto) {
    try {
      Subject subject = subjectRepository.getReferenceById(
          subjectDto.getIdSubject());
      subjectRepository.save(subjectConverter.dtoToOrm(subjectDto, subject));
      return subjectConverter.ormToDto(subject, subjectDto);
    } catch (Exception e) {
      new HandleRuntimeException(e.getMessage());
      return null;
    }
  }

  @Override
  public void delete(UUID uuid) {
    try {
      Subject subject = subjectRepository
          .findById(uuid)
          .orElseThrow(() -> new NotFoundException("Subject not found"));

      subjectRepository.deleteById(subject.getIdSubject());
    } catch (Exception e) {
      throw new HandleRuntimeException(
          "Erro ao deletar a disciplina, verifique se existe algum professor vinculado à disciplina");
    }
  }
}
