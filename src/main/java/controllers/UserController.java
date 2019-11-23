package controllers;

import com.tstu.exceptions.MovieLibraryException;
import com.tstu.jsonResp.UserLogin;
import com.tstu.model.User;
import com.tstu.service.UserService;
import com.tstu.utils.UserConstants;
import org.springframework.beans.factory.annotation.Autowired;

import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.*;
import javax.ws.rs.core.Context;

@Path("/restful/user")
@Produces("application/json")
@Consumes("application/json")
public class UserController {
    @Autowired
    private UserService userService;

    @Context
    private HttpServletRequest request;

    //public static User currentUser= UserConstants.guest;

    @POST
    @Path("/login")
    public void login(UserLogin userLogin){
        System.out.println(userLogin.toString());
        try {
            User loginUser = userService.login(userLogin.getUsername(), userLogin.getPassword());
            request.getSession().setAttribute("user", loginUser);
            //currentUser=userService.login(userLogin.getUsername(),userLogin.getPassword());
        } catch (MovieLibraryException e) {
            e.printStackTrace();
        }
    }

    @GET
    @Path("/logout")
    public void logout(){
        User user = (User) request.getSession().getAttribute("user");
        System.out.println(user.getUsername()+" вышел.");
        request.getSession().setAttribute("user", null);
    }
}
