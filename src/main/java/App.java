import java.util.Map;
import java.util.HashMap;
import spark.ModelAndView;
import spark.template.velocity.VelocityTemplateEngine;
import static spark.Spark.*;

import java.util.Random;

public class App {
  public static void main(String[] args) {
    staticFileLocation("/public");
    String layout = "templates/layout.vtl";
    // Game newGame;
    int guessLimit = 5;


    get("/", (request, response) -> {
      Map<String, Object> model = new HashMap<String, Object>();
      model.put("template", "templates/start.vtl");
      return new ModelAndView(model, layout);
    }, new VelocityTemplateEngine());

    get("/start", (request, response) -> {
      Map<String, Object> model = new HashMap<String, Object>();
      Random rng = new Random();
      int difficulty = Integer.parseInt(request.queryParams("difficulty"));
      Game newGame = new Game(difficulty, rng.nextInt(Game.getWordCount(difficulty)));
      request.session().attribute("newGame", newGame);
      model.put("newGame", request.session().attribute("newGame"));
      model.put("guessLimit", guessLimit);
      model.put("template", "templates/game.vtl");
      return new ModelAndView(model, layout);
    }, new VelocityTemplateEngine());

    get("/game", (request, response) -> {
      Game newGame = request.session().attribute("newGame");
      Map<String, Object> model = new HashMap<String, Object>();
      if (request.queryParams("guess").length() == 1) {
        newGame.guessLetter(request.queryParams("guess").charAt(0));
      }
      model.put("newGame", request.session().attribute("newGame"));
      model.put("guessLimit", guessLimit);
      model.put("template", "templates/game.vtl");
      return new ModelAndView(model, layout);
    }, new VelocityTemplateEngine());


  }
}
