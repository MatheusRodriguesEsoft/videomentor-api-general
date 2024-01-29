package br.com.videomentor.api.commons;

import java.util.List;

/**
 * Classe abstrata para definir os métodos padrões de um converter entre ORM e DTO.
 *
 * @author Matheus Rodrigues <matheusrodridgues@outlook.com>
 * @version 1.0
 * @param <O> Objeto ORM.
 * @param <D> Objeto DTO.
 */
public interface AbstractConverter<O, D> {

    /**
     * Converte um DTO para o formato ORM.
     */
    public O dtoToOrm(D dto, O orm);

    /**
     * Converte um DTO para o formato ORM.
     */
    public O dtoToOrm(D dto);

    /**
     * Converte um ORM para o formato DTO.
     */
    public D ormToDto(O orm, D dto);

    /**
     * Converte um ORM para o formato DTO.
     */
    public D ormToDto(O orm);

    /**
     * Converte uma lista de DTO para uma lista de ORM.
     */
    public List<O> dtoListToOrmList(final List<D> dtoList);

    /**
     * Converte uma lista de ORM para uma lista de DTO.
     */
    public List<D> ormListToDtoList(final List<O> ormList);

}