package jhomt.com.studytimeapi.Domain.Student;

import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface StudentRepository extends JpaRepository<Student, Integer> {
    @Query("""
        SELECT s FROM Student s ORDER BY s.totalPoints DESC
    """)
    List<Student> findDistinctTopByTotalPoints(PageRequest pageRequest);

    @Query("""
        SELECT s FROM Student s WHERE s.email = :email AND s.password = :password
    """)
    Optional<Student> login(@Param("email") String email, @Param("password") String password);
}
