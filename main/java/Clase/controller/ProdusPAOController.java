package Clase.controller;

import Clase.model.ProdusPAO;
import Clase.service.MySqlProdusPAOService;
import Clase.service.UpdateCantitate;
import com.sun.media.jfxmedia.Media;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.sql.SQLException;
import java.util.List;

@Controller
@ResponseBody
public class ProdusPAOController {
    private final MySqlProdusPAOService service;

    public ProdusPAOController(MySqlProdusPAOService service) {
        this.service = service;
    }

    @RequestMapping(value = "/selectAllProdusPAO", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public List<ProdusPAO> selectAll () throws SQLException {
        return service.selectAll();
    }

    @RequestMapping(value = "/selectAllProdusPAO/{category}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public List<ProdusPAO> selectAll (@PathVariable("category") String category) throws SQLException {
        return service.selectAllByCategory(category);
    }

    @RequestMapping(value = "/selectProdusPAO/{id}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public List<ProdusPAO> selectById (@PathVariable("id") int id) throws SQLException {
        return service.selectById(id);
    }

    @RequestMapping(value = "/deleteProdusPAOById/{id}", method = RequestMethod.DELETE)
    public void delete (@PathVariable("id") int id) throws SQLException {
        service.delete(id);
    }

    @RequestMapping(value = "/insertProdusPAO", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
    public void insert (@RequestBody ProdusPAO produsPAO) throws SQLException {
        service.insert(produsPAO);
    }

    @RequestMapping(value = "/updateProdusPAO", method = RequestMethod.PUT, consumes = MediaType.APPLICATION_JSON_VALUE)
    public void update (@RequestBody UpdateCantitate updateCantitate) throws SQLException {
        service.update(updateCantitate);
    }

    @RequestMapping(value = "/updatePlusProdusPAO", method = RequestMethod.PUT, consumes = MediaType.APPLICATION_JSON_VALUE)
    public void updatePlus (@RequestBody UpdateCantitate updateCantitate) throws SQLException {
        service.updatePlus(updateCantitate);
    }

    @RequestMapping(value = "/selectProduseLikeName/{name}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public List<ProdusPAO> selectLike (@PathVariable("name") String name) throws SQLException {
        return service.selectLike(name);
    }
}
