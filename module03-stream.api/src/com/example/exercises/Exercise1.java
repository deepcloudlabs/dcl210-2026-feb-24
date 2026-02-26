package com.example.exercises;

import static java.util.function.Function.identity;
import static java.util.stream.Collectors.counting;
import static java.util.stream.Collectors.groupingBy;

import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.example.domain.Director;
import com.example.domain.Movie;
import com.example.service.InMemoryMovieService;
import com.example.service.MovieService;

/**
 * 
 * @author Binnur Kurt <binnur.kurt@gmail.com>
 *
 */
public class Exercise1 {
	private static final MovieService movieService = InMemoryMovieService.getInstance();

	public static void main(String[] args) {
		// Find the number of movies of each director
        final Collection<Movie> movies = movieService.findAllMovies();
        // imperative programming: before java 8
        System.err.println("Imperative Programming Style");
        HashMap<Director,Long> directorMovieCount = new HashMap<>();
        // external loop
        for (var movie : movies) {
           	// external loop
        	    for (var director: movie.getDirectors()) {
        	    		directorMovieCount.putIfAbsent(director, 0L);
        	    		directorMovieCount.put(director, directorMovieCount.get(director)+1);
        	    }
        }
        // external loop
        for (var entry: directorMovieCount.entrySet()) {
        		System.out.println("%-32s: %2d".formatted(entry.getKey().getName(),entry.getValue()));
        }
        System.err.println("Functional Programming Style");
        // declarative programming: functional programming after java 8+
        // Higher-Order Function: functions accept parameters as function
        // MapReduce Framework: filter, map, reduce, flatMap, distinct, min, max, count, collect,...
        // immutability, function chain (design): HoF -> pipeline (runtime): stream
        Map<Director, Long> directorMovieFunctionalCount = 
        // internal loop		
        movies              							    // Collection<Movie> 
              .stream()     								// Stream<Movie>
              .map(Movie::getDirectors)                  // Stream<List<Director>>
              .flatMap(List::stream)  				    // Stream<Director>
              .collect(groupingBy(identity(),counting())); // Map<Director,Long>
        directorMovieFunctionalCount.forEach((director,count) -> System.out.println("%-32s: %2d".formatted(director.getName(),count)));
	}

}
