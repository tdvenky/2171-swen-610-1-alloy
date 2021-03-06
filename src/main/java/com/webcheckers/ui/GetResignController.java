package com.webcheckers.ui;

import com.webcheckers.appl.GameCenter;
import com.webcheckers.model.webcheckersGame;
import org.json.JSONException;
import org.json.JSONObject;
import spark.*;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

import static spark.Spark.halt;

public class GetResignController implements Route {
	private final GameCenter gameCenter;

	public GetResignController(GameCenter gameCenter) {

		Objects.requireNonNull(gameCenter, "gameCenter must not be null");
		this.gameCenter = gameCenter;

	}

	@Override
	public ModelAndView handle(Request request, Response response) throws JSONException {
		String playerOne = request.session().attribute("playerName");

		String opponetName = request.queryParams("OpponetPlayer");

		System.out.println(" Request opponetName" + opponetName);

		webcheckersGame game = gameCenter.getGameBy(playerOne, opponetName);

		if (game == null) {

			System.out.println("Game is null");
			response.redirect("/");
			halt();
			return null;
		}
		game.resignStatus(playerOne);
		gameCenter.resignGame(game);
		response.redirect("/");
		halt();
		return null;

	}
}
