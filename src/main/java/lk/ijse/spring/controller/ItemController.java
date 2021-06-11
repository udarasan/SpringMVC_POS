package lk.ijse.spring.controller;

import lk.ijse.spring.dto.ItemDTO;
import lk.ijse.spring.exception.NotFoundException;
import lk.ijse.spring.service.ItemService;
import lk.ijse.spring.util.StandradResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;

@RestController
@RequestMapping("/api/v1/item")
@CrossOrigin
public class ItemController {

    @Autowired
    private ItemService service;

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity saveItem(@RequestBody ItemDTO dto) {
        if (dto.getCode().trim().length() <= 0) {
            throw new NotFoundException("Item id cannot be empty");
        }
        service.addItem(dto);
        return new ResponseEntity(new StandradResponse("201", "Done", dto), HttpStatus.CREATED);
    }

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity getAllItems() {
        ArrayList<ItemDTO> allItems = service.getAllItems();
        return new ResponseEntity(new StandradResponse("200", "Done", allItems), HttpStatus.OK);
    }

    @GetMapping(path = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity searchItem(@PathVariable String id) {
        ItemDTO ItemDTO = service.searchItem(id);
        return new ResponseEntity(new StandradResponse("200", "Done", ItemDTO), HttpStatus.OK);
    }


    @DeleteMapping(params = {"id"}, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity deleteItem(@RequestParam String id) {
        service.deleteItem(id);
        return new ResponseEntity(new StandradResponse("200", "Done", null), HttpStatus.OK);
    }

    @PutMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity updateItem(@RequestBody ItemDTO dto) {
        if (dto.getCode().trim().length() <= 0) {
            throw new NotFoundException("No id provided to update");
        }
        service.updateItem(dto);
        return new ResponseEntity(new StandradResponse("200", "Done", dto), HttpStatus.OK);
    }


}
