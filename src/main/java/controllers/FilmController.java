package controllers;

import com.tstu.exceptions.MovieLibraryException;
import com.tstu.filters.FilmFilter;
import com.tstu.model.dto.GenreDto;
import com.tstu.utils.jsonResp.NewReview;
import com.tstu.model.Review;
import com.tstu.model.User;
import com.tstu.model.dto.FilmDto;
import com.tstu.model.dto.ReviewDto;
import com.tstu.service.FilmService;
import com.tstu.utils.ResponseUtil;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;

import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.*;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.Response;
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
    public Response allFilm() {

        try {
            List<FilmDto>  filmDtoList = filmService.findFilmList(null,null,null,null,null).stream()
                    .map(film -> modelMapper.map(film, FilmDto.class))
                    .collect(Collectors.toList());

            return ResponseUtil.successResponse(filmDtoList);
        } catch (MovieLibraryException e) {
            return ResponseUtil.notFoundResponse(e);
        }

    }

    @GET
    @Path("/genres")
    public Response allGenres() {
            List<GenreDto>  genreDtoList = filmService.getAllGenre().stream()
                    .map(genre -> modelMapper.map(genre, GenreDto.class))
                    .collect(Collectors.toList());

            return ResponseUtil.successResponse(genreDtoList);
    }

    @POST
    @Path("/find")
    public Response findFilm(FilmFilter filmFilter) {

        System.out.println(filmFilter.toString());
        try {
            List<FilmDto>  filmDtoList = filmService.findByFilter(filmFilter).stream()
                    .map(film -> modelMapper.map(film, FilmDto.class))
                    .collect(Collectors.toList());
            return ResponseUtil.successResponse(filmDtoList);
        } catch (MovieLibraryException e) {
            return ResponseUtil.notFoundResponse(e);
        }

    }


    @GET
    @Path("/{imdb}")
    public Response getFilmById(@PathParam("imdb") String imdbId) {
        try {
            FilmDto filmDto = modelMapper.map(filmService.findById(imdbId), FilmDto.class);
            return ResponseUtil.successResponse(filmDto);
        } catch (MovieLibraryException e) {
            return ResponseUtil.notFoundResponse(e);
        }
    }

    @POST
    @Path("/{imdb}/review")
    public Response postFilmReview(@PathParam("imdb") String imdbId, NewReview newReview) {
        System.out.println(newReview.toString());
        User user = (User) request.getSession().getAttribute("user");
        if (user!=null) {
            try {
                filmService.postReview(filmService.findById(imdbId), user, new Review(user, newReview.getText(), newReview.getRating()));
                return ResponseUtil.successResponse("Отзыв добавлен.");
            } catch (MovieLibraryException e) {
                return ResponseUtil.badRequestResponse(e);
            }
        }
        return ResponseUtil.unauthorizedResponse("Пользователь не авторизован.");
    }

    @PUT
    @Path("/{imdb}/review")
    public Response editFilmReview(@PathParam("imdb") String imdbId, NewReview newReview) {
        System.out.println(newReview.toString());
        User user = (User) request.getSession().getAttribute("user");
        if (user!=null) {
            try {
                filmService.editReview(filmService.findById(imdbId), user, new Review(user, newReview.getText(), newReview.getRating()));
                return ResponseUtil.successResponse("Отзыв изменён.");
            } catch (MovieLibraryException e) {
                return ResponseUtil.badRequestResponse(e);
            }
        }
        return ResponseUtil.unauthorizedResponse("Пользователь не авторизован.");
    }

    @GET
    @Path("/review/view")
    public Response getUserReview(){
        User user = (User) request.getSession().getAttribute("user");
        if (user!=null) {
            List<ReviewDto> reviewDtoList = filmService.getUserReviews(user).stream()
                    .map(review -> modelMapper.map(review, ReviewDto.class))
                    .collect(Collectors.toList());
            return ResponseUtil.successResponse(reviewDtoList);
        }
        return ResponseUtil.unauthorizedResponse("Пользователь не авторизован.");
    }

    @GET
    @Path("/review/all")
    public Response allReviews() {
        List<ReviewDto>  genreDtoList = filmService.getAllReviews().stream()
                .map(review -> modelMapper.map(review, ReviewDto.class))
                .collect(Collectors.toList());

        return ResponseUtil.successResponse(genreDtoList);
    }

    @DELETE
    @Path("/review/{id}")
    public Response deleteReview(@PathParam("id") long id){
        User user = (User) request.getSession().getAttribute("user");
        if (user!=null) {
            try {
                filmService.deleteReview(id, user);
                return ResponseUtil.successResponse("Отзыв удалён.");
            } catch (MovieLibraryException e) {
                return ResponseUtil.badRequestResponse(e);
            }
        }
        return ResponseUtil.unauthorizedResponse("Пользователь не авторизован.");
    }
}
