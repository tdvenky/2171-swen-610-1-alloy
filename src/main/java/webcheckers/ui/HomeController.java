package webcheckers.ui;

import java.util.HashMap;
import java.util.Map;

import webcheckers.appl.GameCenter;
import spark.*;

import java.util.Objects;

import static spark.Spark.halt;

/**
 * The Web Controller for the Home page.
 *
 * @author <a href='mailto:bdbvse@rit.edu'>Bryan Basham</a>
 */
public class HomeController implements TemplateViewRoute {
  private final GameCenter gameCenter;


  HomeController(final GameCenter gameCenter) {
    // validation
    Objects.requireNonNull(gameCenter, "gameCenter must not be null");
    //
    this.gameCenter = gameCenter;
  }

  @Override
  public ModelAndView handle(Request request, Response response) {
    final Session httpSession = request.session();


    if(httpSession.isNew()){
      response.redirect("/signin");
      halt();
      return null;
    }


    // start building the View-Model
    final Map<String, Object> vm = new HashMap<>();

    vm.put("title", "Welcome!");

    // retrieve the HTTP session

    if (gameCenter.playersList.size()>0){
      vm.put("playersList",gameCenter.playersList );

    }

    //check if the Player is linked?
    if(gameCenter.playerAlreadyPaired(httpSession.attribute("playerName")))
    {
      String OpponetPlayer = gameCenter.getOpponetplayerFromPairedList(httpSession.attribute("playerName"));
      response.redirect("/game?OpponetPlayer="+OpponetPlayer);
      halt();
      return null;

    }
    vm.put("playersList",gameCenter.playersList);
    System.out.println("playerName"+httpSession.attribute("playerName"));



    return new ModelAndView(vm, "home.ftl");

  }

}