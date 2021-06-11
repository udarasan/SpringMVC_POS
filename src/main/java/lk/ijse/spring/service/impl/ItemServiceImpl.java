package lk.ijse.spring.service.impl;

import lk.ijse.spring.dto.ItemDTO;
import lk.ijse.spring.entity.Item;
import lk.ijse.spring.exception.ValidateException;
import lk.ijse.spring.repo.ItemRepo;
import lk.ijse.spring.service.ItemService;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
@Service
@Transactional
public class ItemServiceImpl implements ItemService {

    @Autowired
    public ItemRepo ItemRepo;

    @Autowired
    private ModelMapper mapper;

    @Override
    public void addItem(ItemDTO dto) {
        if (ItemRepo.existsById(dto.getCode())) {
            throw new ValidateException("Item Already Exist");
        }
        ItemRepo.save(mapper.map(dto, Item.class));
    }

    @Override
    public void deleteItem(String id) {
        if (!ItemRepo.existsById(id)) {
            throw new ValidateException("No Item for Delete..!");
        }
        ItemRepo.deleteById(id);
    }

    @Override
    public ItemDTO searchItem(String id) {
        Optional<Item> Item = ItemRepo.findById(id);
        if (Item.isPresent()) {
            return mapper.map(Item.get(), ItemDTO.class);
        }
        return null;
    }

    @Override
    public ArrayList<ItemDTO> getAllItems() {
        List<Item> all = ItemRepo.findAll();
        return mapper.map(all, new TypeToken<ArrayList<ItemDTO>>() {
        }.getType());
    }

    @Override
    public void updateItem(ItemDTO dto) {

        if (ItemRepo.existsById(dto.getCode())) {
            ItemRepo.save(mapper.map(dto, Item.class));
        }
    }
}
