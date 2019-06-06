package security;

import exeptions.AuthentificationException;
import exeptions.ExceptionMessageCatalog;
import io.jsonwebtoken.Jwts;
import permission.entity.PermissionEntity;
import role.entity.RoleEntity;
import user.authentication.TokenDao;
import user.dao.UserDao;

import javax.annotation.Priority;
import javax.ejb.EJB;
import javax.ws.rs.Priorities;
import javax.ws.rs.container.ContainerRequestContext;
import javax.ws.rs.container.ContainerRequestFilter;
import javax.ws.rs.container.ResourceInfo;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.HttpHeaders;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.SecurityContext;
import javax.ws.rs.ext.Provider;
import java.io.IOException;
import java.lang.reflect.Method;
import java.security.Principal;
import java.util.ArrayList;
import java.util.List;

/**
 * Document me.
 *
 * @author msg systems AG; User Name.
 * @since 19.1.2
 */

@Secured
@Provider
@Priority(Priorities.AUTHENTICATION)
public class AuthenticationFilter implements ContainerRequestFilter {

    @Context
    private ResourceInfo resourceInfo;

    @EJB
    TokenDao tokenDao;

    @EJB
    UserDao userDao;

    @EJB
    KeyGenerator keyGenerator;

    private static final String REALM = "JBugs";
    private static final String AUTHENTICATION_SCHEME = "Bearer";

    @Override
    public void filter(ContainerRequestContext requestContext) throws IOException {

        // Get the Authorization header from the request
        String authorizationHeader =
                requestContext.getHeaderString(HttpHeaders.AUTHORIZATION);

        // Validate the Authorization header
        if (!isTokenBasedAuthentication(authorizationHeader)) {
            abortWithUnauthorized(requestContext);
            return;
        }

        // Extract the token from the Authorization header
        String token = authorizationHeader
                .substring(AUTHENTICATION_SCHEME.length()).trim();

        try {
            // Check if the token is valid
            validateToken(token);

            //extract the data you need
            String username = Jwts.parser().setSigningKey(keyGenerator.getKey()).parseClaimsJws(token).getBody().getIssuer();
            if (username!=null) {
                final SecurityContext securityContext = requestContext.getSecurityContext();
                requestContext.setSecurityContext(new SecurityContext() {
                    @Override
                    public Principal getUserPrincipal() {
                        return new Principal() {
                            @Override
                            public String getName() {
                                return username;
                            }
                        };
                    }
                    @Override
                    public boolean isUserInRole(String permission) {

                        List<RoleEntity> roleEntities = userDao.getUserByUsername(username).getRoleEntityList();
                        List<PermissionEntity> permissionEntities = new ArrayList<>();

                        //creating the list containg all the permissionsAllowed
                        for (RoleEntity r : roleEntities) {
                            for (PermissionEntity p : r.getPermissionEntityList()) {
                                if (!permissionEntities.contains(p)) {
                                    permissionEntities.add(p);
                                }
                            }

                        }

                        List<String> permissionStrings = new ArrayList<>();

                        //getting all the types (description and id are not important)
                        for (PermissionEntity p : permissionEntities) {
                            permissionStrings.add(p.getType());
                        }

                        //returns true if the list contains the permission given as parameter
                        for (String p : permissionStrings) {
                            if (p.equals(permission)) {
                                return true;
                            }
                        }
                        return false;
                    }
                    @Override
                    public boolean isSecure() {
                        return true;
                    }
                    @Override
                    public String getAuthenticationScheme() {
                        return AUTHENTICATION_SCHEME;
                    }
                });
            }
            //getting value from annotation
            Method resourceMethod = resourceInfo.getResourceMethod();
            Secured secured = resourceMethod.getAnnotation(Secured.class);
            if (secured != null){
                List<String> permissionStrings = new ArrayList<>();
                for (SecurityPermission s : secured.permissionsAllowed()) {
                    permissionStrings.add(s.getText());
                }

                //performing authorization
                if (permissionStrings.size() > 0 && !isAuthenticated(requestContext)) {
                    refuseRequest();
                }

                for (String role : permissionStrings) {
                    if (requestContext.getSecurityContext().isUserInRole(role)) {
                        return;
                    }
                    else {
                        throw new AuthentificationException(ExceptionMessageCatalog.NOT_ALLOWED);
                    }
                }

                refuseRequest();
            }
        } catch (AuthentificationException e) {
            abortWithUnauthorized(requestContext);
        }
    }

    private boolean isTokenBasedAuthentication(String authorizationHeader) {

        return authorizationHeader != null && authorizationHeader.toLowerCase()
                .startsWith(AUTHENTICATION_SCHEME.toLowerCase() + " ");
    }

    private void abortWithUnauthorized(ContainerRequestContext requestContext) {
        requestContext.abortWith(
                Response.status(Response.Status.UNAUTHORIZED)
                .header(HttpHeaders.WWW_AUTHENTICATE, AUTHENTICATION_SCHEME + " realm=\\" + REALM + "\\")
                .build()
        );
    }

    private String validateToken(String token) throws AuthentificationException {
        try {
            return tokenDao.getTokenEntity(token).getUser().getUsername();
        }
        //TODO change from exception to specific exception
        catch (Exception e) {
            throw new AuthentificationException(ExceptionMessageCatalog.INVALID_TOKEN);
        }
    }

    private void performAuthorization(String[] rolesAllowed,
                                      ContainerRequestContext requestContext) {

        if (rolesAllowed.length > 0 && !isAuthenticated(requestContext)) {
            refuseRequest();
        }

        for (String role : rolesAllowed) {
            if (requestContext.getSecurityContext().isUserInRole(role)) {
                return;
            }
            else {
                throw new AuthentificationException(ExceptionMessageCatalog.NOT_ALLOWED);
            }
        }

        refuseRequest();
    }

    /**
     * Check if the user is authenticated.
     *
     * @param requestContext
     * @return
     */
    private boolean isAuthenticated(final ContainerRequestContext requestContext) {
        // Return true if the user is authenticated or false otherwise
        return requestContext.getSecurityContext().getUserPrincipal() != null;
    }

    /**
     * Refuse the request.
     */
    private void refuseRequest() {
        throw new AuthentificationException(ExceptionMessageCatalog.NOT_ALLOWED);
    }
}
