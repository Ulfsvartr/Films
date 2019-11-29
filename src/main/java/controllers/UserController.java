package controllers;

import com.tstu.exceptions.MovieLibraryException;
import com.tstu.utils.jsonResp.UserLogin;
import com.tstu.model.User;
import com.tstu.service.UserService;
import com.tstu.utils.ResponseUtil;
import org.springframework.beans.factory.annotation.Autowired;

import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.*;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.Response;

@Path("/restful/user")
@Produces("application/json")
@Consumes("application/json")
public class UserController {
    @Autowired
    private UserService userService;

    @Context
    private HttpServletRequest request;

    @POST
    @Path("/login")
    public Response login(UserLogin userLogin){
        System.out.println(userLogin.toString());
        try {
            User loginUser = userService.login(userLogin.getUsername(), userLogin.getPassword());
            request.getSession().setAttribute("user", loginUser);
            return ResponseUtil.successResponse("Пользователь "+loginUser.getUsername()+" авторизован.");
        } catch (MovieLibraryException e) {
            return ResponseUtil.badRequestResponse(e);
        }
    }

    @GET
    @Path("/logout")
    public Response logout(){
        User user = (User) request.getSession().getAttribute("user");
        System.out.println(user.getUsername()+" вышел.");
        request.getSession().setAttribute("user", null);
        return ResponseUtil.successResponse(user.getUsername()+" вышел.");
    }
}
