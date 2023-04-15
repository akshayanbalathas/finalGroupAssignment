package controller;

import java.util.HashSet;
import java.util.Set;

import javax.ws.rs.ApplicationPath;
import javax.ws.rs.core.Application;

import org.glassfish.jersey.media.multipart.MultiPartFeature;

//import provider.MJacksonJsonProvider;

	@ApplicationPath("v1")
	public class AppConfig extends Application{
		
		private Set<Class<?>> ressources = new HashSet<>();
		
		public AppConfig () {
			System.out.println("Created !");
			ressources.add(GameController.class);
			ressources.add(MultiPartFeature.class);
		}

		@Override
		public Set<Class<?>> getClasses() {
			return ressources;
		}


}
