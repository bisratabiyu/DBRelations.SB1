package com.example.demo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.HashSet;
import java.util.Set;

@Controller
public class HomeController {
    @Autowired
    ActorRepository actorRepository;

    @Autowired
    MovieRepository movieRepository;

    @RequestMapping("/")
    public String loaddata(Model model)
    {
        Actor actorObj1=  new Actor();
        actorObj1.setName("Shah Rukh");
        actorObj1.setRealname("Shah Rukh Khan");

        Movie movieObj1= new Movie();
        movieObj1.setTitle("Kuch Kuch Hota Hai");
        movieObj1.setYear(1998);
        movieObj1.setDescription("romance drama");
        Movie movieObj2= new Movie();
        movieObj2.setTitle("Dil To Pagal Hai");
        movieObj2.setYear(1997);
        movieObj2.setDescription("romance drama");

        Set<Movie> movies= new HashSet<Movie>();
        movies.add(movieObj1);
        movies.add(movieObj2);

        actorObj1.setMovie(movies);

        actorRepository.save(actorObj1);

        model.addAttribute("actors", actorRepository.findAll());

        Set<Actor> cast =new HashSet<Actor>();
        cast.add(actorObj1);

        movieObj1.setCast(cast);
        movieObj2.setCast(cast);
        movieRepository.save(movieObj1);
        movieRepository.save(movieObj2);
        model.addAttribute("movies", movieRepository.findAll());

        return "index";
    }
}
