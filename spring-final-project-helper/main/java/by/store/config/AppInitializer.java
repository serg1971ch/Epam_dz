package by.store.config;

//
//import by.store.Constants;
//import org.springframework.web.WebApplicationInitializer;
//import org.springframework.web.context.support.AnnotationConfigWebApplicationContext;
//import org.springframework.web.servlet.DispatcherServlet;
//
//import javax.servlet.ServletContext;
//import javax.servlet.ServletRegistration;

public class AppInitializer {}
//        implements WebApplicationInitializer {
//
//    private static final int SESSION_TIMEOUT = 3;
//    private static final int LOAD_ON_STARTUP = 1;
//
//    @Override
//    public void onStartup(ServletContext servletContext) {
//        AnnotationConfigWebApplicationContext context = new AnnotationConfigWebApplicationContext();
//        servletContext.setSessionTimeout(SESSION_TIMEOUT);
//        context.setConfigLocation(Constants.BY_EPAM_LAB);
//        context.setServletContext(servletContext);
//        ServletRegistration.Dynamic dispatcher = servletContext.addServlet(Constants.DISPATCHER, new DispatcherServlet(context));
//        dispatcher.setLoadOnStartup(LOAD_ON_STARTUP);
//        dispatcher.addMapping(Constants.SLASH);
//    }
//
//}

