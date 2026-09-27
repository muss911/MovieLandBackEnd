package com.example.demo.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import com.example.demo.model.Movie;
import com.example.demo.service.MovieService;

@RestController
@RequestMapping("/movies")
public class MovieController {
	@Autowired
	private MovieService movieService;
	/*public MovieController(MovieService movieService)
	{
		this.movieService = movieService;
		System.out.println("costruzione del valore");
	}*/
	
	@GetMapping
	public List<Movie> getMovies(){
		return movieService.getMovies();
	}
	@GetMapping("/{id}")
	public Movie getMovie(@PathVariable int id){
		return movieService.getMovieById(id);
	}
	@PostMapping
	public Movie create(@RequestBody Movie movie)
	{
		System.out.println("creazione del movie con id" + movie.getMovieId());
		return movieService.saveMovie(movie);
	}
	@PutMapping("/{id}")
	public Movie setMovie(@PathVariable int id, @RequestBody Movie movie)
	{
		 return movieService.updateMovie(id,movie);
		
		
		
	}
	@DeleteMapping("/{id}")
	public void deleteMovie(@PathVariable int id)
	{
		Boolean isDeleteted = movieService.delete(id);
		System.out.println("eliminazione avvenuta con successo");
		if(!isDeleteted)
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, "item not found");
		//return movieService.getMovies();
			
	}

}
