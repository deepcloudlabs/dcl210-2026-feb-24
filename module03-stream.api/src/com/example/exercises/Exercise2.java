package com.example.exercises;

import static java.util.stream.Collectors.groupingBy;
import static java.util.stream.Collectors.maxBy;

import java.util.Collection;
import java.util.Map;
import java.util.Optional;

import com.example.dao.CountryDao;
import com.example.dao.InMemoryWorldDao;
import com.example.domain.City;
import com.example.domain.Country;

/**
 * 
 * @author Binnur Kurt <binnur.kurt@gmail.com>
 *
 */
public class Exercise2 {
	private static final CountryDao countryDao = InMemoryWorldDao.getInstance();

	public static void main(String[] args) {
		// Find the most populated city of each continent
		var countries = countryDao.findAllCountries();
		Map<String,Optional<ContinentCityPair>> mostPopulatedCityOfContinents =
		countries            // Collection<Country>
		       .stream()     // Stream<Country>
		                     // Stream<List<ContinentCityPair>>
		       .map(country -> country.getCities().stream().map(city-> new ContinentCityPair(country,city)).toList()) 
		       .flatMap(Collection::stream)  // Stream<ContinentCityPair>
		       .collect(groupingBy(ContinentCityPair::continent,maxBy(ContinentCityPair::compareTo)));
		mostPopulatedCityOfContinents.forEach((continent,pair) -> System.out.println("%s: %s,%d".formatted(continent,pair.get().city().getName(),pair.get().city().getPopulation())));
	}

}

record ContinentCityPair(String continent,City city) {
	public ContinentCityPair(Country country,City city) {
		this(country.getContinent(),city);
	}
	public static int compareTo(ContinentCityPair left,ContinentCityPair right) {
		return left.city.getPopulation() - right.city.getPopulation();
	}
	
}