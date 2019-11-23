package com.tstu.servlets;

import com.google.gson.Gson;
import com.tstu.exceptions.MovieLibraryException;
import com.tstu.model.Film;
import com.tstu.model.dto.FilmDto;
import com.tstu.service.FilmService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.context.support.SpringBeanAutowiringSupport;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

@WebServlet(name = "findFilmServlet", urlPatterns = "/films")
public class FindFilmServlet extends HttpServlet {

    @Autowired
    private FilmService filmService;
    @Autowired
    private ModelMapper modelMapper;
    @Autowired
    private Gson gson;

    @Override
    public void init(ServletConfig config) throws ServletException {
        super.init(config);
        SpringBeanAutowiringSupport.processInjectionBasedOnServletContext(this,
                config.getServletContext());
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setCharacterEncoding("UTF-8");
        String name = req.getParameter("name");
        String type = req.getParameter("type");
        String genres = req.getParameter("genres");
        String imdbId = req.getParameter("imdbId");
        String date = req.getParameter("releaseDate");
        try {
            List<Film> filmList = filmService.findFilmList(name, imdbId, type, genres, date);
            List<FilmDto> filmDtoList = filmList.stream().map(film -> modelMapper.map(film, FilmDto.class)).collect(Collectors.toList());
            resp.setContentType("application/json");
            String filmJson = gson.toJson(filmDtoList);
            resp.getWriter().println(filmJson);
        } catch (MovieLibraryException e) {
            System.out.println(e.getMessage());
            resp.setStatus(400);
            resp.getWriter().println(e.getMessage());
        }
    }
}
