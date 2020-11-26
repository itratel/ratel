package org.ratelframework.ratel.common.security.service;

import org.springframework.security.oauth2.common.exceptions.InvalidClientException;
import org.springframework.security.oauth2.provider.ClientDetails;
import org.springframework.security.oauth2.provider.ClientRegistrationException;
import org.springframework.security.oauth2.provider.client.JdbcClientDetailsService;

import javax.sql.DataSource;

/**
 * <p>RatelClientDetailsService<p/>
 * RatelClientDetailsService <br>
 *
 * @author whd.java@gmail.com
 * @date 2020/11/26 17:24
 * @since 1.0.0
 */
public class RatelClientDetailsService extends JdbcClientDetailsService {

    /***
     * 数据源
     * @param dataSource dataSource
     */
    public RatelClientDetailsService(DataSource dataSource) {
        super(dataSource);
    }


    /**
     * Load a client by the client id. This method must not return null.
     *
     * @param clientId The client id.
     * @return The client details (never null).
     * @throws ClientRegistrationException If the client account is locked, expired, disabled, or invalid for any other reason.
     */
    @Override
    public ClientDetails loadClientByClientId(String clientId) throws InvalidClientException {
        return super.loadClientByClientId(clientId);
    }
}
