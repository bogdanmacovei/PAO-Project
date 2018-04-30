package Clase.controller;

import Clase.model.User;
import Clase.service.MySqlUserService;
import Clase.service.UpdateImage;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.io.Serializable;
import java.sql.SQLException;
import java.util.List;

@Controller
@ResponseBody
public class UserController {

    private final MySqlUserService service;

    public UserController(MySqlUserService service) {
        this.service = service;
    }

    @GetMapping("/")
    public String sayHello() {
        return "Hello world!";
    }

    @GetMapping(value = "/selectAllUsers", produces = MediaType.APPLICATION_JSON_VALUE)
    public List<User> selectAll() throws SQLException {
        return service.selectAll();
    }

    @RequestMapping(value = "/insertIntoUser", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.CREATED)
    public void insertUser(@RequestBody User user, Model model) throws SQLException {
        service.insertUser(user);
    }

    @RequestMapping(value = "/updateImage", method = RequestMethod.PUT, consumes = MediaType.APPLICATION_JSON_VALUE)
    public void updateImage(@RequestBody UpdateImage updateImage, Model model) throws SQLException {
        service.updateImage(updateImage);
    }

    @RequestMapping(value = "/deleteUser/{id}", method = RequestMethod.DELETE)
    public void deleteUser(@PathVariable("id") int id) throws SQLException {
        service.deleteUser(id);
    }
}
