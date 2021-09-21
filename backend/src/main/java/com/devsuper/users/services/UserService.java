package com.devsuper.users.services;

import com.devsuper.users.dto.ClientDTO;
import com.devsuper.users.entities.User;
import com.devsuper.users.exceptions.DatabaseException;
import com.devsuper.users.repository.UserRepo;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import javax.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class UserService {

  @Autowired UserRepo _userRepo;

  @Transactional(readOnly = true)
  public ClientDTO findById(Long id) {

    Optional<User> _user = _userRepo.findById(id);

    return new ClientDTO(
        _user.orElseThrow(
            () ->
                new com.devsuper.users.exceptions.EntityNotFoundException(
                    "Resource not found for Id: " + id)));
  }

  @Transactional
  public ClientDTO create(User client) {
    User _client =
        new User(
            client.getName(),
            client.getCpf(),
            client.getIncome(),
            client.getBirthDate(),
            client.getChildren());

    _client = _userRepo.save(_client);

    return new ClientDTO(_client);
  }

  @Transactional(readOnly = true)
  public List<ClientDTO> findAll() {
    List<ClientDTO> _listClientDto = new ArrayList<>();

    _userRepo.findAll().forEach(o -> _listClientDto.add(new ClientDTO(o)));

    return _listClientDto;
  }

  @Transactional(readOnly = true)
  public List<ClientDTO> findAllPaged(PageRequest page) {
    List<ClientDTO> _listClientDto = new ArrayList<>();

    _userRepo.findAll(page).forEach(o -> _listClientDto.add(new ClientDTO(o)));

    return _listClientDto;
  }

  @Transactional
  public void delete(Long id) {

    try {
      _userRepo.deleteById(id);
    } catch (EmptyResultDataAccessException ex) {
      throw new EntityNotFoundException("Entity not found " + id);
    } catch (DataIntegrityViolationException ex) {
      throw new DatabaseException("Integrity violation");
    }
  }

  @Transactional
  public void update(Long id, ClientDTO client) {

    try {

      User _usr = _userRepo.getOne(id);

      _usr.setBirthDate(client.getBirthDate());
      _usr.setChildren(client.getChildren());
      _usr.setCpf(client.getCpf());
      _usr.setIncome(client.getIncome());
      _usr.setName(client.getName());

      _userRepo.save(_usr);

    } catch (javax.persistence.EntityNotFoundException e) {
      throw new EntityNotFoundException("Entity not found");
    }
  }
}
