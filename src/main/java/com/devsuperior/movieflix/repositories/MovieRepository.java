package com.devsuperior.movieflix.repositories;

import com.devsuperior.movieflix.dto.MovieCardDTO;
import com.devsuperior.movieflix.entities.Movie;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface MovieRepository extends JpaRepository<Movie, Long> {

    @Query(value = """
        SELECT new com.devsuperior.movieflix.dto.MovieCardDTO(obj)
        FROM Movie obj
        WHERE (:genreId IS NULL OR obj.genre.id = :genreId)
""")
    Page<MovieCardDTO> searchMovies(Long genreId, Pageable pageable);

}
