package Clase.controller;

import Clase.model.OrdDetailsPAO;
import Clase.service.ClientRaport;
import Clase.service.MySqlOrdDetailsPAOService;
import Clase.service.ProdRaport;
import com.sun.xml.internal.messaging.saaj.soap.dynamic.SOAPMessageFactoryDynamicImpl;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.print.attribute.standard.Media;
import java.sql.SQLException;
import java.util.List;

@Controller
@ResponseBody
public class OrdDetailsPAOController {
    private MySqlOrdDetailsPAOService service;

    public OrdDetailsPAOController(MySqlOrdDetailsPAOService service) {
        this.service = service;
    }

    @RequestMapping(value = "/selectAllOrdDetailsPao", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public List<OrdDetailsPAO> selctAll () throws SQLException {
        return service.selectAll();
    }

    @RequestMapping(value = "/selectOrdDetailsByUserId/{id}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public List<OrdDetailsPAO> selectByUserId (@PathVariable("id") int id) throws SQLException {
        return service.selectByUserId(id);
    }

    @RequestMapping(value = "/selectOrdDetailsByProdusId/{id}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public List<OrdDetailsPAO> selectByProdusId (@PathVariable("id") int id) throws SQLException {
        return service.selectByProdusId(id);
    }

    @RequestMapping(value = "/insertOrdDetails", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
    public void insert (@RequestBody OrdDetailsPAO ordDetailsPAO) throws SQLException {
        service.insert(ordDetailsPAO);
    }

    @RequestMapping(value = "/deleteOrdDetailsById/{id}", method = RequestMethod.DELETE)
    public void delete (@PathVariable("id") int id) throws SQLException {
        service.delete(id);
    }

    @RequestMapping(value = "/findRaportByProducts", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public List<ProdRaport> getRaportProducts () throws SQLException {
        return service.getRaportProducts();
    }

    @RequestMapping(value = "/findRaportByClient", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public List<ClientRaport> getRaportClient () throws SQLException {
        return service.getRaportClient();
    }
}
