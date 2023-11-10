package org.example.persistence;

import org.example.persistence.entity.InventoryItem;
import org.springframework.data.domain.Page;

import org.springframework.data.domain.Pageable;
import java.util.Optional;

public interface ArticleRepository {
    Page<InventoryItem> getAllUsers(Pageable pageable);
    Page<InventoryItem> getUsersByEmail(String email, Pageable pageable);
    InventoryItem findUserByEmail(String email);
    Optional<InventoryItem> findUserById(Long id);
    InventoryItem saveUser(InventoryItem user);
}
