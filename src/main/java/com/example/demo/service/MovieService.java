package com.example.demo.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.repo.MovieRepository;
import com.example.demo.model.Movie;
@Service
public class MovieService {
	 	@Autowired
	    private MovieRepository movieRepository;
	 	

	    public List<Movie> getMovies() {
	    	System.out.println(movieRepository.count());
	        return movieRepository.findAll();
	    }

	    public Optional<Movie> getMovieByTitle(String title) {
	        return movieRepository.findByTitle(title);
	    }

	    public Movie saveMovie(Movie movie) {
	    	
	        movieRepository.save(movie);
	        return movie;
	    }

	    public Movie getMovieById(int id) 
	    {
	        Optional<Movie> movie = movieRepository.findById(id);
	        if (movie.isPresent()) {
	            return movie.get();
	        }
	        else
	        {
	        	System.out.println("oggetto non trovato");
	        	return null;
	        }
	    }
	    public Movie updateMovie(Integer id, Movie movie)
	    {
	    	Optional <Movie> existMovie =  movieRepository.findById(id);
	    	System.out.println(movie.getMovieId() + " con titolo"  + movie.getTitle());
	    	if (existMovie.isPresent())
	    	{
	    		existMovie.get().setPhotoUrl(movie.getPhotoUrl());
	    		existMovie.get().setActive(movie.isActive());
	    		existMovie.get().setCast(movie.getCast());
	    		existMovie.get().setDirector(movie.getDirector());
	    		existMovie.get().setPlot(movie.getPlot());
	    		existMovie.get().setTitle(movie.getTitle());
	    		existMovie.get().setVote(movie.getVote());
	    		 Movie updatedMovie = movieRepository.save(existMovie.get());
	    		System.out.println("Modifica avvenuta con successo ");
	    		return updatedMovie;
	    	}
	    	else
	    		System.out.println("Film non trovato");
	    	
	    	
	    	return movie;
	    }
	    public Boolean delete (int id)
	    {
	    	Optional<Movie> foundMovie = movieRepository.findById(id);
	    	if(foundMovie.isEmpty())
	    		return false;
	    	movieRepository.delete(foundMovie.get());
	    	return true;
	    }

}
