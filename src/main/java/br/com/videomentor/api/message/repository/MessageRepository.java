package br.com.videomentor.api.message.repository;

import br.com.videomentor.api.message.model.Message;
import br.com.videomentor.api.user.model.User;

import java.util.List;
import java.util.UUID;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * MessageRepository.
 *
 * @author Matheus Rodrigues <matheusrodrigues.dev@outlook.com>
 * @version 1.0
 */

public interface MessageRepository extends JpaRepository<Message, UUID> {

    List<Message> findByUser(User user);

    List<Message> findByReceiver(User receiver);

    List<Message> findBySender(User sender);

}
