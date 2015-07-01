
package com.br.ulbra.tcc.rest.api.resources;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;

import com.br.ulbra.tcc.rest.api.constants.URIResource;

/** Example resource class hosted at the URI path "/myresource"
 */
@Path(URIResource.MY_RESOURCE_URI)
public class MyResource {
    
    /** Method processing HTTP GET requests, producing "text/plain" MIME media
     * type.
     * @return String that will be send back as a response of type "text/plain".
     */
    @GET 
    @Produces("text/plain")
    public String getIt() {
        return "Hi there!";
    }
}
