//package io.socialgamification.engine.infrastructure.jersey;
//
//import javax.ws.rs.ext.ContextResolver;
//import javax.ws.rs.ext.Provider;
//
//import com.fasterxml.jackson.annotation.JsonTypeInfo;
//import com.fasterxml.jackson.databind.DeserializationFeature;
//import com.fasterxml.jackson.databind.ObjectMapper;
//import com.fasterxml.jackson.databind.ObjectMapper.DefaultTypeResolverBuilder;
//import com.fasterxml.jackson.databind.SerializationFeature;
//import com.fasterxml.jackson.datatype.jdk8.Jdk8Module;
//import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
//
///**
// * 
// * @author rodrigo.p.fraga
// */
//@Provider
//public class ObjectMapperProvider implements ContextResolver<ObjectMapper> 
//{
//	/**
//	 * 
//	 */
//    private final ObjectMapper objectMapper;
// 
//    /**
//     * 
//     */
//    public ObjectMapperProvider() 
//    {
//        this.objectMapper = createDefaultMapper();
//    }
//
//    /*
//     * (non-Javadoc)
//     * @see javax.ws.rs.ext.ContextResolver#getContext(java.lang.Class)
//     */
//    @Override
//    public ObjectMapper getContext(Class<?> type) 
//    {
//    		return this.objectMapper;
//    }
// 
//    /**
//     * 
//     * @return
//     */
//    public static ObjectMapper createDefaultMapper() 
//    {
//        final ObjectMapper objectMapper = new ObjectMapper();
//        objectMapper.disable( DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES );
//        objectMapper.disable( SerializationFeature.WRITE_DATES_AS_TIMESTAMPS );
//        
//        objectMapper.setDefaultTyping( new DefaultTypeResolverBuilder( ObjectMapper.DefaultTyping.JAVA_LANG_OBJECT )
//			.init( JsonTypeInfo.Id.CLASS, null )
//			.inclusion( JsonTypeInfo.As.PROPERTY )
//			.typeProperty( "@type" ) 
//        	);
//        
//        objectMapper.registerModule( new Jdk8Module() );
//        objectMapper.registerModule( new JavaTimeModule() );
//        
//        return objectMapper;
//    }
//}
