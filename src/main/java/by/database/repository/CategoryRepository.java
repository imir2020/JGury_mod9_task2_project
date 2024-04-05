package by.database.repository;

import by.database.entity.Category;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface CategoryRepository extends JpaRepository<Category, Long> {

    List<Category> findAllBy();

    Page<Category> findAll(Pageable pageable);

    Optional<Category> findById(Long id);

    Category save(Category category);

    @Modifying
    @Query("""
            update Category c
            set c.categoryName = :categoryName
            where c.id= :id """)
    void updateName(String categoryName, Long id);

    void deleteById(Long id);


}
