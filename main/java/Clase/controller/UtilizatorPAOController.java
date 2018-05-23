package Clase.controller;

import Clase.model.UtilizatorPAO;
import Clase.service.MySqlUtilizatorPAOService;
import Clase.service.UpdateCategorie;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.sql.SQLException;
import java.util.List;

@Controller
@ResponseBody
public class UtilizatorPAOController {
    private final MySqlUtilizatorPAOService service;

    public UtilizatorPAOController(MySqlUtilizatorPAOService service) {
        this.service = service;
    }

    @GetMapping(value = "/selectAllUtilizatorPAO", produces = MediaType.APPLICATION_JSON_VALUE)
    public List<UtilizatorPAO> selectAll () throws SQLException {
        return service.selectAll();
    }

    @RequestMapping(value = "/insertUtilizatorPAO", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.CREATED)
    public void insertUser (@RequestBody UtilizatorPAO utilizatorPAO) throws SQLException {
        service.insertUser(utilizatorPAO);
    }

    @RequestMapping(value = "/deleteUtilizatorPAO/{id}", method = RequestMethod.DELETE)
    public void deleteUser(@PathVariable("id") int id) throws SQLException {
        service.deleteUser(id);
    }

    @RequestMapping(value = "/selectPasswordByUsername/{username}", method = RequestMethod.GET)
    public String getPassword(@PathVariable("username") String username) throws SQLException {
        return service.getPassword(username);
    }

    @RequestMapping(value = "/selectCategoryByUsername/{username}", method = RequestMethod.GET)
    public String getCategory(@PathVariable("username") String username) throws SQLException {
        return service.getCategory(username);
    }

    @RequestMapping(value = "/updateCategoryByUsername", method = RequestMethod.PUT,
            consumes = MediaType.APPLICATION_JSON_VALUE)
    public void updateCategory(@RequestBody UpdateCategorie updateCategorie) throws SQLException {
        service.updateCategory(updateCategorie);
    }

    @RequestMapping(value = "/selectIdByUsername/{username}", method = RequestMethod.GET)
    public int getId (@PathVariable("username") String username) throws SQLException {
        return service.getId (username);
    }
}
