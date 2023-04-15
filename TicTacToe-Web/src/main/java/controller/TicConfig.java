package controller;

import javax.servlet.http.HttpSession;
import javax.websocket.HandshakeResponse;
import javax.websocket.server.HandshakeRequest;
import javax.websocket.server.ServerEndpointConfig;

import org.apache.tomcat.websocket.server.DefaultServerEndpointConfigurator;
public class TicConfig extends DefaultServerEndpointConfigurator{
public void modifyHandshake(ServerEndpointConfig sec, HandshakeRequest request,HandshakeResponse response )
{
	sec.getUserProperties().put("username", (String)((HttpSession)request.getHttpSession()).getAttribute("username"));
	
	
}
	

}
