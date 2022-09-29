package io.harshproject.moviecatalogservice.resources;

import io.harshproject.moviecatalogservice.models.CatalogItem;
import io.harshproject.moviecatalogservice.models.Movie;
import io.harshproject.moviecatalogservice.models.Rating;
import io.harshproject.moviecatalogservice.models.UserRating;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.reactive.function.client.WebClient;
import java.lang.reflect.ParameterizedType;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/catalog")
public class MovieCatalogResource {

    @Autowired
    private RestTemplate restTemplate;

    @Autowired
    private WebClient.Builder webClientBuilder;

    @RequestMapping("/{userId}")
    public List<CatalogItem> getCatalog(@PathVariable("userId") int userId) {

        UserRating ratings = restTemplate.getForObject("http://localhost:8083/ratingsdata/users/" + userId, UserRating.class);

//        RestTemplate restTemplate = new RestTemplate();

//        List<Rating> ratings = Arrays.asList(
//                new Rating("1234", 3.2f),
//                new Rating("5678", 4.8f),
//                new Rating("9875", 4)
//        );

        return ratings.getRatings().stream().map(rating -> {
            Movie movie = restTemplate.getForObject("http://localhost:8082/movies/" + rating.getMovieId(), Movie.class);

//            Movie movie = webClientBuilder.build()
//                    .get()
//                    .uri("http://localhost:8082/movies/" + rating.getMovieId())
//                    .retrieve()
//                    .bodyToMono(Movie.class)
//                    .block();
            return new CatalogItem(movie.getName(), "Desc", rating.getRating());
                })
                .collect(Collectors.toList());

        // get all rated movie IDs

        // for each movie ID, call movie info and get details

        // Put them all together

//        return Collections.singletonList(
//                new CatalogItem("Swades", "Test", 4.5f)
//        );
    }
}
