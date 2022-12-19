package com.example.javafx;


import javafx.application.Application;
import javafx.application.HostServices;
import javafx.application.Platform;
import javafx.stage.Stage;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.context.ApplicationContextInitializer;
import org.springframework.context.ApplicationEvent;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.support.GenericApplicationContext;


public class JavafxApplication extends Application {
	private ConfigurableApplicationContext context;

	@Override
	public void init() throws Exception {
		ApplicationContextInitializer<GenericApplicationContext> initializer=new ApplicationContextInitializer<GenericApplicationContext>() {
			@Override
			public void initialize(GenericApplicationContext applicationContext) {
				applicationContext.registerBean(Application.class,() ->JavafxApplication.this);
				applicationContext.registerBean(Parameters.class,()->getParameters());
				applicationContext.registerBean(HostServices.class,()->getHostServices());
			}
		};
		this.context = new SpringApplicationBuilder().sources(fxApplication.class).initializers().run(getParameters().getRaw().toArray(new String[0]));
	}

	@Override
	public void start(Stage primaryStage) throws Exception {
		this.context.publishEvent(new StagereadyEvent(primaryStage));


	}

	@Override
	public void stop() throws Exception {
		this.context.close();
		Platform.exit();
	}
}
class StagereadyEvent extends ApplicationEvent{
	public Stage getStage(){
		return Stage.class.cast(getSource());
	}
	public StagereadyEvent(Object source) {
		super(source);
	}
}
