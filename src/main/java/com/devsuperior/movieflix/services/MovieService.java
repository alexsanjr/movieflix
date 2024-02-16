package com.devsuperior.movieflix.services;

import com.devsuperior.movieflix.dto.MovieCardDTO;
import com.devsuperior.movieflix.dto.MovieDetailsDTO;
import com.devsuperior.movieflix.entities.Movie;
import com.devsuperior.movieflix.repositories.MovieRepository;
import com.devsuperior.movieflix.services.exceptions.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class MovieService {

    @Autowired
    private MovieRepository repository;

    @Transactional(readOnly = true)
    public MovieDetailsDTO findById(Long id) {
        Movie entity = repository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Resource not found"));
        return new MovieDetailsDTO(entity);
    }

    @Transactional(readOnly = true)
    public Page<MovieCardDTO> findAll(Pageable pageable, String genreId) {
        if ("0".equals(genreId)) {
            Page<Movie> obj = repository.findAll(pageable);
            return obj.map(MovieCardDTO::new);
        }
        return repository.searchMovies(Long.parseLong(genreId), pageable);
    }
}
