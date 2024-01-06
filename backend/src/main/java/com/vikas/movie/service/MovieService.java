package com.vikas.movie.service;


import com.vikas.movie.model.Movie;
import com.vikas.movie.reposotory.MovieRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class MovieService {


    @Autowired
    private MovieRepository movieRepository;
    public List<Movie> allMovies(){

        return movieRepository.findAll();


    }
    public Optional<Movie> singleMovie(String imdbId){
        return movieRepository.findMovieByImdbId(imdbId);
    }


}
