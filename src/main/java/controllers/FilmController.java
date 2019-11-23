package controllers;

import com.tstu.exceptions.MovieLibraryException;
import com.tstu.filters.FilmFilter;
import com.tstu.jsonResp.NewReview;
import com.tstu.model.Review;
import com.tstu.model.User;
import com.tstu.model.dto.FilmDto;
import com.tstu.model.dto.ReviewDto;
import com.tstu.model.enums.Role;
import com.tstu.service.FilmService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.ws.rs.*;
import javax.ws.rs.core.Context;
import java.util.List;
import java.util.stream.Collectors;

@Path("/restful/film")
@Produces("application/json")
@Consumes("application/json")
public class FilmController {


    @Context
    private HttpServletRequest request;

    @Autowired
    private FilmService filmService;
    @Autowired
    private ModelMapper modelMapper;

    @GET
    @Path("/all")
    public List<FilmDto> allFilm() {

        try {
            return filmService.findFilmList(null,null,null,null,null).stream()
                    .map(film -> modelMapper.map(film, FilmDto.class))
                    .collect(Collectors.toList());

        } catch (MovieLibraryException e) {
            return null;
        }

    }

    @POST
    @Path("/find")
    public List<FilmDto> findFilm(FilmFilter filmFilter) {

        System.out.println(filmFilter.toString());
        try {
            return filmService.findByFilter(filmFilter).stream()
                    .map(film -> modelMapper.map(film, FilmDto.class))
                    .collect(Collectors.toList());

        } catch (MovieLibraryException e) {
            return null;
        }

    }


    @GET
    @Path("/{imdb}")
    public FilmDto getFilmById(@PathParam("imdb") String imdbId) {
        try {
            return modelMapper.map(filmService.findById(imdbId), FilmDto.class);
        } catch (Exception e) {
           return null;
        }
    }

    @POST
    @Path("/{imdb}/review")
    public void postFilmReview(@PathParam("imdb") String imdbId, NewReview newReview) {
        System.out.println(newReview.toString());
        User user = (User) request.getSession().getAttribute("user");
        if (user!=null) {
            try {
                filmService.postReview(filmService.findById(imdbId), user, new Review(user, newReview.getText(), newReview.getRating()));
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    @PUT
    @Path("/{imdb}/review")
    public void editFilmReview(@PathParam("imdb") String imdbId, NewReview newReview) {
        System.out.println(newReview.toString());
        User user = (User) request.getSession().getAttribute("user");
        if (user!=null) {
            try {
                filmService.editReview(filmService.findById(imdbId), user, new Review(user, newReview.getText(), newReview.getRating()));
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    @GET
    @Path("/review/view")
    public List<ReviewDto> getUserReview(){
        User user = (User) request.getSession().getAttribute("user");
        if (user!=null) {
            try {
                return filmService.getUserReviews(user).stream()
                        .map(review -> modelMapper.map(review, ReviewDto.class))
                        .collect(Collectors.toList());
            } catch (Exception e) {
                return null;
            }
        }
        return null;
    }

    @DELETE
    @Path("/review/{id}")
    public void deleteReview(@PathParam("id") int id){
        User user = (User) request.getSession().getAttribute("user");
        if (user!=null) {
            try {
                filmService.deleteReview(id, user);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}
