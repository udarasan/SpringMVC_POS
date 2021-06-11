package lk.ijse.spring.service;

import lk.ijse.spring.dto.ItemDTO;
import java.util.ArrayList;

public interface ItemService {

    void addItem(ItemDTO dto);

    void deleteItem(String id);

    ItemDTO searchItem(String id);

    ArrayList<ItemDTO> getAllItems();

    void updateItem(ItemDTO dto);

}
