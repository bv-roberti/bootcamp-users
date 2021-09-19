package com.devsuper.users.resources;

import com.devsuper.users.dto.ClientDTO;
import com.devsuper.users.entities.User;
import com.devsuper.users.services.UserService;
import java.net.URI;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

@Controller
@RequestMapping(value = "clients")
public class UserResource {

  @Autowired UserService _userService;

  @GetMapping
  public ResponseEntity<List<ClientDTO>> findAll(
      @RequestParam(value = "page", defaultValue = "0") Integer page,
      @RequestParam(value = "linesPerPage", defaultValue = "12") Integer linesPerPage,
      @RequestParam(value = "orderBy", defaultValue = "name") String orderBy,
      @RequestParam(value = "direction", defaultValue = "DESC") String direction) {

    PageRequest _page = PageRequest.of(page, linesPerPage, Direction.valueOf(direction), orderBy);

    return ResponseEntity.ok().body(_userService.findAllPaged(_page));
  }

  @GetMapping(value = "/{id}")
  public ResponseEntity<User> findById(@PathVariable Long id) {
    User _user = _userService.findById(id).get();

    return ResponseEntity.ok().body(_user);
  }

  @PostMapping
  public ResponseEntity<ClientDTO> create(@RequestBody User client) {

    ClientDTO _client = _userService.create(client);

    URI uri =
        ServletUriComponentsBuilder.fromCurrentRequest()
            .path("/{id}s")
            .buildAndExpand(client.getId())
            .toUri();

    return ResponseEntity.created(uri).body(_client);
  }

  @DeleteMapping(value = "/{id}")
  public ResponseEntity<ClientDTO> delete(@PathVariable Long id) {
    _userService.delete(id);

    return ResponseEntity.noContent().build();
  }

  @PutMapping(value = "/{id}")
  public ResponseEntity<ClientDTO> update(@PathVariable Long id, @RequestBody ClientDTO _client) {
    _userService.update(id, _client);

    return ResponseEntity.noContent().build();
  }
}
