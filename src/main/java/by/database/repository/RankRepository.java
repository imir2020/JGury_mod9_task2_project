package by.database.repository;

import by.database.entity.Rank;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface RankRepository extends JpaRepository<Rank, Long> {

    List<Rank> findAllBy();

    Optional<Rank> findById(Long id);

    Rank save(Rank rank);

    @Modifying
    @Query("""
            update Rank  r
            set r.salary = :salary
            where r.id = :id
            """)
    void updateRankSalary(Long salary, Long id);

    void deleteById(Long id);
}
