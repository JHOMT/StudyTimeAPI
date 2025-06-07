package jhomt.com.studytimeapi.Domain.Student;

import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface StudentRepository extends JpaRepository<Student, Integer> {
    @Query("""
        SELECT s FROM Student s ORDER BY s.totalPoints DESC
    """)
    List<Student> findDistinctTopByTotalPoints(PageRequest pageRequest);
}
