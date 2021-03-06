package com.webcheckers.ui;

import com.webcheckers.appl.GameCenter;
import com.webcheckers.model.Player;
import org.junit.Before;
import org.junit.Test;
import spark.ModelAndView;
import spark.Request;
import spark.Response;
import spark.Session;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class PostRegisterControllerTest {

    private GameCenter gamecenter = new GameCenter();
    private PostRegisterController cut = new PostRegisterController(gamecenter);

    private Request request;
    private Session session;
    private Response response;


    static final String PLAYER_ATTR = "PlayerName";



    @Before
    public void setUp() throws Exception {
        request = mock(Request.class);
        session = mock(Session.class);
        when(request.session()).thenReturn(session);
        response = mock(Response.class);
    }
    @Test(expected=spark.HaltException.class)
    public void registeredSucss(){
        when(request.queryParams(PLAYER_ATTR)).thenReturn("ddd");
        final ModelAndView result = cut.handle(request, response);
    }


}